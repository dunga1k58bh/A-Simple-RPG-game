package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

public class Skill1  extends Entity{
    private Image[] frames;
    private Animation skill1Animation;
    private boolean remove;
    private final int manaCost = 200;
    private final int damage = 40;

    public  Skill1(TileMap tileMap) {
        super(tileMap);
        
        width = 85;
        height = 96;

        frames = new Image[6];
        frames[0]= new Image("Skill/Skill4-01.png");
        frames[1]= new Image("SKill/Skill4-11.png");
        frames[2]= new Image("SKill/Skill4-21.png");
        frames[3]= new Image("SKill/Skill4-31.png");
        frames[4]= new Image("SKill/Skill4-41.png");
        frames[5]= new Image("SKill/Skill4-51.png");

        setTimeLoad(1500);
        skill1Animation = new Animation();
        skill1Animation.setFrames(frames);
        skill1Animation.setDelay(200);
        setEntityBoxSize(85, 96);
    }
    
    public int getManaCost() {
    	return manaCost;
    }
    
    public int getDamage() {
    	return damage;
    }
    
    public boolean shouldRemove() {
    	return remove;
    }
    
    public void setRemove() {
    	remove = true;
    }

    public  void setPos(double x, double y){
        super.setPos(x + 10*facing, y);
    }
    
    
    @Override
    public void tick() {
         if(facing==1) posX += 10;
         else posX -= 10;
         if (skill1Animation.hasPlayedOnce()) {
        	 remove = true;
         }
         skill1Animation.update();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        if(notOnScreen()) return;
        graphicsContext.drawImage(skill1Animation.getImage(), (posX - xmap - width/2*facing), (posY - ymap - height), width*facing, height);
    }
}