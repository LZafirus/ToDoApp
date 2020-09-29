package sample.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnector {


    //TODO I am aware that this is not save - only for testing if working reasons
    //FIXME remember to create way to create user etc via login pane
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String dataBase = "machinetodo";

    Connection connection;

    public void connect(String query) {
        System.out.println("working");
        {
            try {

                connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();

                String database = "use machinetodo";
                statement.execute(database);

                statement.execute(query);
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void signUpUser(){

        String insertTest = "INSERT INTO USERS (name, surname, login, password) values ('dawid', 'dawid', 'dawid', 'dawid');";
        String insert = "INSERT INTO " + ConstDataBase.usersTable +
                "(" + ConstDataBase.usersName + ", " + ConstDataBase.usersSurname + ", " +
                ConstDataBase.usersLogin + ", " + ConstDataBase.usersPassword +
                ") VALUES (?, ?, ?, ?);";

        connect(insertTest);


    }

}