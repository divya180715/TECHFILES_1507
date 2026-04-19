package atm;

public class TransactionState implements ATMState {
    private ATM atm;

    public TransactionState(ATM atm) { this.atm = atm; }

    @Override
    public void insertCard(String cardNumber) { System.out.println("Card already inserted."); }
    @Override
    public void enterBiometric(String code) { System.out.println("Biometric already verified."); }
    @Override
    public void enterPin(int pin) { System.out.println("PIN already verified."); }

    @Override
    public void requestTransaction(String type, double amount) {
        atm.requestTransaction(type, amount);
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(atm.getIdleState());
    }
}
