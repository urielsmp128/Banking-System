package bank;

import java.sql.*;

public class DataSource {

    public static Connection connect(){

        String db_file = "jdbc:sqlite:C:\\Users\\uriel\\OneDrive\\Documents\\Github\\Banking-System\\Banking-System\\resources\\bank.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(db_file);
            System.out.println("We're connected!");
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return connection;
    }

    public static Customer getCustomer(String username){
        String sql = "select * from customers where username = ?";
        Customer customer = null;
        try(Connection connection = connect();
            PreparedStatement preparedStatement =  connection.prepareStatement(sql)){
                preparedStatement.setString(1, username);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    customer = new Customer(resultSet.getInt("Id"), resultSet.getString("name"),
                            resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("account_id"), false);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  customer;
    }

    public static Account getAccount(int accountId){
        String sql = "select * from accounts where id = ?";
        Account account = null;
        try (Connection connection = connect();
             PreparedStatement preparedStatement =  connection.prepareStatement(sql)){
            preparedStatement.setInt(1, accountId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                account = new Account(resultSet.getInt("id"), resultSet.getString("type"),
                        resultSet.getDouble("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  account;
    }

    public static  void updateAccountBalance(int accountId, double newBalance){
        String sql = "update accounts set balance = ? where id = ?";

        try (Connection connection = connect();

             PreparedStatement preparedStatement =  connection.prepareStatement(sql)){

            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, accountId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
