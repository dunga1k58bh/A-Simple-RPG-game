package state.map;

import java.util.ArrayList;
import Audio.Music;
import application.Main;
import entity.enemies.Enemy;
import entity.enemies.Fly;
import entity.somethings.Dropping;
import entity.somethings.Gate;
import entity.somethings.HUD;
import entity.Player;
import entity.enemies.Monster2;
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
    private ArrayList<Dropping> droppings;
    private final TileMap tilemap1;
    private Gate gatetoNextMap;
    private HUD hud;
    
    //Music BackGround
    private final Music bgMusic;

    //starting position of player on map (On-map coord)
    private final double playerStartingPosX = 200; //TODO
    private final double playerStartingPosY = 700; //TODO
    //position of player on map if return the old Map
    private final double playerReturnPosX =  2300;
    private final double playerReturnPosY =  100;
    //Is this map clear ?
    public boolean isclear;

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    //Gate to next Map
    private double gateX;
    private double gateY;
    //Gate to previous Map
    // null

    public Map1(GameStateManager gsm) {
        super(gsm);

        bgMusic = new Music("res/Audio/bgMusic0.wav");
        tilemap1 = new TileMap(48);
        tilemap1.loadTileSet("Map/TileSet.png");
        tilemap1.loadMap("res/Map/Map1.map");
        tilemap1.setPos(camPosX, camPosY);
        //generateEnemies();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.1);
        bgMusic.startMusic();

        isclear = false;          // this map is not clear at the time it init()
        gatetoNextMap = new Gate(tilemap1);
        gatetoNextMap.setPos(2376,192);
        gsm.setNextMap(true);
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
        //System.out.println("playerSet: " + player);
        if(gsm.getNextMap()== true) {
            player.setPos(playerStartingPosX,playerStartingPosY);
        }else{
            player.setPos(playerReturnPosX,playerReturnPosY);
        }
        gsm.setNextMap(false);
        //Vá»©t TileMap cho player
        player.setTileMap(tilemap1);
        player.initSkill();
        hud = new HUD(player);
        generateEnemies();
    }
    
	private void generateEnemies() {
		enemies = new ArrayList<>();
		droppings = new ArrayList<>();
		Snail s;
		Fly f;
		Point2D[] points = new Point2D[] {
			new Point2D(200, 900),
			new Point2D(860, 200),
			new Point2D(1525, 200),
			new Point2D(1680, 200),
			new Point2D(1800, 200)
		};
        Monster2 m2 = new Monster2(tilemap1);
        m2.setPosition(700,900);
        enemies.add(m2);
        m2.setTarget(player);
        
        Fly fly = new Fly(tilemap1, player);
        fly.setPos(700, 1000);
        enemies.add(fly);
        
        Fly fly2 = new Fly(tilemap1, player);
        fly2.setPos(500, 700);
        enemies.add(fly2);
        
        for (Point2D point : points) {
            s = new Snail(tilemap1);
            f = new Fly(tilemap1, player);
            f.setPos(point.getX(), point.getY() - 100);
            s.setPosition(point.getX(), point.getY());
            enemies.add(s);
            enemies.add(f);
        }
		
	}

    @Override
    public void tick(){
        if (player.getFacing() == 1) {
            camPosX = player.getPosX() - Main.width*1/3;
        }
        else {
            camPosX = player.getPosX() - Main.width*2/3;
        }
        camPosY = player.getPosY() - Main.height*2/3;
        tilemap1.setPos(camPosX,camPosY);

        tilemap1.tick();
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (player.intersects(e)) player.changeHP(-5);
            if(player.getSkill1().intersects(e)) {
                e.getHit(1);

            }

            e.tick();

            if(e.isDead()) {
                Dropping d = new Dropping(tilemap1, e);
                droppings.add(d);
                enemies.remove(i);
                i--;
            }
        }
        player.tick();

		for(int i = 0; i < droppings.size(); i++) {
			Dropping d = droppings.get(i);
			d.tick();
			if (d.intersects(player)) {
				if (d.type == d.HPpot) player.HPpotNum++;
				else if (d.type == d.MPpot) player.MPpotNum++;
				droppings.remove(i);
				i--;
			}
		}

        //Check to open gate
		tilemap1.OpenNextMap(enemies.size());
        changeMap(); // change the map if can be :
    }

    public void changeMap(){
        if(enemies.size()==0) isclear = true;    ///if there no enemy the map is clear
        //Check to move to next map
        if(isclear&&player.intersects(gatetoNextMap)){   //Move to nextMap
            gsm.nextMap();            // move to next map
            gsm.setNextMap(true);
            gsm.setPlayer(player);    //set player to next map
            gsm.setNextMap(false);
        }//map 1 dont have the gate to previous map
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
        tilemap1.draw(g);
        for (Enemy enemy : enemies) {
//		    System.out.println(enemies.get(0).getPosX()+" "+ enemies.get(0).getPosY());
            enemy.render(g);
        }
        player.render(g);
        hud.render(g);
		for(int i = 0; i < droppings.size(); i++) {
			Dropping d = droppings.get(i);
			d.render(g);
		}
		if(isclear) gatetoNextMap.render(g);
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
