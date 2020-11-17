package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.model.Product;
import sample.model.ShoppingList;

import java.io.IOException;

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
    private Label fridgeLabel;

    @FXML
    private Button listAddButton;

    @FXML
    private Button listRemoveButton;

    @FXML
    private Label listLabel;

    @FXML
    private Label frdigeFridgeLabel;

    @FXML
    private ListView<Product> frdigeFridgeList;

    @FXML
    private Label frdigeShopListLabel;

    @FXML
    private ListView<ShoppingList> frdigeShopList;

    @FXML
    private ImageView fridgeImageBack;

    @FXML
    void initialize() throws IOException {






        fridgeImageBack.setOnMouseClicked(event -> {
            try {
                showMainPage(fridgeImageBack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showMainPage(ImageView image) throws IOException {
        image.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/mainPage.fxml"));

        loader.load();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
