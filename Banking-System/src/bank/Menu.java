package bank;

import bank.exceptions.AmountException;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Welcome to Globe Bank International!");

        Menu menu = new Menu();
        menu.scanner = new Scanner(System.in);

        Customer customer = menu.authenticateUser();

        if(customer != null){
            Account account = DataSource.getAccount(customer.getAccountId());
            menu.showMenu(customer, account);
        }




        menu.scanner.close();
    }

    private void showMenu(Customer customer, Account account) {

        int selection = 0;

        while (selection != 4 && customer.isAuthenticated()) {

            System.out.println("Please select one of the following options:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check balance");
            System.out.println("4. Exit");

            selection = scanner.nextInt();

            switch (selection){
                case 1:
                    System.out.println("How much would you like to deposit? : ");
                    double amount = scanner.nextDouble();
                    try{
                        account.deposit(amount);
                    } catch (AmountException e){
                        System.out.println(e.getMessage());
                        System.out.println("Please try again!");
                    }
                    break;

                case 2:
                    System.out.println("How much would you like to withdraw? : ");
                    double amountToBeWithdrawed = scanner.nextDouble();
                    try{
                        account.withdraw(amountToBeWithdrawed);
                    } catch (AmountException e){
                        System.out.println(e.getMessage());
                        System.out.println("Please try again!");
                    }
                    break;

                case 3:
                    System.out.println("Current balance: $" + account.getBalance());
                    break;

                case 4:
                    Authenticator.userLogout(customer);
                    System.out.println("Thanks for banking in Globe Bank International ");
                    break;

                default:
                    System.out.println("Invalid option, please try again");
                    break;

            }
        }

    }

    private Customer authenticateUser(){
        System.out.println("Please enter your username: ");
        String username = scanner.next();

        System.out.println("Please enter your password: ");
        String password = scanner.next();

        Customer customerAuthenticated = null;
        try {
            customerAuthenticated = Authenticator.userLogin(username, password);
        } catch (LoginException e){
            System.out.println("There was an error: " + e.getMessage());
        }
        return customerAuthenticated;
    }

}
