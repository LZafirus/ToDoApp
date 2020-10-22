package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.model.Task;
import sample.mysql.MySqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<Task> listListView;

    @FXML
    private TextField listTaskName;

    @FXML
    private TextField listTaskDesc;

    @FXML
    private Button listSaveButton;

    private ObservableList<Task> tasks;

    private MySqlConnector mySqlConnector;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        mySqlConnector = new MySqlConnector();
        tasks = FXCollections.observableArrayList();
        mySqlConnector.connect();
        ResultSet resultSet = mySqlConnector.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("task_id"));
            task.setTaskName(resultSet.getString("task_name"));
            task.setTaskDesc(resultSet.getString("task_desc"));

            tasks.addAll(task);
        }

        listListView.setItems(tasks);
        listListView.setCellFactory(CellController -> new CellController());
    }
}

