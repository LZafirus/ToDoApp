package sample.controller;

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

public class ListController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<String> listListView;
    ObservableList<String> listView = FXCollections.observableArrayList(
            "dawid",
            "test",
            "test1"
    );

    @FXML
    private TextField listTaskName;

    @FXML
    private TextField listTaskDesc;

    @FXML
    private Button listSaveButton;

    @FXML
    void initialize() {

        listListView.setItems(listView);

    }

}
