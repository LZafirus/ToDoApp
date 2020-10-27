package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Task;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.SQLException;

public class AddItemFormController {

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

    public static int userId;

    private MySqlConnector mySqlConnector;

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/list.fxml"));

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}