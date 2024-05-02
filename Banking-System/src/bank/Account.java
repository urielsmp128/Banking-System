package bank;

import bank.exceptions.AmountException;

public class Account {
    private int id;
    private String type;
    private double balance;

    public Account(int id, String type, double balance){
        setId(id);
        setType(type);
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void deposit(double amount) throws AmountException {
        if(amount < 0){
            throw new AmountException("The minimum deposit amount is $1");
        }else {
            double newBalance = balance + amount;
            setBalance(newBalance);
            DataSource.updateAccountBalance(id, newBalance);
        }
    }

    public void withdraw(double amountToBeWithdrawed) {
    }
}
