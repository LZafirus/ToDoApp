package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sample.mysql.ConstDataBase;
import sample.mysql.MySqlConnector;

import javax.print.attribute.ResolutionSyntax;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FridgeListController {

    @FXML
    private TextField fridgeNameField;

    @FXML
    private TextField fridgeQuantityField;

    @FXML
    private ImageView fridgeImageBack;

    @FXML
    private Button fridgeAddButton;

    @FXML
    private Button fridgeRemoveButton;

    @FXML
    private Label fridgeLabel;

    @FXML
    private Button listAddButton;

    @FXML
    private Button listRemoveButton;

    @FXML
    private Label listLabel;

    @FXML
    private Label fridgeFridgeLabel;

    @FXML
    private ListView<Product> fridgeFridgeList;

    @FXML
    private Label fridgeShopListLabel;

    @FXML
    private ListView<ShoppingList> fridgeShopList;


    private ObservableList<Product> products;
    private ObservableList<ShoppingList> listForShop;

    private MySqlConnector connector;

    @FXML
    void initialize() throws IOException, SQLException, ClassNotFoundException {

        connector = new MySqlConnector();
        connector.connect();
        products = FXCollections.observableArrayList();
        listForShop = FXCollections.observableArrayList();

        ResultSet resultSet = connector.getProductsByUser(MainPageController.userId);
        while (resultSet.next()) {
            Product product = new Product();
            product.setProduct_id(resultSet.getInt("product_id"));
            product.setName(resultSet.getString("name"));
            product.setQuantity(resultSet.getString("quantity"));
            product.setUser_id(resultSet.getInt("user_id"));
            products.addAll(product);
        }
        fridgeFridgeList.setItems(products);

        ResultSet resultSet1 = connector.getShoppingByUser(MainPageController.userId);
        while (resultSet1.next()) {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setShoppingId(resultSet1.getInt("product_id"));
            shoppingList.setName(resultSet1.getString("name"));
            shoppingList.setQuantity(resultSet1.getString("quantity"));
            shoppingList.setUserId(resultSet1.getInt("user_id"));
            listForShop.addAll(shoppingList);
        }
        fridgeShopList.setItems(listForShop);


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
