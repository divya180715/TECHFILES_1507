package atm;

import bank.BankProxy;

public class PinVerifiedState implements ATMState {
    private ATM atm;

    public PinVerifiedState(ATM atm) { this.atm = atm; }

    @Override
    public void insertCard(String cardNumber) { System.out.println("Card already inserted."); }

    @Override
    public void enterBiometric(String code) { System.out.println("Biometric already verified."); }

    @Override
    public void enterPin(int pin) {
        BankProxy bank = atm.getBankProxy();
        if (bank.verifyPin(atm.getBankProxy().getLastCard(), pin)) {
            System.out.println("PIN verified. You can perform transactions now.");
            atm.setState(atm.getTransactionState());
        } else {
            System.out.println("Invalid PIN. Card ejected.");
            atm.setState(atm.getIdleState());
        }
    }

    @Override
    public void requestTransaction(String type, double amount) { System.out.println("Verify PIN first!"); }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(atm.getIdleState());
    }
}
