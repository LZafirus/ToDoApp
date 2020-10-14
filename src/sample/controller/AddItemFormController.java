package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Task;
import sample.mysql.MySqlConnector;

import java.sql.SQLException;

public class AddItemFormController {

    public static int userId;

    private MySqlConnector mySqlConnector;

    @FXML
    private TextField addTaskDesc;

    @FXML
    private TextField addTaskName;

    @FXML
    private Button addTaskButton;

    @FXML
    private Label addingLabelSucess;

    @FXML
    private Button todosButton;

    @FXML
    void initialize() {

        addTaskButton.setOnAction(event -> {
            try {
                addTask();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void addTask() throws SQLException {
        mySqlConnector = new MySqlConnector();

        String taskName = addTaskName.getText().trim();
        String taskDesc = addTaskDesc.getText().trim();
        Task task = new Task(taskName, taskDesc);


        try {
            mySqlConnector.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        task.setTaskName(taskName);
        task.setTaskDesc(taskDesc);
        task.setUserId(getUserId());

        try {
            mySqlConnector.insertTask(task);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        addingLabelSucess.setVisible(true);
        todosButton.setVisible(true);

        int taskNumber = mySqlConnector.getAllTasks(AddItemController.userId);
        todosButton.setText("Back to list. Total: " + taskNumber);

        addTaskName.setText("");
        addTaskDesc.setText("");

        todosButton.setOnAction(event -> {

        });

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}