package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    }

    void setName(String name) {
        detailsWelcome.setText("Welcome " + name);
    }


}
