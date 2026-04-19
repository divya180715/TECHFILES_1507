package transactions;

import bank.BankProxy;

public class DepositHandler extends TransactionHandler {
    private BankProxy bank;

    public DepositHandler(BankProxy bank) { this.bank = bank; }

    @Override
    public void handleTransaction(String cardNumber, String type, double amount) {
        if (type.equalsIgnoreCase("deposit")) {
            bank.deposit(cardNumber, amount);
            System.out.println("Deposited: " + amount);
        } else if (nextHandler != null) nextHandler.handleTransaction(cardNumber, type, amount);
    }
}
