package sample.mysql;

import sample.model.User;

import java.sql.*;

public class MySqlConnector {


    //TODO I am aware that this is not safe - only for testing if working reasons
    //FIXME remember to create way to create user etc via login pane

    String url = "jdbc:mysql://localhost:3306/machinetodo";
    String user = "root";
    String password = "";
    String dataBase = "machinetodo";

    Connection connection;

    public void connect() {

        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void disconnect() throws SQLException {
       Statement statement = connection.createStatement();
       statement.close();
    }

    public ResultSet getUser(User user) throws SQLException {
        ResultSet resultSet = null;

        if(!user.getLogin().equals("") || !user.getPassword().equals("")){
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM " + ConstDataBase.usersTable +
                    " WHERE " + ConstDataBase.usersLogin + " = '" + user.getLogin()
                    + "' AND " + ConstDataBase.usersPassword + " ='" + user.getPassword() + "';";

            resultSet = statement.executeQuery(query);
        } else {
            System.out.println("Enter correct credentials");
        }

       // return resultSet;
        return resultSet;
    }
}