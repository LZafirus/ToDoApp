package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {

        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
        primaryStage.setTitle("Personal To-Do Machine");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

//        MySqlConnector mySqlConnector = new MySqlConnector();
//        mySqlConnector.connect();
//        ResultSet resultSet = mySqlConnector.getTasksByUser(3);
//
//        while (resultSet.next()){
//            System.out.println("User tasks: " + resultSet.getString("task_name"));
//        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}