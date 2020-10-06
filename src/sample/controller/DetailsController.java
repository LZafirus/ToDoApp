package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.animations.Shaker;

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
    void initialize(){

        detailsAddTask.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker labelShaker = new Shaker(detailsAddTask);
            labelShaker.shake();
            System.out.println("adding clicked");
        });

    }

}
