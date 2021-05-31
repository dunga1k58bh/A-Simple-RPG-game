package entity.enemies;
import entity.Animation;
import entity.Player;
import entity.skills.LaserAttack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.Tile;
import tilemap.TileMap;



public class Boss extends Enemy{
    private int currentAnimation;
    private int nextAnimation;
    private int consercutiveAnimations;
    //target attack
    private Player player;

    //Action+Animation
    private final int IDLE = 0;
    private final int WALK = 1;
    private final int JUMP = 2;
    private final int LASERATTACK =3;
    private final int DEAD = 4;
   //Ä�á»©ng yÃªn
    private final  int LASERSWEPT = 5;
    private final  int HURT = 6;
    private int EXP;

    //attack action
    int random_int;

    private final LaserAttack laserAttack;
    private Animation[] animations;
    private int hardlevel;
    Image image ;

    public Boss(TileMap tm, int hardLevel) {
        super(tm);
        this.hardlevel = hardLevel;
        EXP = 150 * hardLevel;
        moveSpeed = 1;
        maxSpeed = 3 * hardLevel;
        fallSpeed = 0.2;
        HP = maxHP = 300 * hardLevel;
        damage = 50 * hardLevel;
        jumpStart =10;
        width = 220;
        height =190;
        xmin = width/2+1;
        xmax = tileMap.getWidth()-cwidth/2;
        ymin = height;
        ymax = tileMap.getHeight() -1;
        setEntityBoxSize(170,190);

        laserAttack = new LaserAttack(tileMap);

        animations = new Animation[7];
        for (int i =0;i<animations.length;i++){
            animations[i] = new Animation();
        }
        // the animations
        animations[HURT].setWidthHeight(220, 190);
        animations[HURT].setFrames("res/enemies/monster2/hurt.png", 5);
        animations[HURT].setDelay(25);
        animations[HURT].setMaxConsercutiveAnimation(1);
        //
        animations[WALK].setWidthHeight(212,189);
        animations[WALK].setFrames("res/enemies/monster2/walk.png",19,4,5);
        animations[WALK].setDelay(25);
        animations[WALK].setMaxConsercutiveAnimation(4);

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
        animations[JUMP].setDelay(75);
        animations[JUMP].setMaxConsercutiveAnimation(1);
        //
        animations[LASERSWEPT].setWidthHeight(221,224);
        animations[LASERSWEPT].setFrames("res/enemies/monster2/lasser_swept.png",121,11,11);
        animations[LASERSWEPT].setDelay(25);
        animations[LASERATTACK].setMaxConsercutiveAnimation(1);
        //
        animations[LASERATTACK].setWidthHeight(215,182);
        animations[LASERATTACK].setFrames("res/enemies/monster2/laser_attack.png",60,6,10);
        animations[LASERATTACK].setDelay(30);
        animations[LASERATTACK].setMaxConsercutiveAnimation(1);


        flinching =false;

        right = true;
        facing = 1;
        jumping = false;
        currentAnimation = WALK;
        nextAnimation=-1;//THat mean there no nex

    }
    private void getNextPosition() {
        // movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) dx = -maxSpeed;
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) dx = maxSpeed;
        } else {
            dx = 0;
        }
        if (jumping) {
            if (animations[currentAnimation].getFrame() == 0){    // Begin jump
                dy = -jumpStart;                                  //
            } else {
            if(jumpStart > 0)jumpStart -= 0.2;                    //jumpspeed --
            }
    }
        // falling
        if(falling) dy += fallSpeed;
        if(dy>0){                                                  //if falling
            fallSpeed+=0.01;                                       //fallspedd ++
        }
        if(onGround){                                              //ifOnground return the begin value
            fallSpeed = 0.2;
            jumpStart = 10;
        }

    }


    @Override
    public void tick() {
        falling = true;
        // update position
//        System.out.println("Current Animation "+currentAnimation);
//        System.out.println("Beinghit"+ beingHit);
        if(currentAnimation!= JUMP)  falling = true;
        if (HP < 0.2 *maxHP ){
            moveSpeed=1.5;
            maxSpeed=5;
            animations[WALK].setMaxConsercutiveAnimation(2);
        }

        // check flinching
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 1000) flinching = false;
        }
        if(posX-posYBegin>500){           // change direction if go too far from the begin Pos
            right=false;
            left=true;
            facing =-1;
        }else if(posX-posXBegin<-500){
            right=true;
            left=false;
            facing = 1;
        }
        setNextAnimation();


        if (isDead()) currentAnimation =DEAD;     // What we do with the current animation and action
        if (currentAnimation == WALK ){
            jumping = false;
            random_int = (int)(Math.random() * (6- hardlevel - 2 + 1) + 2); // random from 2 to 6,
            if (random_int<=3) nextAnimation = random_int;   //(??%) random jump or laser , game will harder if this percent is higher
            else nextAnimation=-1;
        }else if(currentAnimation == JUMP){
            jumping=true;
            nextAnimation=WALK;
        }else if(currentAnimation == IDLE){
           jumping =false;
           left = false;
           right= false;
           int random = (int)(Math.random() * (3 - 2 + 1) + 2);
//           System.out.println("Random"+random);
           nextAnimation = random;
        }else if(currentAnimation == LASERATTACK){
            if(24<=animations[LASERATTACK].getFrame()&&animations[LASERATTACK].getFrame()<36){
                laserAttack.setBeingUsed(true);
            }else{
                laserAttack.setBeingUsed(false);
                laserAttack.rsAnimation();
            }
            jumping = false;
            left = false;
            right = false;
            nextAnimation = WALK;
            flinching = true; // can be attack
        }else if(currentAnimation == HURT){
            left = false;
            right = false;
            jumping = false;
            nextAnimation = WALK;

        }else if(currentAnimation == DEAD){
            left = false;
            right=false;
            jumping =false;
            //threre no animations any more
        }
