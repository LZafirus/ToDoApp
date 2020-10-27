package sample.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {

    private FadeTransition fadeTransition;

    public Fade(Node node) {
        fadeTransition = new FadeTransition(Duration.millis(2000), node);
        fadeTransition.setFromValue(1f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(false);
    }

    public void fade() {fadeTransition.playFromStart();}
}
