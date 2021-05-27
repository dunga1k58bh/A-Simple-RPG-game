package entity.enemies;

import java.io.FileInputStream;
import entity.Animation;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

public class FlyBall extends Enemy{
	private boolean hit;
	private boolean remove;
	private Image[] sprites;
	private double playerPosX;
	private double playerPosY;
	private double distance;
	
	public FlyBall(TileMap tm) {
		super(tm);
		
		width = 10;
		height = 10;
		this.xmin = width/2+1;
		this.xmax = tileMap.getWidth()-width/2-1;
		this.ymin = height;
		this.ymax = tileMap.getHeight() -1;
	    setEntityBoxSize(10, 10);
	    
	    // load sprites
		try {
			sprites = new Image[1];
			sprites[0] = new Image(new FileInputStream("res/enemies/fly/fly_ball.png"));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
	}
	public void getPlayerPos(Player player) {
		playerPosX = player.getPosX();
		playerPosY = player.getPosY();
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		dx = 0;		dy = 0;
	}
	
	public boolean shouldRemove() { 
		return remove; 
	}
	
	private void getNextPosition() {
		if (playerPosX < posXBegin) {
			left = true;
			right = false;
		}
		if (playerPosX > posXBegin) {
			left = false;
			right = true;
		}
		if (playerPosY < posYBegin) {
			up = true;
			down = false;
		}
		if (playerPosY > posYBegin) {
			up = false;
			down = true;
		}
		
		distance = Math.sqrt((playerPosX - posXBegin)*(playerPosX - posXBegin)
							 + (playerPosY - posYBegin)*(playerPosY - posYBegin));
		// movement
		dx = 5 *(playerPosX - posXBegin) / distance;
		dy = 5 *(playerPosY - posYBegin) / distance;
	}
	
	@Override
	public void tick() {
		// update position
		getNextPosition();
		CheckTileMapCollision();
		posX += dx;
		posY += dy;
		if((dx == 0 || dy == 0) && !hit) {
			setHit();
		}
		
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		// update animation
		animation.update();
	}
	
	@Override
	public void render(GraphicsContext graphicsContext) {
		setMapPosittion();
		if(notOnScreen()) return;
		super.render(graphicsContext);
	}
}
