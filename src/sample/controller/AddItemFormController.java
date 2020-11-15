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
import java.sql.Statement;
import java.util.Calendar;

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

    @FXML
    private Button addTaskBackButton;

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
            stage.show();

            closeWindows(todosButton);
        });

        addTaskBackButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/mainPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            closeWindows(todosButton);
        });
    }

    public void addTask() throws SQLException {

        mySqlConnector = new MySqlConnector();

        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

        Task task = new Task();

        String taskName = addTaskName.getText().trim();
        String taskDesc = addTaskDesc.getText().trim();

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
        task.setDatecreated(timestamp);

        try {
            mySqlConnector.insertTask(task);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        addingLabelSucess.setVisible(true);
        todosButton.setVisible(true);

        int taskNumber = mySqlConnector.getAllTasks(MainPageController.userId);
        todosButton.setText("Back to list. Total: " + taskNumber);

        addTaskName.setText("");
        addTaskDesc.setText("");


    }

    private void closeWindows(Button button){
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}