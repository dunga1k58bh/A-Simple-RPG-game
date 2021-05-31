package entity;

import entity.skills.AttackBox;
import entity.skills.Skill1;
import entity.skills.Skill2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import utils.Key;
import utils.animation.Animation;
import utils.animation.attackAnimation.meleeGreateSword;
import utils.animation.movementAnimation.run;
import utils.animation.movementAnimation.stand;
import utils.animation.particle.bloodSplash;
import utils.attackType;

import java.util.ArrayList;

public class Player extends Entity{
    private final Image big1 = new Image("char/Big1-resources.assets-9056.png"); //outfit and miscellaneous stuffs
    private final ArrayList<Image> jumpLowerBody = new ArrayList<Image>();
    private final ArrayList<Image> jumpUpperBody = new ArrayList<Image>();
    private final Image jumpHead;

    private int animationStep2 = 0;
    private int animationStep3 = 0;

    private int phs = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int offset = 0;

    //Skills
    private Skill1 skill1;
    private Skill2 skill2;
    private long skill1Timer;
    private long skill2Timer;
    private long attackTimer;
    private boolean skill1Lock;
    private boolean skill2Lock;
    private boolean attackLock;
    private AttackBox box;


    private int jump = 0;  //this variable is set to 1 whenever player is on-air
    private double dt = 1; //pseudo time between frames

    //velocity of player when moving
    private final double velocityX = 5;
    private final double velocityY = -15;
    private double currentVelocityX = 0;
    private double currentVelocityY = 0;
    private final double accelerationX = 0.4;
    private final double accelerationY = 0.7;
    private int runningDirection = 0; //0 = not running, 1 = right, -1 = left
    private int lastRunningDirection = 0;
    //private int facing = 1;

    private double zzY = 0;
    private double zzX = 0;
    private boolean lock = false;
    private boolean lock2 = false;
    private Key key= new Key();

    // max HP, MP, level ...
    public int maxHP, maxMP, level;
    public int curEXP, curMaxEXP, level1EXP, level2EXP, level3EXP;
    public int HPpotNum, MPpotNum;
    public int HPinc, MPinc;
    private int atkType = attackType.RANGED;

    private Animation currentAttackAnimation = new meleeGreateSword();
    private Animation currentStandAnimation = new stand();
    private Animation currentRunAnimation = new run();

    private boolean lock3;
    private boolean debug;
    
    public Player() {
        facing = 1;
        attackLock = false;
    	skill1Lock = false;
    	skill2Lock = false;
    	HPinc = 50;
    	MPinc = 100;
    	HPpotNum = 5;
    	MPpotNum = 5;
    	curEXP = 0;
    	level1EXP = 100;
    	level2EXP = 150;
    	level3EXP = 250;
    	curMaxEXP = level1EXP;
    	level = 1;
    	HP = 500;
    	MP = 500;
    	maxHP = 500;
    	maxMP = 500;
    	
        setPosX(500);
        setPosY(300);
        setEntityBoxSize(30,50);// Dòng này thêm kích cỡ nhân vật (E mới ước chừng thôi) ENGLISH PLS? stupid

        //jump animation contains 3 phases:
        //Phase 1: from ground to highest-air (Example: https://drive.google.com/file/d/12mnGkDOkYaG6wl46t_2hhXUM60TrX_NP/view?usp=sharing)
        //Phase 2: air-rolling (Example: https://drive.google.com/file/d/19lhi_kn1RBmVkM7ksC5QPMG8mFIGK23E/view?usp=sharing)
        //Phase 3: falling (Example: https://drive.google.com/file/d/1HrBG9Pz_xFWDgIKE4c9VnVCrhyeICt2Y/view?usp=sharing)
        jumpHead = new Image("char/Small32-resources.assets-4440.png");

        jumpUpperBody.add(0, new Image("char/Small11-resources.assets-7160.png")); //Phase 1
        jumpUpperBody.add(1, new Image("char/Small1-resources.assets-7704.png")); //*
        jumpUpperBody.add(2, new Image("char/Small2-resources.assets-6576.png")); //* Phase 2
        jumpUpperBody.add(3, new Image("char/Small3-resources.assets-11828.png")); //*
        jumpUpperBody.add(4, new Image("char/Small4-resources.assets-5934.png")); //*
        jumpUpperBody.add(5, new Image("char/Small12-resources.assets-4133.png")); //Phase 3

        jumpLowerBody.add(0,new Image("char/Small29-resources.assets-15129.png")); //Phase 1
        jumpLowerBody.add(1,new Image("char/Small30-resources.assets-11864.png")); //Phase 3

    }
    public void setMaxHP(int hp) {
    	maxHP = hp;
    }
    public void setMaxMP(int mp) {
    	maxHP = mp;
    }

