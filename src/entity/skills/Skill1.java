package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

import java.util.ArrayList;

public class Skill1  extends Entity{
    private Image[] images;
    private Animation skill1;

    public  Skill1(TileMap tileMap) {
        super(tileMap);
        width = 70;
        height = 92;

        images = new Image[3];
        images[0]= new Image("Skill/Skill1-1.png");
        images[1]= new Image("SKill/Skill1-2.png");
        images[2]= new Image("SKill/Skill1-3.png");
        skill1 = new Animation();
        skill1.setFrames(images);
        skill1.setDelay(300);
    }
    public void resetAnimation(){
        skill1.setFrame(0);
    }



    @Override
    public void render(GraphicsContext graphicsContext) {
            setMapPosittion();
        if(facingRight) {
            graphicsContext.drawImage(
                    skill1.getImage(),
                    (posX -xmap+ 200 - width/2),
                    (posY -ymap- height  ),
                    width,height);
        }
        else {
            graphicsContext.drawImage(
                    skill1.getImage(),
                    (posX -xmap -200 +width/2),
                    (posY -ymap- height ),
                    -width,height);
        }
    }

    @Override
    public void tick() {
         skill1.update();
    }
}
