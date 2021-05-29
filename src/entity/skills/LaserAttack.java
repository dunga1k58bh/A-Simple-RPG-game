package entity.skills;

import entity.Animation;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import tilemap.TileMap;




public class LaserAttack extends Entity {
    private Animation animation;
    private boolean beingused;
    public LaserAttack(TileMap tileMap){
        super(tileMap);


        animation = new Animation();
        animation.setWidthHeight(732,133);
        animation.setFrames("res/enemies/monster2/laser.png",24,2,6);
        animation.setDelay(50);
        facingRight = true;

    }
    public void ChangeDirection(boolean facingRight){
        this.facingRight = facingRight;
    }
    public void setPos(double x , double y){
        if(facingRight) {
            super.setPos(x + 80, y - 170);
        }else{
            super.setPos(x-80,y-170);
        }
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
        if(facingRight) {
            graphicsContext.drawImage(
                    animation.getImage(),
                    (posX -xmap ),
                    (posY -ymap ),
                    animation.getWidth(),animation.getHeight());
        }
        else {
            graphicsContext.drawImage(
                    animation.getImage(),
                    (posX -xmap ),
                    (posY -ymap ),
                    -animation.getWidth(),animation.getHeight());
        }

    }

    @Override
    public void tick() {
        animation.update();
    }

   }
