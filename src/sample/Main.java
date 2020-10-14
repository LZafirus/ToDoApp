package sample;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, ClassNotFoundException {

        //MySqlConnector connector = new MySqlConnector();
        //connector.connect();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/sample/view/details.fxml"));
        primaryStage.setTitle("Personal To-Do Machine");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}