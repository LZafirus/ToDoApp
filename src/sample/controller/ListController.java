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

        listListView.setCellFactory(param -> new JFXCell());

    }

    static class JFXCell extends JFXListCell<String> {

        HBox hBox = new HBox();
        Button helloButton = new Button("hei");
        Label task = new Label();

        Pane pane = new Pane();

        Image icon = new Image("/sample/assets/task_add.png");
        ImageView imageView = new ImageView(icon);

        public JFXCell() {
            super();

            hBox.getChildren().addAll(imageView, task, helloButton);
            hBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(String taskName, boolean empty) {
            super.updateItem(taskName, empty);
            setText(null);
            setGraphic(null);

            if (taskName != null && !empty) {
                task.setText(taskName);
                setGraphic(hBox);
            }
        }


    }

}
