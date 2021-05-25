package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

import java.util.ArrayList;

public class Skill1  extends Entity{
    private Image[] frames;
    private Animation skill1Animation;

    public  Skill1(TileMap tileMap) {
        super(tileMap);
        width = 70;
        height = 92;

        frames = new Image[3];
        frames[0]= new Image("Skill/Skill1-1.png");
        frames[1]= new Image("SKill/Skill1-2.png");
        frames[2]= new Image("SKill/Skill1-3.png");
        skill1Animation = new Animation();
        skill1Animation.setFrames(frames);
        skill1Animation.setDelay(300);
    }
    public void resetAnimation(){
        skill1Animation.setFrame(0);
    }



    @Override
    public void render(GraphicsContext graphicsContext) {
            setMapPosittion();
        if(facingRight) {
            graphicsContext.drawImage(
                    skill1Animation.getImage(),
                    (posX -xmap+ 200 - width/2),
                    (posY -ymap- height  ),
                    width,height);
        }
        else {
            graphicsContext.drawImage(
                    skill1Animation.getImage(),
                    (posX -xmap -200 +width/2),
                    (posY -ymap- height ),
                    -width,height);
        }
    }

    @Override
    public void tick() {
         skill1Animation.update();
    }
}
