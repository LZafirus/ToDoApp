package sample.mysql;

import sample.model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class MySqlConnector {


    //TODO I am aware that this is not safe - only for testing if working reasons

    String url = "jdbc:mysql://localhost:3306/machinetodo";
    String user = "root";
    String password = "";
    String dataBase = "machinetodo";

    Connection connection;

    public Connection connect() throws ClassNotFoundException, SQLException {

        connection = DriverManager.getConnection(url, user, password);
        Class.forName("com.mysql.jdbc.Driver");

        return connection;
    }

    public void signUpUser(User user) throws SQLException {

        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.usersTable +
                    "(" + ConstDataBase.usersName + ", " + ConstDataBase.usersSurname + ", " +
                    ConstDataBase.usersLogin + ", " + ConstDataBase.usersPassword +
                    ") VALUES ('" + user.getFirstName() + "', '"
                    + user.getLastName() + "', '"
                    + user.getLogin() + "', '"
                    + user.getPassword() + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loginInUser(User user) throws SQLException {
        ResultSet resultSet;
        Statement statement = connection.createStatement();
        if (!user.getLogin().equals("") && !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + ConstDataBase.usersTable
                    + " WHERE " + ConstDataBase.usersLogin + " = '" + user.getLogin()
                    + "' AND " + ConstDataBase.usersPassword + " = '" + user.getPassword() + "';";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(
                        "id:" + resultSet.getString("user_id"));
            }
        } else {
            System.out.println("Missing login or password.");
        }
    }

    public void disconnect() throws SQLException {
        Statement statement = connection.createStatement();
        statement.close();
    }
}