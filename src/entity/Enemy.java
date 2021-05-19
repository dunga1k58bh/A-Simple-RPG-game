package entity;

import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import tilemap.Tile;
import tilemap.TileMap;

public class Enemy extends Entity{

	protected int HP;
	protected int maxHP;
	protected boolean dead;
	protected int damage;
	protected boolean flinching;
	protected long flinchTimer;
	
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double posX;
	protected double posY;
	protected double dx;
	protected double dy;
	
	// dimensions
	protected int width;
	protected int height;
	
	// collision box
	protected int cwidth;
	protected int cheight;
	
	// collision
	protected int curRow;
	protected int curCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// animation
	protected Animation animation;
	protected int curAction;
	protected int preAction;
	protected boolean facingRight;
	
	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
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
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}
	public int getx() { return (int)posX; }
	public int gety() { return (int)posY; }
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
		this.posX = x;
		this.posY = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public void setMapPosition() {
		xmap = tileMap.getCameraPosX();
		ymap = tileMap.getCameraPosY();
	}
	
	
	public boolean intersects(Enemy e) {
		Rectangle2D r1 = getRectangle();
		Rectangle2D r2 = e.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D((int)posX - cwidth, (int)posY - cheight, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y) {
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCK;
		topRight = tr == Tile.BLOCK;
		bottomLeft = bl == Tile.BLOCK;
		bottomRight = br == Tile.BLOCK;
	}
	
	public void checkTileMapCollision() {
		curCol = (int)posX / tileSize;
		curRow = (int)posY / tileSize;
		xdest = posX + dx;
		ydest = posY + dy;
		xtemp = posX;
		ytemp = posY;
		
		calculateCorners(posX, ydest);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				ytemp = curRow * tileSize + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (curRow + 1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, posY);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				xtemp = curCol * tileSize + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				xtemp = (curCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if(!falling) {
			calculateCorners(posX, ydest + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
	}
	
	
	public boolean notOnScreen() {
		return posX + xmap + width < 0 ||
			posX + xmap - width > Main.width ||
			posY + ymap + height < 0 ||
			posY + ymap - height > Main.height;
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
		if(facingRight) {
			graphicsContext.drawImage(
				animation.getImage(),
				(int)(posX + xmap - width / 2), (int)(posY + ymap - height / 2));
		}
		else {
			graphicsContext.drawImage(
				animation.getImage(),
				(int)(posX + xmap - width / 2 + width),
				(int)(posY + ymap - height / 2),
				-width,height);
		}
	}

	@Override
	public void tick() {}
}
