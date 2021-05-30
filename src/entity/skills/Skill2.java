package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

public class Skill2  extends Entity{
    private Image[] frames;
    private Animation skill2Animation;
    private boolean remove;
    private final int manaCost = 100;

    public  Skill2(TileMap tileMap) {
        super(tileMap);
        
        width = 70;
        height = 92;

        frames = new Image[4];
        frames[0]= new Image("Skill/Skill3-0.png");
        frames[1]= new Image("SKill/Skill3-1.png");
        frames[2]= new Image("SKill/Skill3-2.png");
        frames[3]= new Image("SKill/Skill3-3.png");

        setTimeLoad(1000);
        skill2Animation = new Animation();
        skill2Animation.setFrames(frames);
        skill2Animation.setDelay(100);
        setEntityBoxSize(70, 92);
    }
    
    public int getManaCost() {
    	return manaCost;
    }
    
    public boolean shouldRemove() {
    	return remove;
    }
    
    public void setRemove() {
    	remove = true;
    }

    public  void setPos(double x, double y){
        if (facingRight){
            super.setPos(x + 200, y);
        }else {
            super.setPos(x - 200, y);
        }
    }
    
    
    @Override
    public void tick() {
        if (skill2Animation.hasPlayedOnce()) {
        	remove = true;
        }
        skill2Animation.update();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        if(notOnScreen()) return;
        if(facingRight) {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX - xmap - width/2),
                    (posY - ymap - height),
                    width, height);
        }
        else {
            graphicsContext.drawImage(
                    skill2Animation.getImage(),
                    (posX - xmap + width/2),
                    (posY - ymap - height),
                    -width, height);
        }
    }
}