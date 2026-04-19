package atm;

import bank.BankProxy;
import transactions.*;

public class ATM {
    private ATMState idleState;
    private ATMState cardInsertedState;
    private ATMState biometricState;
    private ATMState pinVerifiedState;
    private ATMState transactionState;

    private ATMState currentState;
    private BankProxy bankProxy;
    private TransactionHandler txChain;

    private String cardNumber;

    public ATM(BankProxy bankProxy) {
        this.bankProxy = bankProxy;

        idleState = new IdleState(this);
        cardInsertedState = new CardInsertedState(this);
        biometricState = new BiometricState(this);
        pinVerifiedState = new PinVerifiedState(this);
        transactionState = new TransactionState(this);

        currentState = idleState;

        setupTransactionChain();
    }

    private void setupTransactionChain() {
        FraudHandler fraud = new FraudHandler(bankProxy);
        WithdrawHandler withdraw = new WithdrawHandler(bankProxy);
        DepositHandler deposit = new DepositHandler(bankProxy);
        BalanceHandler balance = new BalanceHandler(bankProxy);

        fraud.setNextHandler(withdraw);
        withdraw.setNextHandler(deposit);
        deposit.setNextHandler(balance);

        txChain = fraud;
    }

    public void setState(ATMState state) { this.currentState = state; }

    public ATMState getIdleState() { return idleState; }
    public ATMState getCardInsertedState() { return cardInsertedState; }
    public ATMState getBiometricState() { return biometricState; }
    public ATMState getPinVerifiedState() { return pinVerifiedState; }
    public ATMState getTransactionState() { return transactionState; }

    public void insertCard(String cardNumber) {
        this.cardNumber = cardNumber;
        currentState.insertCard(cardNumber);
    }

    public void enterBiometric(String code) { currentState.enterBiometric(code); }
    public void enterPin(int pin) { currentState.enterPin(pin); }
    public void requestTransaction(String type, double amount) {
        txChain.handleTransaction(cardNumber, type, amount);
    }
    public void ejectCard() { currentState.ejectCard(); }

    public BankProxy getBankProxy() { return bankProxy; }
}
