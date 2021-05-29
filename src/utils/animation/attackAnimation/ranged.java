package utils.animation.attackAnimation;

import javafx.scene.image.Image;
import utils.animation.Animation;


public class ranged extends Animation {
    public ranged() {
        setNumberOfStep(4);
        setSelfLock(false);
        setInterval(3);
        getUpper().add(0, new Image("char/Small14-resources.assets-8747.png"));
        getUpper().add(1, new Image("char/Small15-resources.assets-12132.png"));
        getUpper().add(2, new Image("char/Small16-resources.assets-4604.png"));
        getUpper().add(3, new Image("char/Small17-resources.assets-12638.png"));
        for (int i = 0; i < getNumberOfStep();i++) {
            getLower().add(i, new Image("char/Small31-resources.assets-1259.png"));
        }
        for (int i = 0; i < getNumberOfStep(); i++) {
            getHead().add(i, new Image("char/Small32-resources.assets-4440.png"));
        }
    }
}
