package sample.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaConnection {

    //TODO I am aware that this is not save - only for testing if working reasons
    //FIXME remember to create way to create user etc via login pane

    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";

    Connection connection;

    {
        try {

            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String query = "CREATE DATABASE bazaTestowa";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}



