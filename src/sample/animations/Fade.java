package sample.animations;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Fade {

    private FadeTransition fadeTransition;

    public Fade(FadeTransition fadeTransition) {
        fadeTransition = new FadeTransition(Duration.millis(2000), fadeTransition.getNode());

        fadeTransition.setFromValue(1f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(false);
    }

    public void fade() {fadeTransition.play();}

}
