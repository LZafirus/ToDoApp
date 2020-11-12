package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.animations.Fade;
import sample.animations.Shaker;

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
    void initialize() {

        detailsAddTask.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker labelShaker = new Shaker(detailsAddTask);
            labelShaker.shake();

            detailsAddTask.relocate(0, 25);
            detailsAddTask.setOpacity(0);
            detailsAddTask.setText("Adding..");

            Fade fadeTransition = new Fade(detailsAddTask);
            fadeTransition.fade();


            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));
                //TODO search more this one to understand
                AddItemFormController.userId = getUserId();

                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("user id is " + this.userId);
    }

    public int getUserId() {
        return this.userId;
    }
}
