package bank;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Welcome to Globe Bank Internationa;!");

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
