package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {

    @FXML
    private TextField updateTaskName;

    @FXML
    private TextField updateTaskDesc;

    @FXML
    public Button updateSaveButton;

    @FXML
    private Label updateLabel;

    @FXML
    void initialize() {

        updateSaveButton.setOnMouseClicked(event -> {
            closingWindow(updateSaveButton);

        });

    }

    public void closingWindow(Button button){
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();
    }

    public void setTaskName(String taskName) {
        this.updateTaskName.setText(taskName);
    }

    public String getTaskName() {
        return this.updateTaskName.getText().trim();
    }

    public void setUpdateTaskDesc(String taskDesc) {
        this.updateTaskDesc.setText(taskDesc);
    }

    public String getTaskDesc() {
        return this.updateTaskDesc.getText().trim();
    }

}
