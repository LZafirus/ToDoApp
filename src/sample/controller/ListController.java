package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.model.Task;

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

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        Task mytask = new Task();
        mytask.setTaskName("Car");
        mytask.setTaskDesc("Cleaning");

        tasks = FXCollections.observableArrayList();

        tasks.add(mytask);

        listListView.setItems(tasks);
        listListView.setCellFactory(CellController -> new CellController());



    }
}

