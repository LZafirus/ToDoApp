package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.model.Task;

public class ListController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<Task> listTasks;

    @FXML
    private TextField listTaskName;

    @FXML
    private TextField listTaskDesc;

    @FXML
    private Button listSaveButton;

    private ObservableList<Task> tasks;

    @FXML
    void initialize() {

        Task myTask = new Task();
        myTask.setTaskName("Car");
        myTask.setTaskDesc("Cleaning");

        tasks = FXCollections.observableArrayList();

        tasks.add(myTask);

        listTasks.setItems(tasks);
        listTasks.setCellFactory(CellController -> new CellController());

    }


}
