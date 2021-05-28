package entity.enemies;
import entity.Animation;
import entity.Player;
import entity.skills.LaserAttack;
import entity.skills.Skill1;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;



public class Monster2 extends Enemy{
    private int currentAnimation;
    private int nextAnimation;
    private int consercutiveAnimations;
    //target attack
    private Player player;

    //Action+Animation
    private final int IDLE = 0;
    private final int JUMP = 1;
    private final int WALK = 2;
    private final int LASERATTACK =3;
    private final int DEAD = 4;
   //Đứng yên
    private final  int LASERSWEPT = 5;
    private final  int HURT = 6;


    //attack action
    int random_int;
    private boolean laserattack;

    private LaserAttack laserAttack;
    private Skill1 skill11;
    private Animation[] animations;

    Image image ;

    public Monster2(TileMap tm) {
        super(tm);
        moveSpeed = 1;
        maxSpeed = 3;
        fallSpeed = 3;
        maxFallSpeed = 10.0;
        HP = maxHP = 100;
        damage = 1;
        jumpStart =3;
        stopJumpSpeed=5;
        width = 220;
        height =190;
        xmin = width/2+1;
        xmax = tileMap.getWidth()-cwidth/2;
        ymin = height;
        ymax = tileMap.getHeight() -1;
        setEntityBoxSize(170,190);

        laserAttack = new LaserAttack(tileMap);
        skill11 = new Skill1(tileMap);

        animations = new Animation[7];
        for (int i =0;i<animations.length;i++){
            animations[i] = new Animation();
        }
        //
        animations[HURT].setWidthHeight(220, 190);
        animations[HURT].setFrames("res/enemies/monster2/hurt.png", 5);
        animations[HURT].setDelay(100);
        animations[HURT].setMaxConsercutiveAnimation(1);
        //
        animations[WALK].setWidthHeight(212,189);
        animations[WALK].setFrames("res/enemies/monster2/walk.png",19,4,5);
        animations[WALK].setDelay(25);
        animations[WALK].setMaxConsercutiveAnimation(6);

        //
        animations[DEAD].setWidthHeight(297,219);
        animations[DEAD].setFrames("res/enemies/monster2/death.png",24,4,6);
        animations[DEAD].setDelay(25);
        animations[DEAD].setMaxConsercutiveAnimation(1);
        //
        animations[IDLE].setWidthHeight(214,182);
        animations[IDLE].setFrames("res/enemies/monster2/idle.png",48,6,8);
        animations[IDLE].setDelay(25);
        animations[IDLE].setMaxConsercutiveAnimation(1);
        //
        animations[JUMP].setWidthHeight(235,259);
        animations[JUMP].setFrames("res/enemies/monster2/jump.png",24,4,6);
        animations[JUMP].setDelay(50);
        animations[JUMP].setMaxConsercutiveAnimation(1);
        //
        animations[LASERSWEPT].setWidthHeight(221,224);
        animations[LASERSWEPT].setFrames("res/enemies/monster2/lasser_swept.png",121,11,11);
        animations[LASERSWEPT].setDelay(25);
        animations[LASERATTACK].setMaxConsercutiveAnimation(1);
        //
        animations[LASERATTACK].setWidthHeight(215,182);
        animations[LASERATTACK].setFrames("res/enemies/monster2/laser_attack.png",60,6,10);
        animations[LASERATTACK].setDelay(25);
        animations[LASERATTACK].setMaxConsercutiveAnimation(1);


        flinching =false;

        right = true;
        facingRight = true;
        jumping = false;
        currentAnimation = WALK;
        nextAnimation=-1;//THat mean there no nex

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
        }else{
             dx=0;
        }
        if (jumping){
            dy -=jumpStart;
            if (dy<=-stopJumpSpeed) dy = -stopJumpSpeed;
        }
        // falling
        if(falling) dy += fallSpeed;
    }


    @Override
    public void tick() {
        // update position
        System.out.println("Current Animation "+currentAnimation);
        System.out.println("FacingRight"+ facingRight);
        getNextPosition();
        CheckTileMapCollision();
        posX += dx;
        posY += dy;
        // check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) flinching = false;
        }
        if(posX-posYBegin>300){
            right=false;
            left=true;
            facingRight=false;
        }else if(posX-posXBegin<-300){
            right=true;
            left=false;
            facingRight=true;
        }
        setNextAnimation();

        if (currentAnimation == WALK ){
            jumping = false;
            nextAnimation=-1;
        }else if(currentAnimation == JUMP){
            jumping=true;
            if(animations[JUMP].getFrame()>=(int)animations[JUMP].getLength()/2) {
                jumping=false;
                falling=true;
            }
            nextAnimation=WALK;
        }else if(currentAnimation == IDLE){
           jumping =false;
           left = false;
           right= false;
           nextAnimation = LASERATTACK;
        }else if(currentAnimation == LASERATTACK){
            jumping = false;
            left = false;
            right = false;
            nextAnimation = WALK;
        }
        System.out.println("COncursiv"+consercutiveAnimations);

        laserAttack.setPos(posX,posY);
        laserAttack.ChangeDirection(facingRight);
        laserAttack.tick();
        // update animation
        animations[currentAnimation].update();
    }

    public void setNextAnimation(){
        if(animations[currentAnimation].hasPlayedOnce()==true) {
            consercutiveAnimations++;
            animations[currentAnimation].setPlayedOnce(false);
            if(consercutiveAnimations == animations[currentAnimation].getMaxConsercutiveAnimation()) {
                consercutiveAnimations=0;
                if (nextAnimation != -1) {
                    currentAnimation = nextAnimation;
                } else {
                        random_int = (int) Math.floor(Math.random() * 2);
                        if(currentAnimation!=random_int){
                            currentAnimation=random_int;
                    }

                    System.out.println("Random" + currentAnimation);
                }
                if(player.getPosX()-posX>0){
                    right=true;facingRight=true;
                    left=false;
                }else{
                    right=false;facingRight=false;
                    left=true;
                }
            }
        }
    }
    public void setTarget(Player player){////Possition X of Player
      this.player=player;
    }



    @Override
    public void render(GraphicsContext graphicsContext){
        setMapPosittion();
//        if(notOnScreen()) return;

        if(facingRight) {
            graphicsContext.drawImage(
                    animations[currentAnimation].getImage(),
                    (posX -xmap+ (double)animations[currentAnimation].getWidth()/2-15),
                    (posY -ymap- (double)animations[currentAnimation].getHeight()+15),
                    -animations[currentAnimation].getWidth(),
                    animations[currentAnimation].getHeight());

        }else{
            graphicsContext.drawImage(
                    animations[currentAnimation].getImage(),
                    (posX -xmap- (double)animations[currentAnimation].getWidth()/2 ),
                    (posY -ymap- (double)animations[currentAnimation].getHeight()+15),
                    animations[currentAnimation].getWidth(),
                    animations[currentAnimation].getHeight());

        }
        //Draw a small dot at Monster position for simple debug
        double radius = 3;
        //Stoking
        graphicsContext.strokeOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);
        //Filling:
        graphicsContext.fillOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);
        if(currentAnimation == LASERATTACK)  laserAttack.render(graphicsContext);
    }
}
