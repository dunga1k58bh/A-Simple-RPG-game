package entity.enemies;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import entity.Animation;
import entity.Enemy;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

public class Snail extends Enemy{

	private Image[] sprites;
	
	public Snail(TileMap tm) {
		super(tm);
		
		moveSpeed = 0.3;
		maxSpeed = 1;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		HP = maxHP = 3;
		damage = 1;
		
		width = 60;
		height = 60;
	    setEntityBoxSize(60,60);
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
				new FileInputStream("res/enemies/snail.gif")
			);
			
			sprites = new Image[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = SwingFXUtils.toFXImage(spritesheet.getSubimage(
					i * width,
					0,
					width,
					height),
					null
				);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
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
		
		// falling
		if(falling) dy += fallSpeed;

	}
	
	public void tick() {
		// update position
		falling = true;
		getNextPosition();
		CheckTileMapCollision();
		posX += dx;
		posY += dy;
		// check flinching
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) flinching = false;
		}
		
		// if it hits a wall, go other direction
		if((right && dx == 0)||posX - posXBegin > 100) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if((left && dx == 0)||posX - posXBegin < -100){
			right = true;
			left = false;
			facingRight = true;
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
