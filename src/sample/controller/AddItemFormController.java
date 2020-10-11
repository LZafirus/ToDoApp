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
    private TextField addTaskDesc;

    @FXML
    private TextField addTaskName;

    @FXML
    private Button addTaskButton;

    @FXML
    void initialize() {

        mySqlConnector = new MySqlConnector();

        try {
            mySqlConnector.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        addTaskButton.setOnAction(event -> {
            String taskName = addTaskName.getText();
            String taskDesc = addTaskDesc.getText();

            Task task = new Task(taskName, taskDesc);
            try {
                mySqlConnector.insertTask(task);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });
    }

}