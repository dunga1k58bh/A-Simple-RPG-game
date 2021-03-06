package entity.enemies;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import entity.Animation;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tilemap.TileMap;
import utils.animation.particle.bloodSplash;

public class Snail extends Enemy{
	private Image[] sprites;
	private utils.animation.Animation bloodSplash = new bloodSplash();
	public Snail(TileMap tm, int hardlevel) {
		super(tm);
		
		EXP = 5;
		moveSpeed = 0.3;
		maxSpeed = hardlevel;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		HP = maxHP = 30 * hardlevel;
		damage = 10 * hardlevel;
		
		width = 60;
		height = 60;
		this.xmin = width/2 + 1;
		this.xmax = tileMap.getWidth() - width/2 - 1;
		this.ymin = height;
		this.ymax = tileMap.getHeight() - 1;
	    setEntityBoxSize(40, 40);
		
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
		facing = 1;
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
	@Override
	public void tick() {
		if (lastHp != HP) {
			((bloodSplash)bloodSplash).activate();
			lastHp = HP;
		}
		bloodSplash.tick();
		bloodSplash.setFacing(facing);
		// update position
		falling = true;
		getNextPosition();
		CheckTileMapCollision();
		posX += dx;
		posY += dy;
		// check flinching
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) flinching = false;
		}
		
		// if it hits a wall, go other direction
		if((right && dx == 0)||posX - posXBegin > 100) {
			right = false;
			left = true;
			facing = -1;
		}
		else if((left && dx == 0)||posX - posXBegin < -100){
			right = true;
			left = false;
			facing = 1;
		}
		
		// update animation
		animation.update();
	}
	@Override
	public void render(GraphicsContext graphicsContext) {
		setMapPosittion();

		if(notOnScreen()) return;
		//HP of enemy
		if (!dead) {
			graphicsContext.setFill(Color.WHITE);
			graphicsContext.fillRect(
					posX -xmap- (double)width / 2 + 10,
					posY -ymap- (double)height + 10,
					40,
					5);
			graphicsContext.setFill(Color.RED);
			graphicsContext.fillRect(
					posX -xmap- (double)width / 2 + 10,
					posY -ymap- (double)height + 10,
					40 * (double)HP / (double)maxHP,
					5);
		}
		super.render(graphicsContext);
		bloodSplash.render(graphicsContext, posX + (-xmap - (double)width/2) * -facing, posY -ymap - (double)height/2, 0, 0);
	}
}
