package atm;

public class IdleState implements ATMState {
    private ATM atm;

    public IdleState(ATM atm) { this.atm = atm; }

    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Card inserted: " + cardNumber);
        atm.setState(atm.getCardInsertedState());
    }

    @Override public void enterBiometric(String code) { System.out.println("Insert card first!"); }
    @Override public void enterPin(int pin) { System.out.println("Insert card first!"); }
    @Override public void requestTransaction(String type, double amount) { System.out.println("Insert card first!"); }
    @Override public void ejectCard() { System.out.println("No card to eject."); }
}
