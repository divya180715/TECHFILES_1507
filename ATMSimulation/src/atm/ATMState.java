package atm;

public interface ATMState {
    void insertCard(String cardNumber);
    void enterBiometric(String code);
    void enterPin(int pin);
    void requestTransaction(String type, double amount);
    void ejectCard();
}
