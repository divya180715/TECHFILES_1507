package transactions;

import bank.BankProxy;

public class BalanceHandler extends TransactionHandler {
    private BankProxy bank;

    public BalanceHandler(BankProxy bank) { this.bank = bank; }

    @Override
    public void handleTransaction(String cardNumber, String type, double amount) {
        if (type.equalsIgnoreCase("balance")) {
            double balance = bank.getBalance(cardNumber);
            System.out.println("Balance: " + balance);
        } else if (nextHandler != null) nextHandler.handleTransaction(cardNumber, type, amount);
    }
}
