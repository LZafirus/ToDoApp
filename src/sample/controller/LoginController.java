package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

        String loginText = loginUsername.getText().trim();
        String passwordText = loginPassword.getText().trim();

        loginButton.setOnAction(event -> {

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

        loginButton.setOnAction(event -> {
            if (!loginText.equals("") || !loginPassword.equals("")){
                loginUser(loginText, passwordText);
        } else {
                System.err.println("Login or password is missing");
            }
        });

        loginButton.setOnAction(event -> {
            loginUser(loginUsername.getText(), loginPassword.getText());
        });
    }

    private void loginUser(String userName, String userPassword) {
        if (!userName.equals("") || !userPassword.equals("")){

        } else {

        }
    }


}
