package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import tilemap.TileMap;




public class LaserAttack extends Entity {
    private Animation animation;
    private boolean beingused;
    private int damage;
    public LaserAttack(TileMap tileMap){
        super(tileMap);


        animation = new Animation();
        animation.setWidthHeight(732,133);
        animation.setFrames("res/enemies/monster2/laser.png",24,2,6);
        animation.setDelay(30);
        facing = 1;
        setEntityBoxSize(732,60);
        damage = 150;
    }
    public void ChangeDirection(int facing){
        this.facing = facing;
    }
    public void setPos(double x , double y){
        super.setPos(x + 80*facing, y - 90);
    }
    public  void setBeingUsed(boolean b){
        beingused = b;
    }
    public boolean getBeingUsed(){
        return beingused;
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        graphicsContext.drawImage(
                animation.getImage(),
                (posX -xmap ),
                (posY -ymap -70),
                animation.getWidth()*facing,animation.getHeight());
        //Draw a small dot at Monster position for simple debug
        double radius = 3;
        //Stoking
        graphicsContext.strokeOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);
        //Filling:
        graphicsContext.fillOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);

    }
    @Override
    public Rectangle2D getRectangle(){
        return new Rectangle2D((int) posX, (int) posY - cheight / 2*facing, cwidth, cheight);
    }
    public int getDamage(){
        return  damage;
    }
    public void rsAnimation(){
        animation.setFrame(0);
    }

    @Override
    public void tick() {
        animation.update();
    }

   }
