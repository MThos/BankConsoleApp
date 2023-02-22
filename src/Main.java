import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final User _USER = new User();

    public static void main(String[] args) {
        System.out.print("Name: ");
        Scanner reader = new Scanner(System.in);
        _USER.setName(reader.next());
        System.out.println("Welcome back, " + _USER.getName());
        System.out.println();
        transactionOptions();
    }

    public static void transactionOptions() {
        boolean correctOption = false;
        int option = 0;
        Scanner reader = new Scanner(System.in);

        System.out.println("How can we help you?");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Option: ");

        do {
            try {
                option = reader.nextInt();
                if (option > 0 && option < 5) {
                    correctOption = true;
                } else {
                    System.out.println("Must be an option between 1-4.");
                }
            } catch (Exception ex) {
                System.out.println("Must be an option between 1-4.");
                System.out.print("Option: ");
                reader.next();
            }
        } while (!correctOption);

        System.out.println();

        if (option == 4) {
            System.exit(0);
        } else {
            accountOptions(option);
        }
    }

    public static void accountOptions(int type) {
        boolean correctOption = false;
        int option = 0;
        String typeOfTransaction;
        Scanner reader = new Scanner(System.in);

        if (type == 1) {
            typeOfTransaction = "withdraw from?";
        } else if (type == 2) {
            typeOfTransaction = "deposit to?";
        } else {
            typeOfTransaction = "check the balance of?";
        }

        System.out.println("Which account would you like to " + typeOfTransaction);
        System.out.println("1. Chequing");
        System.out.println("2. Savings");
        System.out.println("3. Back");
        System.out.println();
        System.out.print("Option: ");

        do {
            try {
                option = reader.nextInt();
                if (option > 0 && option < 4) {
                    correctOption = true;
                } else {
                    System.out.println("Must be an option between 1-3.");
                }
            } catch (Exception ex) {
                System.out.println("Must be an option between 1-3.");
                System.out.print("Option: ");
                reader.next();
            }
        } while (!correctOption);

        System.out.println();

        if (option == 1) {
            if ((type == 1)) {
                withdrawal(_USER, "C");
            } else if (type == 2) {
                deposit(_USER, "C");
            } else {
                balance(_USER, "C");
            }
        } else if (option == 2) {
            if ((type == 1)) {
                withdrawal(_USER, "S");
            } else if (type == 2) {
                deposit(_USER, "S");
            } else {
                balance(_USER, "S");
            }
        } else {
            transactionOptions();
        }
    }

    public static void withdrawal(User _USER, String accountType) {
        System.out.println("How much would you like to withdraw from your " + getAccountName(accountType) + " account?");
        System.out.print("Amount: ");
        Scanner reader = new Scanner(System.in);
        double amount = reader.nextDouble();

        if (amount == 0) {
            System.out.println();
            System.out.println("You cannot withdraw $0.00");
        } else if (!_USER.confirmBalance(amount, accountType)) {
            System.out.println();
            System.out.println("You do not have enough in your " + getAccountName(accountType) + " account to withdraw $" + df.format(amount));
        } else {
            if (Objects.equals(accountType, "C")) {
                _USER.setChequing(_USER.getChequing() - amount);
            } else {
                _USER.setSavings(_USER.getSavings() - amount);
            }
            System.out.println();
            System.out.println("You have withdrawn $" + df.format(amount) + " from your " + getAccountName(accountType) + " account.");
        }

        System.out.println();
        transactionOptions();
    }

    public static void deposit(User _USER, String accountType) {
        System.out.println("How much would you like to deposit in your " + getAccountName(accountType) + " account?");
        System.out.print("Amount: ");
        Scanner reader = new Scanner(System.in);
        double amount = reader.nextDouble();

        if (amount < 0 || amount == 0) {
            System.out.println();
            System.out.println("You cannot deposit $0.00 or less.");
        } else {
            if (Objects.equals(accountType, "C")) {
                _USER.setChequing(_USER.getChequing() + amount);
            } else {
                _USER.setSavings(_USER.getSavings() + amount);
            }
            System.out.println();
            System.out.println("You have deposited $" + df.format(amount) + " in your " + getAccountName(accountType) + " account.");
        }

        System.out.println();
        transactionOptions();
    }

    public static void balance(User _USER, String accountType) {
        System.out.println("Your " + getAccountName(accountType) + " balance is currently: $" +
                df.format((Objects.equals(accountType, "C") ? _USER.getChequing() : _USER.getSavings())));

        System.out.println();
        transactionOptions();
    }

    public static String getAccountName(String accountType) {
        return (Objects.equals(accountType, "C")) ? "chequing" : "savings";
    }
}
