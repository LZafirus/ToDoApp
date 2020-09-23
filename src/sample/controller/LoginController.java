package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    void initialize(){


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("working check");
            }
        });
    }

    private void loginUserIn() throws IOException {

        if(!loginUsername.getText().toString().trim().equals("")
                && !loginPassword.getText().toString().trim().equals(""))
        {
            Stage detailsStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/details.fxml"));

        }

    }

}
