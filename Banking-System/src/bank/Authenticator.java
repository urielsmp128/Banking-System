package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

    public static Customer userLogin(String username, String password) throws LoginException {

        Customer customer = DataSource.getCustomer(username);

        if(customer == null){
            throw new LoginException("Username not found!");
        }

        if (password.equals(customer.getPassword())){
            customer.setAuthenticated(true);
        }else{
            throw  new LoginException("Incorrect password for this username!");
        }
        return customer;
    }

    public static void userLogout(Customer customer){

        customer.setAuthenticated(false);
    }
}