//      System.out.println("COncursiv"+consercutiveAnimations);
        getNextPosition();
        CheckTileMapCollision();
        posX += dx;
        posY += dy;
        laserAttack.setPos(posX,posY);
        laserAttack.ChangeDirection(facing);
        laserAttack.tick();
        // update animation
        if(currentAnimation!=DEAD||animations[DEAD].hasPlayedOnce()==false) {
            animations[currentAnimation].update();
        }else{
            animations[DEAD].setFrame(23);
        }
    }

    //set the next animation an action if this action is done;
    public void setNextAnimation(){
        if (beingHit) currentAnimation = HURT;
        if(animations[currentAnimation].hasPlayedOnce()==true) {
            beingHit = false;
            System.out.println(animations[currentAnimation].hasPlayedOnce());
            consercutiveAnimations++;
            animations[currentAnimation].setPlayedOnce(false);
            if(consercutiveAnimations >= animations[currentAnimation].getMaxConsercutiveAnimation()) {
                consercutiveAnimations=0;
                if (nextAnimation != -1) {
                    currentAnimation = nextAnimation;
                } else {
                        random_int = (int) Math.floor(Math.random() * 2);
                        if(currentAnimation!=random_int){
                            currentAnimation=random_int;
                    }

//                    System.out.println("Random" + currentAnimation);
                }
                if(player.getPosX()-posX>0){
                    right=true;
                    facing = 1;
                    left=false;
                }else{
                    right=false;
                    facing =-1;
                    left=true;
                }
            }
        }
    }
    public void setTarget(Player player){////Possition X of Player
      this.player=player;
    }
    public LaserAttack getLaserAttack(){
        return laserAttack;
    }

    @Override
    public void CheckTileMapCollision() {
        tileMap.setTile(0,10,12, Tile.ALLOW);
        super.CheckTileMapCollision();
        tileMap.setTile(0,10,12, Tile.BLOCK);
    }

    @Override
    public void render(GraphicsContext graphicsContext){
        setMapPosittion();

        graphicsContext.drawImage(
                animations[currentAnimation].getImage(),
                (posX -xmap+ ((double)animations[currentAnimation].getWidth()/2-15)*facing),
                (posY -ymap- (double)animations[currentAnimation].getHeight()+15),
                animations[currentAnimation].getWidth()*facing*(-1),
                animations[currentAnimation].getHeight());

        if(laserAttack.getBeingUsed())  laserAttack.render(graphicsContext);
    }
}
