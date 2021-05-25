package entity.enemies;

import entity.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;



public class Monster2 extends Enemy{
    private int currentAnimation;
    private final  int HURT = 0;
    private final int WALK = 1;
    private final int DEAD = 2;


    private Animation animations[];
    public Monster2(TileMap tm) {
        super(tm);
        moveSpeed = 0.3;
        maxSpeed = 1;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;
        HP = maxHP = 3;
        damage = 1;

        width = 220;
        height = 190;
        animations = new Animation[5];
        for (int i =0;i<animations.length;i++){
            animations[i] = new Animation();
        }
        //
        animations[HURT].setWidthHeight(220, 190);
        animations[HURT].setFrames("res/enemies/monster2/hurt.png", 5);
        animations[HURT].setDelay(100);
        //
        animations[WALK].setWidthHeight(212,189);
        animations[WALK].setFrames("res/enemies/monster2/walk.png",19,4,5);
        animations[WALK].setDelay(50);

        //
        animations[DEAD].setWidthHeight(297,219);
        animations[DEAD].setFrames("res/enemies/monster2/death.png",24,4,6);
        animations[DEAD].setDelay(25);
        right = true;
        facingRight = true;
        currentAnimation = WALK;
    }
    private void getNextPosition() {
        // movement
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) dx = -maxSpeed;
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) dx = maxSpeed;
        }
        if (jumping){

        }
        // falling
        if(falling) dy += fallSpeed;

    }


    @Override
    public void tick() {
        // update position
        falling = true;
        getNextPosition();
        CheckTileMapCollision();
        posX += dx;
        posY += dy;
        // check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) flinching = false;
        }

        // if it hits a wall, go other direction
        if ((right && dx == 0) || posX - posXBegin > 100) {
            right = false;
            left = true;
            facingRight = false;
        } else if ((left && dx == 0) || posX - posXBegin < -100) {
            right = true;
            left = false;
            facingRight = true;
        }

        // update animation
        animations[currentAnimation].update();
    }

    @Override
    public void render(GraphicsContext graphicsContext){
        setMapPosittion();
//        if(notOnScreen()) return;
        if(facingRight) {
            graphicsContext.drawImage(
                    animations[currentAnimation].getImage(),
                    (posX -xmap+ (double)animations[currentAnimation].getWidth()/2-20),
                    (posY -ymap- (double)animations[currentAnimation].getHeight()+10),
                    -width,height);

        }else{
            graphicsContext.drawImage(
                    animations[currentAnimation].getImage(),
                    (posX -xmap- (double)animations[currentAnimation].getWidth()/2 ),
                    (posY -ymap- (double)animations[currentAnimation].getHeight()+10),
                    width,height);

        }
    }
}
