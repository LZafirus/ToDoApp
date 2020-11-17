package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FridgeListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField frdigeNameField;

    @FXML
    private TextField frdigeQuantityField;

    @FXML
    private Button frdigeAddButton;

    @FXML
    private Button frdigeRemoveButton;

    @FXML
    private Button frdigeAddButton1;

    @FXML
    private Button frdigeRemoveButton1;

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
