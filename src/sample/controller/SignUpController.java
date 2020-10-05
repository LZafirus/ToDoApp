package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.model.User;
import sample.mysql.MySqlConnector;

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
    void initialize(){

        signUp.setOnAction(event -> createUser());

    }

    private void createUser() {

        MySqlConnector connector = new MySqlConnector();
        connector.connect();

        String name = signUpName.getText();
        String surname = signUpSurname.getText();
        String login = signUpLogin.getText();
        String password = signUpPassword.getText();

        User user = new User(name, surname, login, password);

        try {
            connector.signUpUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
