package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;

import java.io.IOException;

public class DetailsController {


    @FXML
    private Label detailsWelcome;

    @FXML
    private Label detailsAddTask;

    @FXML
    private Label detailsLabelTwo;

    @FXML
    private Label detailsLabelThree;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    void initialize() {

        detailsAddTask.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker labelShaker = new Shaker(detailsAddTask);
            labelShaker.shake();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), detailsAddTask);

            detailsAddTask.relocate(0, 25);
            detailsAddTask.setOpacity(0);
            detailsAddTask.setText("Adding..");

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(2);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));
                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

}
