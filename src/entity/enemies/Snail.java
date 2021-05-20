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
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		HP = maxHP = 3;
		damage = 1;
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
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
		getNextPosition();
		CheckTileMapCollision();
		posX+=dx;
		posY+=dy;
		// check flinching
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) flinching = false;
		}
		
		// if it hits a wall, go other direction
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}
		
		// update animation
		animation.update();
	}
	@Override
	public void render(GraphicsContext graphicsContext) {
		if(notOnScreen()) return;
		super.render(graphicsContext);
	}
}
