package utils.animation.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.animation.Animation;

public class bloodSplash extends Animation {
    private boolean activate = false;
    public bloodSplash() {
        facing = 1; //facing of attacker
        setNumberOfStep(5);
        setInterval(3);
        upper.add(0, new Image("particle/bloodSplash/bloodSplash1.png"));
        upper.add(1, new Image("particle/bloodSplash/bloodSplash0.png"));
        upper.add(2, new Image("particle/bloodSplash/bloodSplash2.png"));
    }

    public void activate() {
        activate = true;
    }

    public void deactivate() {
        activate = false;
    }

    @Override
    public void tick() {
        if (!activate) {
            return;
        }
        else {
            if (interval != 1 && interval != 0 && count % interval == 0 && count != 0){
                animationStep++;
            }
            else if (interval == 1) {
                animationStep++;
            }
            count ++;
            if (animationStep == getNumberOfStep()) {
                animationStep = 0;
                deactivate();
            }
        }
        System.out.println(activate);
    }

    @Override
    public void setFacing(int f) {
        if (activate) {
            //no op
        }
        else {
            this.facing = f;
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY, double zzX, double zzY){
        if (!activate) {
            return;
        }
        else {
            switch (animationStep) {
                case 0 -> {
                    graphicsContext.drawImage(upper.get(animationStep),0,0, upper.get(animationStep).getWidth(), upper.get(animationStep).getHeight(),posX,posY,upper.get(animationStep).getWidth()*facing,upper.get(animationStep).getHeight());
                }
                case 1 -> {
                    graphicsContext.drawImage(upper.get(animationStep),0,0, upper.get(animationStep).getWidth(), upper.get(animationStep).getHeight(),posX,posY,upper.get(animationStep).getWidth()*facing,upper.get(animationStep).getHeight());
                }
                case 2 -> {
                    graphicsContext.drawImage(upper.get(animationStep),0,0, upper.get(animationStep).getWidth(), upper.get(animationStep).getHeight(),posX,posY,upper.get(animationStep).getWidth()*facing,upper.get(animationStep).getHeight());
                }
            }
        }
    }
}
