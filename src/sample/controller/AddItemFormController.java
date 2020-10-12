package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.model.Task;
import sample.model.User;
import sample.mysql.MySqlConnector;

import java.sql.SQLException;
import java.util.Calendar;

public class AddItemFormController {

    private int userId;

    private MySqlConnector mySqlConnector;

    @FXML
    private TextField addTaskDesc;

    @FXML
    private TextField addTaskName;

    @FXML
    private Button addTaskButton;

    @FXML
    void initialize() {

        addTaskButton.setOnAction(event -> {
            addTask();
        });
    }

    public void addTask() {
        mySqlConnector = new MySqlConnector();

        try {
            mySqlConnector.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String taskName = addTaskName.getText();
        String taskDesc = addTaskDesc.getText();

        Task task = new Task(taskName, taskDesc);

        try {
            mySqlConnector.insertTask(task);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("user id is " + this.userId);
    }

    public int getUserId(){
        return this.userId;
    }
}