package entity;

import java.io.FileInputStream;
import entity.enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

public class Dropping extends Enemy{
	public final static int HPpot = 0;
	public final static int MPpot = 1;
	
	private Image[] imageHP, imageMP;
	public int type;
	public Dropping(TileMap tm, Enemy enemy) {
		super(tm);
		
		posX = enemy.posX;
		posY = enemy.posY;
		
		width = 20;
		height = 20;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		this.xmin = width/2+1;
		this.xmax = tileMap.getWidth()-width/2-1;
		this.ymin = height;
		this.ymax = tileMap.getHeight() -1;
		setEntityBoxSize(width, height);
		
		try {
			imageHP = new Image[1];
			imageMP = new Image[1];
			imageHP[0] = new Image(new FileInputStream("res/hud/hp_dropping.png"));
			imageMP[0] = new Image(new FileInputStream("res/hud/mp_dropping.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		int random_int = (int)Math.floor(Math.random()*2);
		if (random_int == HPpot)	animation.setFrames(imageHP);
		else if (random_int == MPpot)	animation.setFrames(imageMP);
		type = random_int;
		animation.setDelay(300);
	}
	
	private void getNextPosition() {
		// falling
		if(falling) {
			dy += fallSpeed;
			if (dy > maxFallSpeed) dy = maxFallSpeed;
		}
	}

	@Override
	public void tick() {
		// update position
		falling = true;
		getNextPosition();
		CheckTileMapCollision();
		posY += dy;
		
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
