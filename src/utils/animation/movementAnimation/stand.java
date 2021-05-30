package utils.animation.movementAnimation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.animation.Animation;

public class stand extends Animation {
    private final Image weaponTable;
    private double offset = 0;
    public stand() {
        facing = 1;
        setNumberOfStep(1);
        setInterval(1);
        weaponTable = new Image("char/Big1-resources.assets-9056.png");
        head.add(0, new Image("char/Small32-resources.assets-4440.png")); //standing
        lower.add(0, new Image("char/Small23-resources.assets-11235.png"));
        upper.add(0, new Image("char/Small5-resources.assets-1687.png"));
    }
    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY,double zzX, double zzY){
        graphicsContext.drawImage(weaponTable,0,69,40,85,posX + -22.5*facing,posY + -85.5 + offset,40*facing,85);
        graphicsContext.drawImage(lower.get(0), 0, 0, lower.get(0).getWidth(), lower.get(0).getHeight(), posX - lower.get(0).getWidth()/2*facing, posY - lower.get(0).getHeight(), lower.get(0).getWidth() * facing, lower.get(0).getHeight());
        graphicsContext.drawImage(upper.get(0), 0, 0, upper.get(0).getWidth(), upper.get(0).getHeight(), posX +(- 14 - 9)*facing, posY - 35 + offset, upper.get(0).getWidth() * facing, upper.get(0).getHeight());
        graphicsContext.drawImage(head.get(0), 0, 0, head.get(0).getWidth(), head.get(0).getHeight(), posX +(- 15 - 7)*facing, posY - 66 + offset, head.get(0).getWidth() * facing, head.get(0).getHeight()); //head
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }
}
