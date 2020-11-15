package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class FridgeListController {

    @FXML
    private TextField frdigeNameField;

    @FXML
    private TextField frdigeQuantityField;

    @FXML
    private Button frdigeAddButton;

    @FXML
    private Button frdigeRemoveButton;

    @FXML
    private ToggleButton fridgeToggleButton;

    @FXML
    private Label frdigeFridgeLabel;

    @FXML
    private ListView<?> frdigeFridgeList;

    @FXML
    private Label frdigeShopListLabel;

    @FXML
    private ListView<?> frdigeShopList;

    @FXML
    void initialize() {


    }
}
