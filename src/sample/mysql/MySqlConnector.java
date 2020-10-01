package sample.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void signUpUser(String name, String surname, String login, String password) throws SQLException {

        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.usersTable +
                    "(" + ConstDataBase.usersName + ", " + ConstDataBase.usersSurname + ", " +
                    ConstDataBase.usersLogin + ", " + ConstDataBase.usersPassword +
                    ") VALUES ('" + name + "', '"
                                    + surname + "', '"
                                     + login + "', '"
                                        + password + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect() throws SQLException {
       Statement statement = connection.createStatement();
       statement.close();
    }

}