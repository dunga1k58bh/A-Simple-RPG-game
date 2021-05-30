package utils.animation.movementAnimation;

import javafx.scene.canvas.GraphicsContext;
import utils.animation.Animation;

public class stand extends Animation {
    public stand() {
        setNumberOfStep(1);
        setSelfLock(false);
        setInterval(3);
    }
    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY,double zzX, double zzY){

    }
}
