package utils.animation.attackAnimation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.animation.Animation;


public class ranged extends Animation {
    public ranged() {
        setNumberOfStep(4);
        setInterval(3);
        upper.add(0, new Image("char/Small14-resources.assets-8747.png"));
        upper.add(1, new Image("char/Small15-resources.assets-12132.png"));
        upper.add(2, new Image("char/Small16-resources.assets-4604.png"));
        upper.add(3, new Image("char/Small17-resources.assets-12638.png"));
        for (int i = 0; i < getNumberOfStep(); i++) {
            lower.add(i, new Image("char/Small31-resources.assets-1259.png"));
        }
        for (int i = 0; i < getNumberOfStep(); i++) {
            head.add(i, new Image("char/Small32-resources.assets-4440.png"));
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY, double zzX, double zzY) {
        switch (animationStep) {
            case 0 -> {
                if (getFacing() == 1) {
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing - 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + -23.5, posY + -38.0, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + -13.5 - 6, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                } else {
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + 16.5, posY - 37.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + 27 - 9, posY - 66, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                }
            }
            case 1 -> {
                if (facing == 1) {
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + -13.5 - 6, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing - 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + -22.0, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                } else {
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + 27 - 9, posY - 66, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + 17.5, posY + -35.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                }
            }
            case 2 -> {
                if (facing == 1) {
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + -13.5 - 6, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing - 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + -15.5, posY + -32.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                } else {
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + 27 - 9, posY - 66, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + 11.5, posY + -34.0, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                }
            }
            case 3 -> {
                if (facing == 1) {
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing - 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + -26.0, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + -13.5 - 6, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                } else {
                    graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + 5, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + 22.5, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                    graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + 27 - 9, posY - 66, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                }
            }
        }
    }
}
