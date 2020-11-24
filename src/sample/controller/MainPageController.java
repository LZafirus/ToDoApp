package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    public static int userId;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Button detailsAddTask;

    @FXML
    private Button detailsTaskList;

    @FXML
    private Button detailsFridgeAdd;

    @FXML
    private Button detailsFridgeList;

    @FXML
    private Label detailsWelcome;

    @FXML
    private Button detailsClose;

    @FXML
    void initialize() {

        detailsAddTask.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addItemForm.fxml"));
            try {
                loader.load();
                AddItemFormController.userId = getUserId();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            closingWindow(detailsAddTask);

        });

        detailsTaskList.setOnAction(event -> {
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

            closingWindow(detailsTaskList);
        });

        detailsClose.setOnMouseClicked(event -> {
            closingWindow(detailsClose);
        });

        detailsFridgeList.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/fridgeList.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            closingWindow(detailsFridgeList);
        });
    }

    public void closingWindow(Button button){
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("user id is " + this.userId);
    }

    public int getUserId() {
        return this.userId;
    }
}
