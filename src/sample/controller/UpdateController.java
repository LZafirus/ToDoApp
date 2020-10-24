package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateController {

    @FXML
    private TextField updateTaskName;

    @FXML
    private TextField updateTaskDesc;

    @FXML
    private Button updateSaveButton;

    @FXML
    private Label updateLabel;

    @FXML
    void initialize() {


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
