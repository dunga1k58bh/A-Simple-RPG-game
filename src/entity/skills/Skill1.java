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
        width = 170;
        height = 192;

        frames = new Image[6];
        frames[0]= new Image("Skill/Skill4-0.png");
        frames[1]= new Image("SKill/Skill4-1.png");
        frames[2]= new Image("SKill/Skill4-2.png");
        frames[3]= new Image("SKill/Skill4-3.png");
        frames[4]= new Image("SKill/Skill4-4.png");
        frames[5]= new Image("SKill/Skill4-5.png");

        setTimeLoad(3000);
        skill1Animation = new Animation();
        skill1Animation.setFrames(frames);
        skill1Animation.setDelay(200);
        setEntityBoxSize(170,192);
    }



    public  void setPos(double x, double y){
        if (facingRight){
            super.setPos(x + 5, y);
        }else {
            super.setPos(x -5, y);
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
            setMapPosittion();
        if(facingRight) {
            graphicsContext.drawImage(
                    skill1Animation.getImage(),
                    (posX -xmap - width/2),
                    (posY -ymap- height  ),
                    width,height);
        }
        else {
            graphicsContext.drawImage(
                    skill1Animation.getImage(),
                    (posX -xmap  +width/2),
                    (posY -ymap- height ),
                    -width,height);
        }
    }

    public boolean hasPlayedOnce() {
        return skill1Animation.hasPlayedOnce();
    }

    public void setPlayedOnce(boolean b){
        skill1Animation.setPlayedOnce(b);
    }
    @Override
    public void tick() {
         skill1Animation.update();
         if(facingRight) posX += 10;

         else posX -= 10;
    }
}
