package transactions;

import bank.BankProxy;

public class FraudHandler extends TransactionHandler {
    private BankProxy bank;

    public FraudHandler(BankProxy bank) { this.bank = bank; }

    @Override
    public void handleTransaction(String cardNumber, String type, double amount) {
        if (type.equalsIgnoreCase("withdraw") && amount > 10000) {
            System.out.println("Fraud Alert: Withdrawal exceeds limit!");
            return;
        }
        if (nextHandler != null) nextHandler.handleTransaction(cardNumber, type, amount);
    }
}
