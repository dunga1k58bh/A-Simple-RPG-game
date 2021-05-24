package state.map;

import java.util.ArrayList;

import Audio.Music;
import application.Main;
import entity.enemies.Enemy;
import entity.Player;
import entity.enemies.Snail;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;
import tilemap.TileMap;

public class Map1 extends GameState {

    //OVERALL DESCRIPTION:
    //this class owns tilemap obj and stores a reference to player object which playstate owns. Playstate will pass player obj to this class.
    //this class also owns player's on-map cord
    //this class owns camera position

    private final Image bg= new Image("bg/bgMap1.png");

    //only reference
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move
    private ArrayList<Enemy> enemies;
    private final TileMap tilemap1;

    //Music BackGround
    private final Music bgMusic;

    //starting position of player on map (On-map coord)
    public final double playerStartingPosX = 200; //TODO
    public final double playerStartingPosY = 100; //TODO

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    public Map1(GameStateManager gsm){
        super(gsm);

        bgMusic = new Music("res/Audio/bgMusic0.wav");
        tilemap1 = new TileMap(48);
        tilemap1.loadTileSet("Map/TileSet.png");
        tilemap1.loadMap("res/Map/Map1.map");
        tilemap1.setPos(camPosX,camPosY);
        generateEnemies();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.1);
        bgMusic.startMusic();
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setPosX(playerStartingPosX);
        player.setPosY(playerStartingPosY);
        //Vá»©t TileMap cho player
        player.setTileMap(tilemap1);
        player.initSkill();
    }
    
	private void generateEnemies() {
		enemies = new ArrayList<>();
		Snail s;
		Point2D[] points = new Point2D[] {
			new Point2D(200, 100),
			new Point2D(860, 200),
			new Point2D(1525, 200),
			new Point2D(1680, 200),
			new Point2D(1800, 200)
		};
        for (Point2D point : points) {
            s = new Snail(tilemap1);
            s.setPosition(point.getX(), point.getY());
            enemies.add(s);
        }
		
	}

    @Override
    public void init() {

    }
    @Override
    public void tick() {

        camPosX = player.getPosX() - Main.width*1/3;
        camPosY = player.getPosY() - Main.height*2/3;
        tilemap1.setPos(camPosX,camPosY);
        tilemap1.tick();
        player.tick();
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.tick();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
			}
		}
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
        tilemap1.draw(g);
        player.render(g);
        for (Enemy enemy : enemies) {
//		    System.out.println(enemies.get(0).getPosX()+" "+ enemies.get(0).getPosY());
            enemy.render(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        player.keyIn(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {
//
        player.keyIn(k);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        player.keyIn(k);
    }
}
