import atm.ATM;
import bank.BankProxy;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankProxy bankProxy = new BankProxy();
        ATM atm = new ATM(bankProxy);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Welcome to Smart ATM ===");
            System.out.println("1. Insert Card");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter card number: ");
                    String cardNumber = sc.nextLine();
                    atm.insertCard(cardNumber);

                    System.out.print("Enter biometric code: ");
                    String bioCode = sc.nextLine();
                    atm.enterBiometric(bioCode);

                    System.out.print("Enter PIN: ");
                    int pin = sc.nextInt();
                    sc.nextLine();
                    atm.enterPin(pin);

                    boolean sessionActive = true;
                    while (sessionActive) {
                        System.out.println("\n--- Transactions ---");
                        System.out.println("1. Withdraw");
                        System.out.println("2. Deposit");
                        System.out.println("3. Balance");
                        System.out.println("4. Eject Card");
                        System.out.print("Choose: ");
                        int txChoice = sc.nextInt();
                        sc.nextLine();

                        switch (txChoice) {
                            case 1:
                                System.out.print("Enter amount to withdraw: ");
                                double wAmt = sc.nextDouble();
                                sc.nextLine();
                                atm.requestTransaction("withdraw", wAmt);
                                break;
                            case 2:
                                System.out.print("Enter amount to deposit: ");
                                double dAmt = sc.nextDouble();
                                sc.nextLine();
                                atm.requestTransaction("deposit", dAmt);
                                break;
                            case 3:
                                atm.requestTransaction("balance", 0);
                                break;
                            case 4:
                                atm.ejectCard();
                                sessionActive = false;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Thank you for using Smart ATM!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
