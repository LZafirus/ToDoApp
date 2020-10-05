package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {


    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignUp;

    @FXML
    void initialize(){

        loginButton.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String passwordText = loginPassword.getText().trim();

            User user  = new User();
            user.setLogin(loginText);
            user.setPassword(passwordText);

            MySqlConnector connector = new MySqlConnector();
            try {
                connector.connect();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connector.loginInUser(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });

        loginSignUp.setOnAction(event -> {

            loginSignUp.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}
