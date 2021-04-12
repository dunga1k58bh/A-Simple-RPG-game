package com.first.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.first.game.graphic.Animation;
import com.first.game.graphic.Sprite;
import com.first.game.util.AABB;

import com.first.game.util.Vector2f;

public abstract class Entity {
	 private final int UP =3;
	 private final int DOWN = 2;
	 private final int RIGHT = 0;
	 private final int LEFT = 1;
	 
	 protected Animation ani;
	 protected Sprite sprite;
	 protected Vector2f pos;
	 protected int size;
	 
	 protected int currentAnimation;
	 protected boolean up;
	 protected boolean down;
	 protected boolean left;
	 protected boolean right;
	 protected boolean attack;
	 protected boolean attackSpeed;
	 protected boolean attackDuration;
	 protected float dx;
	 protected float dy;
	 
	 protected float maxSpeed=2f;
	 protected float acc=1f;
	 protected float deacc=0.5f;
	 
	 protected AABB hitBounds;
	 protected AABB bounds;
	 
     public Entity(Sprite sprite, Vector2f orgin, int size){
    	
    	 
    	 this.sprite = sprite;
    	 pos = orgin;
    	 this.size = size;
    	 
    	 bounds =new AABB(orgin, size,size);
    	 hitBounds = new AABB(new Vector2f(orgin.x +(size/2 ), orgin.y),size,size);
    	 this.ani = new Animation();
    	 setAnimation(RIGHT, sprite.getSpriteArray(RIGHT),10);
    	 
     }
     public void setSprite(Sprite sprite) {
    	 this.sprite = sprite;
     }
     public void setSize(int i) {size = i;}
     public void setMaxSpeed(float f) {maxSpeed = f;}
     public void setAcc(float f) {acc =f;}
     public void setDeacc(float f) { deacc =f;}
     
     public AABB getBound() {return bounds;}
     public int getSize() {return size;}	
     
     
     public void setAnimation(int i,BufferedImage[] frame, int delay) {
    	 currentAnimation = i;
    	 ani.setFrames(frame);
    	 ani.setDelay(delay);
     }
     public Animation getAnimation() {return ani;}
     public void animate() {
    	 if(up) {
    		 if(currentAnimation != UP|| ani.getDelay() == -1) {
    			 setAnimation(UP, sprite.getSpriteArray(UP), 5);
    		 }
    	 }
    	 else if(down) {
    		 if(currentAnimation != DOWN|| ani.getDelay() == -1) {
    			 setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
    		 }
    	 }
    	 else if(left) {
    		 if(currentAnimation != LEFT|| ani.getDelay() == -1) {
    			 setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
    		 }
    	 }
    	 else if(right) {
    		 if(currentAnimation != RIGHT|| ani.getDelay() == -1) {
    			 setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
    		 }
    	 }else {
    		 setAnimation(currentAnimation,sprite.getSpriteArray(currentAnimation),-1);
    	 }
     }
     private void setHitBoxDirection() {
    	 if(up) {
    		 hitBounds.setxOffset(-size /2);
    		 hitBounds.setyOffset(-size /2);
    	 }else if(down) {
    		 hitBounds.setxOffset(-size /2);
    		 hitBounds.setyOffset(-size /2);
    	 }else if(left) {
    		 hitBounds.setxOffset(-size /2);
    		 hitBounds.setyOffset(0);
    	 }else if (right) {
    		 hitBounds.setxOffset(0);
    		 hitBounds.setyOffset(0);
    	 }
     }
     public void update() {
    	 animate ();
    	 setHitBoxDirection();	
    	 ani.update();
    	 
     }
     public abstract void render(Graphics2D g);
   
     
}     
     