    public Player(int x, int y) {
        this();
        setPosX(x);
        setPosY(y);
    }

    public int getAttackType() {
        return atkType;
    }
    
    public Skill1 getSkill1(){
        return skill1;
    }
    
    public Skill2 getSkill2(){
        return skill2;
    }
    
    public Key getKey() {
    	return key;
    }
    
    public AttackBox getBox() {
    	return box;
    }
    
    public boolean getLock3() {
    	return lock3;
    }

    @Override
    public void tick() {
        //tick

        //player cant be attack if flinching is true
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 1000) flinching = false; // player cant be attacked in 1000ms if he has been attacked before
        }

        if (!onGround && !lock) { //falling
            falling = true;
        }

        if (onGround) {
            falling = false;
            lock = false;
            lock2 = false;
            currentVelocityY = 0;
        }
        if (key.attack == 1 && !lock3) {
            currentVelocityY = 0;
            lock3 = true;
        }
        if (lock3) {
            currentAttackAnimation.tick();
            if (currentAttackAnimation.getAnimationStep() % currentAttackAnimation.getNumberOfStep() == 0  && currentAttackAnimation.getAnimationStep() !=0) {
                currentAttackAnimation.setAnimationStep(0);
                currentAttackAnimation.setCount(0);
                lock3 = false;
            }
            return;
        }
        if (lock) { //lock = true -> start jump animation
            if (dy < 0) {  //from ground to top phs = 0 -> animation phase 1
                phs = 0;
            }
            else if ((currentVelocityY + dt * accelerationY * 1000) > 0 && !lock2) { //on top
                lock2 = true;
                phs = 1;
            }
            if (phs == 1) {
                count3 ++;
            }
            if (count3 % 2 == 1) {
                animationStep3 ++;
            }
            if (animationStep3 % 6 == 0 && animationStep3 != 0) {
                animationStep3 = 0;
                count3 = 0;
                phs = 2; //phs = 2 when complete phase 2 animation, phs = 2 -> animation phase 3
            }
        }

        if (key.up == 1 && currentVelocityY >= 0 && onGround && !lock) {
            lock = true;
            currentVelocityY = velocityY;
        }

        runningDirection = key.right - key.left;
        if (runningDirection == 1) {
            facing = runningDirection;
            lastRunningDirection = runningDirection;
            currentVelocityX = velocityX;
            dx= dt * Math.max(velocityX,0);
            currentRunAnimation.setFacing(facing);
            currentRunAnimation.tick();
        } else if (runningDirection == -1) {
            facing = runningDirection;
            lastRunningDirection = runningDirection;
            currentVelocityX = velocityX;
            dx =- dt * Math.max(velocityX,0);
            currentRunAnimation.setFacing(facing);
            currentRunAnimation.tick();
        }
        else if (runningDirection == 0) {
            currentVelocityX -= accelerationX *dt;
            dx = lastRunningDirection * dt * Math.max(currentVelocityX,0);
            currentRunAnimation.refresh();
        }

        currentVelocityY += dt * accelerationY;
        dy = dt * currentVelocityY;
        CheckTileMapCollision();
        posX+=dx;
        posY+=dy;

        //for the head shaking animation
        count2++;
        if (count2 % 13 == 0 && count2!=0){
            count2 = 0;
            animationStep2 ++;
            offset = animationStep2%2*3;
            ((run) currentRunAnimation).setOffset(offset);
            ((stand)currentStandAnimation).setOffset(offset);
        }
        
        //Update Skill
        if (key.skill1 == 1) {
        	if (!skill1.shouldRemove()) {
        		skill1.tick();
        	}
        	else {
        		key.skill1 = 0;
        	}
        }
        if (key.skill2 == 1) {
        	if (!skill2.shouldRemove()) {
        		skill2.tick();
        	}
        	else {
        		key.skill2 = 0;
        	}
        }
    }

    //private "default skin"
    @Override
    public void render(GraphicsContext graphicsContext) {
        //Đoạn này hiểu đơn giản chỉ cần lấy pos Draw như bên dưới thì nó sẽ nằm trong màn hình
        double posXTemp = posX;
        double posYTemp = posY;
        posX = posX - tileMap.getCameraPosX();
        posY = posY - tileMap.getCameraPosY();
        if (lock3){
            currentAttackAnimation.setFacing(facing);
            currentAttackAnimation.render(graphicsContext, posX, posY, zzX, zzY);
        }
        else if (falling) {
            if (facing == 1) {
                graphicsContext.drawImage(big1,0,69,40,85,posX + -20.5,posY + -87.5 ,40*facing,85);
                graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(),posX+-24.0,posY+-76.0,jumpHead.getWidth()*facing,jumpHead.getHeight());
                graphicsContext.drawImage(jumpLowerBody.get(1),0,0,jumpLowerBody.get(1).getWidth(),jumpLowerBody.get(1).getHeight(),posX-jumpLowerBody.get(1).getWidth()/2*facing,posY-jumpLowerBody.get(1).getHeight(),jumpLowerBody.get(1).getWidth()*facing,jumpLowerBody.get(1).getHeight());
                graphicsContext.drawImage(jumpUpperBody.get(5),0,0,jumpUpperBody.get(5).getWidth(),jumpUpperBody.get(5).getHeight(),posX - 31.0 - 6,posY+-59.0,jumpUpperBody.get(5).getWidth()*facing,jumpUpperBody.get(5).getHeight());
            }
            else {
                graphicsContext.drawImage(big1,0,69,40,85,posX + 20.5,posY + -87.5 ,40*facing,85);
                graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(),posX+25,posY+-75.5,jumpHead.getWidth()*facing,jumpHead.getHeight());
                graphicsContext.drawImage(jumpLowerBody.get(1),0,0,jumpLowerBody.get(1).getWidth(),jumpLowerBody.get(1).getHeight(),posX-jumpLowerBody.get(1).getWidth()/2*facing,posY-jumpLowerBody.get(1).getHeight(),jumpLowerBody.get(1).getWidth()*facing,jumpLowerBody.get(1).getHeight());
                graphicsContext.drawImage(jumpUpperBody.get(5),0,0,jumpUpperBody.get(5).getWidth(),jumpUpperBody.get(5).getHeight(),posX+31,posY+-59.0,jumpUpperBody.get(5).getWidth()*facing,jumpUpperBody.get(5).getHeight());
            }
        }
        else if (lock) {
            if (phs == 0) {
                if (facing == 1) {
                    graphicsContext.drawImage(big1,0,69,40,85,posX + -20.5,posY + -87.5,40*facing,85);
                    graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(),posX + -20.0,posY +-69.5,jumpHead.getWidth()*facing,jumpHead.getHeight());
                    graphicsContext.drawImage(jumpLowerBody.get(0),0,0,jumpLowerBody.get(0).getWidth(),jumpLowerBody.get(0).getHeight(),posX-jumpLowerBody.get(0).getWidth()/2,posY-jumpLowerBody.get(0).getHeight(),jumpLowerBody.get(0).getWidth()*facing,jumpLowerBody.get(0).getHeight());
                    graphicsContext.drawImage(jumpUpperBody.get(0),0,0,jumpUpperBody.get(0).getWidth(),jumpUpperBody.get(0).getHeight(),posX + -20.5,posY + -41.0,jumpUpperBody.get(0).getWidth()*facing,jumpUpperBody.get(0).getHeight());
                }
                else {
                    graphicsContext.drawImage(big1,0,69,40,85,posX + 20.5,posY + -87.5 ,40*facing,85);
                    graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(), posX+20.0,posY+-69.5,jumpHead.getWidth()*facing,jumpHead.getHeight());
                    graphicsContext.drawImage(jumpLowerBody.get(0),0,0,jumpLowerBody.get(0).getWidth(),jumpLowerBody.get(0).getHeight(),posX+jumpLowerBody.get(0).getWidth()/2,posY-jumpLowerBody.get(0).getHeight(),jumpLowerBody.get(0).getWidth()*facing,jumpLowerBody.get(0).getHeight());
                    graphicsContext.drawImage(jumpUpperBody.get(0),0,0,jumpUpperBody.get(0).getWidth(),jumpUpperBody.get(0).getHeight(),posX + 21.0,posY + -41.0,jumpUpperBody.get(0).getWidth()*facing,jumpUpperBody.get(0).getHeight());
                }
            }
            if (phs == 1) {
                if (animationStep3 == 4) {
                    graphicsContext.drawImage(jumpUpperBody.get(animationStep3),0,0,jumpUpperBody.get(animationStep3).getWidth(),jumpUpperBody.get(animationStep3).getHeight(),posX-jumpUpperBody.get(animationStep3).getWidth()/2*facing,posY-jumpUpperBody.get(animationStep3).getHeight(),jumpUpperBody.get(animationStep3).getWidth()*facing,jumpUpperBody.get(animationStep3).getHeight());
                }
                else {
                    graphicsContext.drawImage(jumpUpperBody.get(animationStep3%4), 0, 0, jumpUpperBody.get(animationStep3%4).getWidth(), jumpUpperBody.get(animationStep3%4).getHeight(), posX-jumpUpperBody.get(animationStep3%4).getWidth()/2*facing, posY-jumpUpperBody.get(animationStep3%4).getHeight(), jumpUpperBody.get(animationStep3%4).getWidth() * facing, jumpUpperBody.get(animationStep3%4).getHeight());
                }
            }
            if (phs == 2) {
                if (facing == 1) {
                    graphicsContext.drawImage(big1,0,69,40,85,posX + -20.5,posY + -87.5 ,40*facing,85);
                    graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(),posX+-24.0,posY+-76.0,jumpHead.getWidth()*facing,jumpHead.getHeight());
                    graphicsContext.drawImage(jumpLowerBody.get(1),0,0,jumpLowerBody.get(1).getWidth(),jumpLowerBody.get(1).getHeight(),posX-jumpLowerBody.get(1).getWidth()/2*facing,posY-jumpLowerBody.get(1).getHeight(),jumpLowerBody.get(1).getWidth()*facing,jumpLowerBody.get(1).getHeight());
                    graphicsContext.drawImage(jumpUpperBody.get(5),0,0,jumpUpperBody.get(5).getWidth(),jumpUpperBody.get(5).getHeight(),posX+-31.0,posY+-59.0,jumpUpperBody.get(5).getWidth()*facing,jumpUpperBody.get(5).getHeight());
                }
                else {
                    graphicsContext.drawImage(big1,0,69,40,85,posX + 20.5,posY + -87.5 ,40*facing,85);
                    graphicsContext.drawImage(jumpHead,0,0,jumpHead.getWidth(),jumpHead.getHeight(),posX+25,posY+-75.5,jumpHead.getWidth()*facing,jumpHead.getHeight());
                    graphicsContext.drawImage(jumpLowerBody.get(1),0,0,jumpLowerBody.get(1).getWidth(),jumpLowerBody.get(1).getHeight(),posX-jumpLowerBody.get(1).getWidth()/2*facing,posY-jumpLowerBody.get(1).getHeight(),jumpLowerBody.get(1).getWidth()*facing,jumpLowerBody.get(1).getHeight());
                    graphicsContext.drawImage(jumpUpperBody.get(5),0,0,jumpUpperBody.get(5).getWidth(),jumpUpperBody.get(5).getHeight(),posX+31,posY+-59.0,jumpUpperBody.get(5).getWidth()*facing,jumpUpperBody.get(5).getHeight());
                }
            }
        }
        else  if (runningDirection == 0) {
            currentStandAnimation.setFacing(facing);
            currentStandAnimation.render(graphicsContext,posX,posY,zzX,zzY);
        }
        else if (runningDirection == -1 || runningDirection == 1) {
            currentRunAnimation.setFacing(facing);
            currentRunAnimation.render(graphicsContext,posX,posY,zzX,zzY);
        }

        //Draw a small dot at player position for simple debug
        if (debug) {
            double radius = 3;
            //Stoking
            graphicsContext.strokeOval(posX-radius, posY-radius, radius*2, radius*2);
            //Filling:
            graphicsContext.fillOval(posX-radius, posY-radius, radius*2, radius*2);
        }
        posX = posXTemp;
        posY = posYTemp;

        if (key.skill1 == 1) {
            skill1.render(graphicsContext);
        }
        if (key.skill2 == 1) {
            skill2.render(graphicsContext);
        }
        if (true) {
        	key.attack = 0;
        }
    }

    public int getFacing() {
        return facing;
    }

    private void drawWalkAnimation(int s) {
        return;
    }

    public void keyIn(KeyEvent keyEvent) {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) { //KEY PRESSED
            switch (keyEvent.getCode()) {
                case UP -> {
                    key.up = 1;
                    zzY -= 0.5;
                    //System.out.println("zzY = "+zzY);
                }
                case DOWN -> {
                    key.down = 1;
                    zzY += 0.5;
                    //System.out.println("zzY = "+zzY);
                }
                case LEFT -> {
                    key.left = 1;
                    zzX -= 0.5;
                    //System.out.println("zzX = "+zzX);
                }
                case RIGHT -> {
                    key.right = 1;
                    zzX += 0.5;
                    //System.out.println("zzX = "+zzX);
                }
                case ENTER -> {
                    debug = !debug;
                }
                case Q -> {
                	if (attackLock) {
                		long elapsed = (System.nanoTime() - attackTimer) / 1000000;
                		if (elapsed > box.getTimeLoad()) {
                			attackLock = false;
                		}
                		else {
                			break;
                		}
                	}
                	box = new AttackBox(tileMap);
                    attackTimer = System.nanoTime();
                    box.facing = facing;
                    box.setPos(posX, posY);
                    key.attack = 1;
                    attackLock = true;
                }
            }
        }
        else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)){
        	switch (keyEvent.getCode()) {
                case UP -> {
                    key.up = 0;
                }
                case DOWN -> {
                    key.down = 0;
                }
                case LEFT -> {
                    key.left = 0;
                }
                case RIGHT -> {
                    key.right = 0;
                }
                case DIGIT1 -> {
                	if (HPpotNum > 0 && HP < maxHP) {
                		HPpotNum--;
                		HP += HPinc;
                		if (HP > maxHP) HP = maxHP;
                	}
                }
                case DIGIT2 -> {
                	if (MPpotNum > 0 && MP < maxMP) {
                		MPpotNum--;
                		MP += MPinc;
                		if (MP > maxMP) MP = maxMP;
                	}
                }
                case ENTER -> {
                    debug = false;
                }
                case W-> {
                	if (skill1Lock) {
                		long elapsed = (System.nanoTime() - skill1Timer) / 1000000;
                		if (elapsed > skill1.getTimeLoad()) {
                			skill1Lock = false;
                		}
                		else {
                			break;
                		}
                	}
                	skill1 = new Skill1(tileMap);
                	if (MP < skill1.getManaCost()) {
                		skill1.setRemove();
                		break;
                	}
                	skill1Timer = System.nanoTime();
                	MP -= skill1.getManaCost();
                	skill1.facing = facing;
                	skill1.setPos(posX, posY);
                	key.skill1 = 1;
                	skill1Lock = true;
                }
                case E -> {
                	if (skill2Lock) {
                		long elapsed = (System.nanoTime() - skill2Timer) / 1000000;
                		if (elapsed > skill2.getTimeLoad()) {
                			skill2Lock = false;
                		}
                		else {
                			skill2.setRemove();
                			break;
                		}
                	}
                	skill2 = new Skill2(tileMap);
                	if (MP < skill2.getManaCost()) {
                		skill2.setRemove();
                		break;
                	}
                	skill2Timer = System.nanoTime();
                	MP -= skill2.getManaCost();
                	skill2.facing = facing;
                	skill2.setPos(posX, posY);
                	key.skill2 = 1;
                	skill2Lock = true;
                }
            }
        }
    }


    /*
    //Method does not control animation
    private void jump() {

    }
    //Method does not control animation
    private void left() { //Method does not control animation

    }
    //Method does not control animation
    private void right(){

    }
     */
}
