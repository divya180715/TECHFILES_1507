package atm;

import bank.BankProxy;

public class BiometricState implements ATMState {
    private ATM atm;

    public BiometricState(ATM atm) { this.atm = atm; }

    @Override
    public void insertCard(String cardNumber) { System.out.println("Card already inserted."); }

    @Override
    public void enterBiometric(String code) {
        BankProxy bank = atm.getBankProxy();
        if (bank.verifyBiometric(atm.getBankProxy().getLastCard(), code)) {
            System.out.println("Biometric verified.");
            atm.setState(atm.getPinVerifiedState());
        } else {
            System.out.println("Biometric verification failed.");
            atm.setState(atm.getIdleState());
        }
    }

    @Override public void enterPin(int pin) { System.out.println("Biometric verification required first!"); }
    @Override public void requestTransaction(String type, double amount) { System.out.println("PIN required first!"); }
    @Override public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(atm.getIdleState());
    }
}
