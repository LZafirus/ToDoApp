package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;
import sample.mysql.MySqlConnector;

import java.io.IOException;
import java.sql.SQLException;

public class CellController extends JFXListCell<Task> {

    @FXML
    private AnchorPane cellRootPane;

    @FXML
    private Label cellNameLabel;

    @FXML
    private Label cellDescLabel;

    @FXML
    private ImageView cellRemoveImage;

    @FXML
    private ImageView cellDoneImgae;

    private FXMLLoader fxmlLoader;

    private MySqlConnector mySqlConnector;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
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
            cellNameLabel.setText(item.getTaskName());
            cellDescLabel.setText(item.getTaskDesc());

            int taskId = item.getTaskId();

            cellRemoveImage.setOnMouseClicked(event -> {
                mySqlConnector = new MySqlConnector();
                try {
                    mySqlConnector.connect();
                    mySqlConnector.removeTask(AddItemController.userId, taskId);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                getListView().getItems().remove(getItem());
            });

            setText(null);
            setGraphic(cellRootPane);
        }
    }
}
