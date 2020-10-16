package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;

import java.io.IOException;

public class CellController extends JFXListCell<Task> {

    @FXML
    private AnchorPane cellRootPane;

    @FXML
    private Label cellNameLabel;

    @FXML
    private Label cellDescLabel;

    @FXML
    private ImageView cellDoneImgae;

    @FXML
    private ImageView cellRemoveImage;

    @FXML
    private Label cellDateLabel;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {

    }

    @Override
    public void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            cellNameLabel.setText(myTask.getTaskName());
            cellDescLabel.setText(myTask.getTaskDesc());

            setText(null);
            setGraphic(cellRootPane);
        }
    }

}
