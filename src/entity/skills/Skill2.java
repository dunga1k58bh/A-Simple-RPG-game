package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

import java.util.ArrayList;

public class Skill2  extends Entity{
    private Image[] frames;
    private Animation skill2Animation;

    public  Skill2(TileMap tileMap) {
        super(tileMap);
        width = 70;
        height = 92;

        frames = new Image[3];
        frames[0]= new Image("Skill/Skill2-1.png");
        frames[1]= new Image("SKill/Skill2-2.png");
        frames[2]= new Image("SKill/Skill2-3.png");
        skill2Animation = new Animation();
        skill2Animation.setFrames(frames);
        skill2Animation.setDelay(120);
    }
    public void resetAnimation(){
        skill2Animation.setFrame(0);
    }



    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        if(facingRight) {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap+ 100 - width/2),
                    (posY -ymap- height  ),
                    width,height);

            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap+ 200 - width/2),
                    (posY -ymap- height  ),
                    width,height);

            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap+ 300 - width/2),
                    (posY -ymap- height  ),
                    width,height);

        }
        else {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap -100 +width/2),
                    (posY -ymap- height ),
                    -width,height);
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap -200 +width/2),
                    (posY -ymap- height ),
                    -width,height);
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap -300 +width/2),
                    (posY -ymap- height ),
                    -width,height);
        }
    }

    @Override
    public void tick() {
        skill2Animation.update();
    }
}
