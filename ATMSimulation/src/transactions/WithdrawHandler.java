package transactions;

import bank.BankProxy;

public class WithdrawHandler extends TransactionHandler {
    private BankProxy bank;

    public WithdrawHandler(BankProxy bank) { this.bank = bank; }

    @Override
    public void handleTransaction(String cardNumber, String type, double amount) {
        if (type.equalsIgnoreCase("withdraw")) {
            if (bank.withdraw(cardNumber, amount))
                System.out.println("Withdrawn: " + amount);
            else System.out.println("Insufficient Balance");
        } else if (nextHandler != null) nextHandler.handleTransaction(cardNumber, type, amount);
    }
}
