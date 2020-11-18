package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Fade;
import sample.model.User;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpLogin;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUp;

    @FXML
    private Button signUpBack;

    @FXML
    private Label signUpAddedLabel;

    Fade fade;

    @FXML
    void initialize() {
        signUp.setOnAction(event -> {
            createUser();
            signUpAddedLabel.setText("User " + signUpName.getText() + " added.");
            signUpAddedLabel.setVisible(true);

            fade = new Fade(signUpAddedLabel);
            fade.fade();
        });

        signUpBack.setOnAction(event -> {
            getBack(signUpBack);
        });
    }

    private void getBack(Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void createUser() {
        MySqlConnector connector = new MySqlConnector();

        try {
            connector.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String name = signUpName.getText();
        String surname = signUpSurname.getText();
        String login = signUpLogin.getText();
        String password = signUpPassword.getText();

        User user = new User(name, surname, login, password);

        connector.signUpUser(user);
    }
}
