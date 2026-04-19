package bank;

public class BankProxy implements BankOperations {
    private RealBankServer realBank;
    private String lastCard;

    public BankProxy() { realBank = new RealBankServer(); }

    public String getLastCard() { return lastCard; }

    @Override
    public boolean verifyPin(String cardNumber, int pin) {
        lastCard = cardNumber;
        // Could add logging or security checks here
        return realBank.verifyPin(cardNumber, pin);
    }

    @Override
    public boolean verifyBiometric(String cardNumber, String biometric) {
        lastCard = cardNumber;
        return realBank.verifyBiometric(cardNumber, biometric);
    }

    @Override
    public double getBalance(String cardNumber) { return realBank.getBalance(cardNumber); }

    @Override
    public boolean withdraw(String cardNumber, double amount) { return realBank.withdraw(cardNumber, amount); }

    @Override
    public void deposit(String cardNumber, double amount) { realBank.deposit(cardNumber, amount); }

    @Override
    public void logTransaction(String cardNumber, String type, double amount) { realBank.logTransaction(cardNumber, type, amount); }
}
