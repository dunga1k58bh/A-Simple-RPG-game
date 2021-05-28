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

        frames = new Image[4];
        frames[0]= new Image("Skill/Skill3-0.png");
        frames[1]= new Image("SKill/Skill3-1.png");
        frames[2]= new Image("SKill/Skill3-2.png");
        frames[3]= new Image("SKill/Skill3-3.png");
        skill2Animation = new Animation();
        skill2Animation.setFrames(frames);
        skill2Animation.setDelay(100);
        setEntityBoxSize(70,92);

    }
    public void resetAnimation(){
        skill2Animation.setFrame(0);
    }

    public  void setPos(double x, double y){
        if (facingRight){
            super.setPos(x + 50, y);
        }else {
            super.setPos(x - 50, y);
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        if(facingRight) {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap+ 200 -skill2Animation.getImage().getWidth()/2),
                    (posY -ymap-  skill2Animation.getImage().getHeight()),
                    skill2Animation.getImage().getWidth(),
                    skill2Animation.getImage().getHeight());

        }
        else {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX -xmap -200 +skill2Animation.getImage().getWidth()/2),
                    (posY -ymap- skill2Animation.getImage().getHeight() ),
                    -skill2Animation.getImage().getWidth(),
                    skill2Animation.getImage().getHeight());

        }
    }

    @Override
    public void tick() {


        skill2Animation.update();

    }
}
