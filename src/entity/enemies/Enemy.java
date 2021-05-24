package entity.enemies;

import application.Main;
import entity.Animation;
import entity.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tilemap.TileMap;

public abstract class Enemy extends Entity {

	protected  double posXBegin;
	protected  double posYBegin;
	protected int HP;
	protected int maxHP;
	protected boolean dead;
	protected int damage;
	protected boolean flinching;
	protected long flinchTimer;



	
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
		super();
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}
	public double getx() { return (int)posX; }
	public double gety() { return (int)posY; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }
	public boolean isDead() { return dead; }
	public int getDamage() { return damage; }
	public void setPosition(double x, double y) {
		posXBegin = x;
		posYBegin = y;
		this.posX = x;
		this.posY = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public boolean intersects(Enemy e) {
		Rectangle2D r1 = getRectangle();
		Rectangle2D r2 = e.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D((int)posX - cwidth, (int)posY - cheight, cwidth, cheight);
	}

	
	public boolean notOnScreen() {
		return posX - xmap- width > Main.width ||
			posX - xmap + width < 0 ||
			posY - ymap + height < 0 ||
			posY - ymap - height > Main.height;
	}
	
	public void getHit(int damage) {
		if(dead || flinching) return;
		HP -= damage;
		if(HP < 0) HP = 0;
		if(HP == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
    @Override
	public void render(GraphicsContext graphicsContext) {
    	if (!dead) {
    		graphicsContext.fillRect(
    				posX -xmap- width / 2 + 10,
    				posY -ymap- height + 10,
    				40,
    				5);
    		graphicsContext.setFill(Color.RED);
    		graphicsContext.fillRect(
    				posX -xmap- width / 2 + 10,
    				posY -ymap- height + 10,
    				40 * (double)HP / (double)maxHP,
    				5);
    		graphicsContext.setFill(Color.WHITE);
    	}
    	
		if(facingRight) {
			graphicsContext.drawImage(
					animation.getImage(),
					(posX -xmap- width / 2 ),
					(posY -ymap- height ),
					width,height);
		}
		else {
			graphicsContext.drawImage(
				animation.getImage(),
				(posX -xmap + width / 2 ),
				(posY -ymap- height ),
				-width,height);
		}
	}

}
