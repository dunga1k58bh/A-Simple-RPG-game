package entity.enemies;

import java.io.FileInputStream;
import java.util.ArrayList;
import entity.Animation;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tilemap.TileMap;
import utils.animation.particle.bloodSplash;

public class Fly extends Enemy{
	private Image[] sprites;
	public final static int DOWN = 1;

	public final static int UP = 0;public final static int LEFT = 2;
	public final static int RIGHT = 3;
	// fly ball
	private ArrayList<FlyBall> flyBalls;
	private int flyBallDamage;
	private boolean firing;
	private long firingTimer;
	private long firingTime;
	
	// player
	private Player player;
	private utils.animation.Animation bloodSplash = new bloodSplash();
	
	public Fly(TileMap tm, Player p, int hardLevel) {
		super(tm);
		player = p;
		
		firingTime = 2500 / hardLevel;
		EXP = 5;
		moveSpeed = 1;
		maxSpeed = 2 * hardLevel;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		HP = maxHP = 40 * hardLevel;
		lastHp = HP;
		damage = 10 * hardLevel;
		flyBalls = new ArrayList<FlyBall>();
		flyBallDamage = 5;
		firing = true;
		
		width = 80;
		height = 62;
		this.xmin = width/2+1;
		this.xmax = tileMap.getWidth()-width/2-1;
		this.ymin = height;
		this.ymax = tileMap.getHeight() -1;
	    setEntityBoxSize(80, 62);
		
		// load sprites
		try {
			sprites = new Image[4];
			sprites[0] = new Image(new FileInputStream("res/enemies/fly/fly1.png"));
			sprites[1] = new Image(new FileInputStream("res/enemies/fly/fly2.png"));
			sprites[2] = new Image(new FileInputStream("res/enemies/fly/fly3.png"));
			sprites[3] = new Image(new FileInputStream("res/enemies/fly/fly4.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(200);
		
		right = true;
		left = false;
		up = false;
		down = false;
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
		else if(up) {
			dy -= moveSpeed;
			if (dy < -maxSpeed) dy = -maxSpeed;
		}
		else if(down) {
			dy += moveSpeed;
			if (dy > maxSpeed) dy = maxSpeed;
		}
	}
	
	@Override
	public void tick() {
		if (lastHp != HP) {
			((bloodSplash)bloodSplash).activate();
			lastHp = HP;
		}
		bloodSplash.tick();
		bloodSplash.setFacing(facing);
		if (firing && !notOnScreen()) {
			FlyBall fb = new FlyBall(tileMap);
			fb.setPosition(posX, posY);
			fb.getPlayerPos(player);
			flyBalls.add(fb);
			firingTimer = System.nanoTime();
			firing = false;
		}
		if (!firing) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if (elapsed > firingTime) firing = true;

		}
		
		// update fly balls
		for(int i = 0; i < flyBalls.size(); i++) {
			flyBalls.get(i).tick();
			if(flyBalls.get(i).shouldRemove()) {
				flyBalls.remove(i);
				i--;
			}
			else {
				if(flyBalls.get(i).intersects(player)) {
					flyBalls.remove(i);
					i--;
					player.changeHP(-flyBallDamage);
				}
			}
		}
		
		// update position
		getNextPosition();
		CheckTileMapCollision();
		posX += dx;
		posY += dy;
		
		// check flinching
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) flinching = false;
		}
		
		int random_int = (int)Math.floor(Math.random()*4);
		if (random_int == UP) {
			up = true;
			down = false;	left = false;	right = false;
		}
		else if (random_int == DOWN) {
			down = true;
			up = false;		left = false;	right = false;
		}
		else if (random_int == LEFT) {
			left = true;
			right = false;	up = false;		down = false;
		}
		else if (random_int == RIGHT) {
			right = true;
			left = false;	up = false;		down = false;
		}
		
		// if it hits a wall, go other direction
		if(right && dx == 0) {
			right = false;
			left = true;
			facing = -1;
		}
		else if(left && dx == 0){
			right = true;
			left = false;
			facing = 1;
		}
		else if(up && dy == 0) {
			up = false;
			down = true;
		}
		else if(down && dy == 0) {
			up = true;
			down = false;
		}
		// update animation
		animation.update();
	}
	
	@Override
	public void render(GraphicsContext graphicsContext) {
		setMapPosittion();
		// render fly balls
		for(int i = 0; i < flyBalls.size(); i++) {
			flyBalls.get(i).render(graphicsContext);
		}
		if(notOnScreen()) return;
		//HP of enemy
		if (!dead) {
			graphicsContext.setFill(Color.WHITE);
			graphicsContext.fillRect(
					posX -xmap- (double)width / 2 + 11,
					posY -ymap- (double)height - 5,
					41,
					5);
			graphicsContext.setFill(Color.RED);
			graphicsContext.fillRect(
					posX -xmap- (double)width / 2 + 11,
					posY -ymap- (double)height - 5,
					41 * (double)HP / (double)maxHP,
					5);
		}

		super.render(graphicsContext);
		bloodSplash.render(graphicsContext, posX + (-xmap - (double)width/2) * -facing, posY -ymap - (double)height/2, 0, 0);
//		double radius = 3;
//		//Stoking
//		graphicsContext.strokeOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);
//		//Filling:
//		graphicsContext.fillOval(posX-xmap-radius, posY-ymap-radius, radius*2, radius*2);
	}
}
