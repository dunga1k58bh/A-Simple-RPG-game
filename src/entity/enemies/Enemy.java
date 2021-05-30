package entity.enemies;

import application.Main;
import entity.Animation;
import entity.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tilemap.Tile;
import tilemap.TileMap;

public abstract class Enemy extends Entity {

	protected  double posXBegin;
	protected  double posYBegin;

	protected int HP;
	protected int maxHP;
	protected boolean dead;
	protected int damage;
	protected int EXP;


	
	// animation
	protected Animation animation;
	protected int curAction;
	protected int preAction;

	
	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	
	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	// constructor
	public Enemy(TileMap tm) {
		super(tm);
	}
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public void setUp(boolean b) { up = b; }
	public boolean isDead() { return dead; }
	public int getDamage() { return damage; }
	public void setPosition(double x, double y) {
		posXBegin = x;
		posYBegin = y;
		this.posX = x;
		this.posY = y;
	}
	public int getEXP() {
		return EXP;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	@Override
    public void getHit(int damage) {
        if(dead || flinching) return;
        HP -= damage;
        if(HP < 0) HP = 0;
        if(HP == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();
        beingHit = true;
    }

	@Override
	public void CheckTileMapCollision() {
		super.CheckTileMapCollision();
		CaculateCorrners(posX,posY+ 3*dy);
		if(dy<0){ //Bay lên
            if (TopLeft == Tile.BLOCK || TopRight == Tile.BLOCK){
                dy = 0;
                posY =(currRow)*tileSize +3;
            }
		}
	}

	@Override
	public void render(GraphicsContext graphicsContext) {


    	//draw Animation of Enemy
		if(facingRight) {
			graphicsContext.drawImage(
					animation.getImage(),
					(posX -xmap- width / 2),
					(posY -ymap- height ),
					width,height);
		}
		else {
			graphicsContext.drawImage(
				animation.getImage(),
				(posX -xmap + width / 2 ),
				(posY -ymap- height ),
				-width,height);          //-width to flip image
		}
	}

}
