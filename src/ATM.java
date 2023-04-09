import classes.Account;
import classes.Client;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        System.out.println("JAVA BANK");

        // initialize bank clients
        ArrayList<Client> clients = initializeClients();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter pin & press enter ... ");
        String userInput = scanner.nextLine();
        Client foundClient = null;

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).pin.equals(userInput)) {
                foundClient = clients.get(i);
            }
        }
        if (foundClient == null) {
            System.out.println("Wrong pin ");
        } else {
            System.out.println("Welcome " + foundClient.getFirstName() + " " + foundClient.lastName);
            while (true) {
                int userAction = bankLogic(scanner, foundClient);
                if (userAction == 0) {
                    exit(foundClient);
                    return;
                }
            }

        }
    }


    private static int bankLogic(Scanner scanner, Client foundClient) {
        System.out.println("----------------------------------");
        System.out.println("Press 1 to check accounts balance.");
        System.out.println("Press 2 to withdraw.");
        System.out.println("Press 3 to add money in account. ");
        System.out.println("Press 0 to EXIT");
        int userAction = scanner.nextInt();
        switch (userAction) {
            case 1:
                showUserBalance(foundClient);
                break;
            case 2:
                withdraw(foundClient);
                break;
            case 3:
                addMoney(foundClient);
                break;
        }
        return userAction;
    }

    private static void addMoney(Client foundClient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the account you want to insert.");

        for (int i = 0; i < foundClient.accounts.size(); i++) {
            Account account = foundClient.accounts.get(i);
            System.out.println("Press " + i + " for " + account.getCurrency() + " account.");
        }
        int insertAmount = scanner.nextInt();
        Account account = foundClient.accounts.get(insertAmount);
        System.out.println("Insert amount. ");
        int insertedAmount = scanner.nextInt();
        float finalAmount = account.getAmount() + insertedAmount;
        System.out.println("Your new balance is " + finalAmount + " thank you.");


    }

    private static void exit(Client foundClient) {
        System.out.println("Thank you " + foundClient.getFirstName() + " " + foundClient.lastName + ".");
    }

    private static void withdraw(Client foundClient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select account currency ");

        for (int i = 0; i < foundClient.accounts.size(); i++) {
            Account account = foundClient.accounts.get(i);
            System.out.println("Press " + i + " for " + account.getCurrency() + " account.");
        }

        int accountIndex = scanner.nextInt();
        Account account = foundClient.accounts.get(accountIndex);
        System.out.println("Withdraw amount: ");
        int withdrawAmount = scanner.nextInt();
        if (withdrawAmount <= account.getAmount()) {
            float finalAmount = account.getAmount() - withdrawAmount;
            System.out.println("Your new balance is " + finalAmount + " thank you.");

        } else {
            System.out.println("You don't have enough found to do this withdrawal!");
        }
    }


    private static void showUserBalance(Client foundClient) {
        System.out.println("Balance: ");
        foundClient.accounts.forEach(account -> {
            System.out.println(account.getCurrency() + " : " + account.getAmount());
        });
    }

    private static ArrayList<Client> initializeClients() {
        Client c1 = new Client();
        c1.setFirstName("Geri");
        c1.lastName = "Balla";
        c1.pin = "2123";
        Account a1c1 = new Account("ALL", 9200);
        Account a2c1 = new Account("EUR", 9900);
        c1.accounts = new ArrayList<Account>();
        c1.accounts.add(a1c1);
        c1.accounts.add(a2c1);

        Client c2 = new Client();
        c2.setFirstName("Rei");
        c2.lastName = "Balla";
        c2.pin = "2395";
        Account a1c2 = new Account("ALL", 100);
        Account a2c2 = new Account("EUR", 200);
        Account a3c2 = new Account("USD", 200);
        c2.accounts = new ArrayList<>();
        c2.accounts.add(a1c2);
        c2.accounts.add(a2c2);
        c2.accounts.add(a3c2);

        ArrayList<Client> cls = new ArrayList<Client>();
        cls.add(c1);
        cls.add(c2);
        return cls;
    }
}
