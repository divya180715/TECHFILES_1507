package transactions;

public abstract class TransactionHandler {
    protected TransactionHandler nextHandler;
    public void setNextHandler(TransactionHandler handler) { nextHandler = handler; }
    public abstract void handleTransaction(String cardNumber, String type, double amount);
}
