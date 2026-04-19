#include <iostream>
#include <string>
#include <map>
#include <sstream>
#include <fstream>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iomanip>

// Link with the Windows Socket library
#pragma comment(lib, "ws2_32.lib")

// Hardcoded exchange rates relative to 1 USD 
// This acts as our C++ database of rates across the globe!
std::map<std::string, double> exchangeRates = {
    {"USD", 1.0}, {"EUR", 0.92}, {"GBP", 0.79}, {"INR", 83.15},
    {"JPY", 151.20}, {"AUD", 1.54}, {"CAD", 1.36}, {"CHF", 0.90},
    {"CNY", 7.23}, {"SEK", 10.60}, {"NZD", 1.67}, {"MXN", 16.50},
    {"SGD", 1.35}, {"HKD", 7.83}, {"NOK", 10.85}, {"KRW", 1350.50},
    {"TRY", 32.20}, {"RUB", 92.50}, {"BRL", 5.05}, {"ZAR", 18.80}
};

// Helper function to extract URL parameters
std::string getQueryParam(const std::string& path, const std::string& param) {
    size_t start = path.find(param + "=");
    if (start == std::string::npos) return "";
    start += param.length() + 1;
    size_t end = path.find("&", start);
    if (end == std::string::npos) end = path.length();
    return path.substr(start, end - start);
}

// Function to read the HTML file from disk
std::string readHtmlFile(const std::string& filepath) {
    std::ifstream file(filepath);
    if (!file.is_open()) return "";
    std::stringstream buffer;
    buffer << file.rdbuf();
    return buffer.str();
}

// Function to handle a single HTTP client connection
void handleClient(SOCKET clientSocket) {
    char buffer[4096];
    int bytesReceived = recv(clientSocket, buffer, sizeof(buffer) - 1, 0);
    
    if (bytesReceived <= 0) {
        closesocket(clientSocket);
        return;
    }
    buffer[bytesReceived] = '\0';
    
    std::string request(buffer);
    std::string response;

    // Simple HTTP request parsing
    std::istringstream reqStream(request);
    std::string method, path, version;
    reqStream >> method >> path >> version;

    if (method == "GET" && path == "/") {
        // Serve the UI (frontend)
        std::string html = readHtmlFile("index.html");
        if(html.empty()) html = "<h1>Error: index.html not found in current folder!</h1>";
        
        response = "HTTP/1.1 200 OK\r\n";
        response += "Content-Type: text/html\r\n";
        response += "Connection: close\r\n\r\n";
        response += html;
    } 
    else if (method == "GET" && path.find("/api/convert") == 0) {
        // Handle C++ API requests from Frontend
        std::string amountStr = getQueryParam(path, "amount");
        std::string fromStr = getQueryParam(path, "from");
        std::string toStr = getQueryParam(path, "to");

        double amount = 0.0;
        try { amount = std::stod(amountStr); } catch(...) {}

        if (exchangeRates.count(fromStr) && exchangeRates.count(toStr)) {
            // Processing in C++ Backend
            double rateFrom = exchangeRates[fromStr];
            double rateTo = exchangeRates[toStr];
            
            double amountInUsd = amount / rateFrom;
            double finalAmount = amountInUsd * rateTo;

            // Generate JSON Output
            std::stringstream json;
            json << std::fixed << std::setprecision(2);
            json << "{\"result\": " << finalAmount << "}";

            response = "HTTP/1.1 200 OK\r\n";
            response += "Content-Type: application/json\r\n";
            response += "Access-Control-Allow-Origin: *\r\n";
            response += "Connection: close\r\n\r\n";
            response += json.str();
        } else {
            // Bad Request
            response = "HTTP/1.1 400 Bad Request\r\nConnection: close\r\n\r\n{\"error\":\"Invalid currencies\"}";
        }
    } 
    else {
        // Standard 404 response
        response = "HTTP/1.1 404 Not Found\r\nConnection: close\r\n\r\n404 Not Found";
    }

    send(clientSocket, response.c_str(), response.length(), 0);
    closesocket(clientSocket);
}

int main() {
    // 1. Initialize Winsock
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        std::cerr << "WSAStartup failed.\n";
        return 1;
    }

    // 2. Create Socket
    SOCKET serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket == INVALID_SOCKET) {
        std::cerr << "Socket creation failed.\n";
        WSACleanup();
        return 1;
    }

    // 3. Bind to IP and Port
    sockaddr_in serverAddr;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(8080); // Serving on port 8080

    if (bind(serverSocket, (sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        std::cerr << "Bind failed.\n";
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    // 4. Start Listening
    if (listen(serverSocket, SOMAXCONN) == SOCKET_ERROR) {
        std::cerr << "Listen failed.\n";
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    std::cout << "==========================================\n";
    std::cout << " Currency Converter C++ Server is Live!   \n";
    std::cout << " Open your browser to: http://localhost:8080 \n";
    std::cout << " Waiting for connections...               \n";
    std::cout << "==========================================\n";

    // 5. Accept connections indefinitely
    while (true) {
        SOCKET clientSocket = accept(serverSocket, nullptr, nullptr);
        if (clientSocket != INVALID_SOCKET) {
            handleClient(clientSocket);
        }
    }

    // 6. Cleanup (unreachable due to infinite loop, but good practice)
    closesocket(serverSocket);
    WSACleanup();
    return 0;
}
