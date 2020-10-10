package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.model.Task;
import sample.mysql.MySqlConnector;

import java.sql.SQLException;
import java.util.Calendar;

public class AddItemFormController {

    private MySqlConnector mySqlConnector;

    @FXML
    private TextField addItemDescLabel;

    @FXML
    private TextField addTaskLabel;

    @FXML
    private Button addSaveButton;

    @FXML
    void initialize() {

        mySqlConnector = new MySqlConnector();

        addSaveButton.setOnAction(event -> {
            Task task = new Task();
            try {
                mySqlConnector.insertTask(task);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });


    }

}