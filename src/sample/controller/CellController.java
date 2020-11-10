package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

    @FXML
    private Label cellDateLabel;

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
            cellDateLabel.setText(item.getDatecreated().toString());

            int taskId = item.getTaskId();


            cellDoneImgae.setOnMouseClicked(event -> {
                mySqlConnector = new MySqlConnector();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/update.fxml"));

                try {
                    mySqlConnector.connect();
                    loader.load();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdateController updateController = loader.getController();
                updateController.setTaskName(item.getTaskName());
                updateController.setUpdateTaskDesc(item.getTaskDesc());

                updateController.updateSaveButton.setOnAction(event1 -> {
                    try {
                        mySqlConnector.updateTask(updateController.getTaskName(), updateController.getTaskDesc(), item.getTaskId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
                stage.show();
            });

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
