package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Personal To-Do Machine");

        FlowPane root = new FlowPane(Orientation.VERTICAL, 5, 5);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}