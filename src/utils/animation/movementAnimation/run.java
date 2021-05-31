package utils.animation.movementAnimation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.animation.Animation;

public class run extends Animation {
    private final Image weaponTable;
    private double offset = 0;

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public run() {
        facing = 1;
        setNumberOfStep(5);
        setInterval(5);

        weaponTable = new Image("char/Big1-resources.assets-9056.png");

        head.add(0, new Image("char/Small33-resources.assets-14326.png")); //running

        upper.add(0, new Image("char/Small6-resources.assets-12601.png"));
        upper.add(1, new Image("char/Small7-resources.assets-12452.png"));
        upper.add(2, new Image("char/Small8-resources.assets-1354.png"));
        upper.add(3, new Image("char/Small9-resources.assets-7446.png"));
        upper.add(4, new Image("char/Small10-resources.assets-8065.png")); //Small11-resources.assets-7160.png

        lower.add(0, new Image("char/Small24-resources.assets-12840.png"));
        lower.add(1, new Image("char/Small25-resources.assets-6123.png"));
        lower.add(2, new Image("char/Small26-resources.assets-6747.png"));
        lower.add(3, new Image("char/Small27-resources.assets-6734.png"));
        lower.add(4, new Image("char/Small28-resources.assets-5528.png"));
    }

    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY, double zzX, double zzY) {
        switch (animationStep%numberOfStep) {
            case 0 -> { //Draw
                graphicsContext.drawImage(weaponTable, 0, 69, 40, 85, posX + (-22.5 + 2)*facing, posY + -85.5 + offset, 40 * facing, 85);
                graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX + -15*facing, posY - 68 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX - 14*facing, posY - 35 + offset, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
            }
            case 1 -> {
                graphicsContext.drawImage(weaponTable, 0, 69, 40, 85, posX + -20.5*facing, posY + -85.5 + offset, 40 * facing, 85);
                graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX - 15*facing, posY - 68 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2*facing + 3*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX - 19*facing, posY - 35 + offset, getImageThisFrame("upper").getWidth()*facing, getImageThisFrame("upper").getHeight());
            }
            case 2 -> {
                graphicsContext.drawImage(weaponTable, 0, 69, 40, 85, posX + (-22.5 + 2)*facing, posY + -85.5 + offset, 40 * facing, 85);
                graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX - 15*facing, posY - 68 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2*facing + 2*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX - 10*facing, posY - 33 + offset, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
            }
            case 3 -> {
                graphicsContext.drawImage(weaponTable, 0, 69, 40, 85, posX + -22.5*facing + 2*facing, posY + -85.5 + offset, 40 * facing, 85);
                graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX - 15*facing, posY - 68 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2*facing + 4*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX - 13*facing, posY - 33 + offset, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
            }
            case 4 -> {
                graphicsContext.drawImage(weaponTable, 0, 69, 40, 85, posX + -22.5*facing + 2*facing, posY + -85.5 + offset, 40 * facing, 85);
                graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX - 15*facing, posY - 68 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2*facing + 4*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX - 9*facing, posY - 33 + offset, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
            }
        }
    }
}
