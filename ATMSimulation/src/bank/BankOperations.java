package bank;

public interface BankOperations {
    boolean verifyPin(String cardNumber, int pin);
    boolean verifyBiometric(String cardNumber, String biometric);
    double getBalance(String cardNumber);
    boolean withdraw(String cardNumber, double amount);
    void deposit(String cardNumber, double amount);
    void logTransaction(String cardNumber, String type, double amount);
}
