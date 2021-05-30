package state.map;

import java.io.FileInputStream;
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

public class Map3 extends GameState {

    //OVERALL DESCRIPTION:
    //this class owns tilemap obj and stores a reference to player object which playstate owns. Playstate will pass player obj to this class.
    //this class also owns player's on-map cord
    //this class owns camera position

    private  Image bg;

    //only reference
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move
    private ArrayList<Enemy> enemies;
    private ArrayList<Dropping> droppings;
    private  TileMap tileMap;
    private final Gate gatetoNextMap;
    private final Gate gateToPreviousMap;
    private HUD hud;
    private int hardLevel;

    //Music BackGround
    private  Music bgMusic;

    //starting position of player on map (On-map coord)
    public final double playerStartingPosX = 100;//TODO
    public final double playerStartingPosY = 200; //TODO
    //Is this map clear ?
    public boolean isclear;

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    public Map3(GameStateManager gsm){
        super(gsm);
        try {
            bg= new Image(new FileInputStream("res/bg/bgMap3.gif"));
            bgMusic = new Music("res/Audio/bgMusic0.wav");
            tileMap = new TileMap(48);
            tileMap.loadTileSet("Map/TileSet.png");
            tileMap.loadMap("res/Map/Map3.map");
        }catch (Exception e){
            e.printStackTrace();
        }
        tileMap.setPos(camPosX,camPosY);
        hardLevel = gsm.getHardLevel();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.1);
        //the gate
        gateToPreviousMap = new Gate(tileMap);
        gateToPreviousMap.setPos(24,288);
        gatetoNextMap = new Gate(tileMap);
        gatetoNextMap.setPos(2136,1632);
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
        //System.out.println("playerSet: " + player);
        player.setPosX(playerStartingPosX);
        player.setPosY(playerStartingPosY);
        //Vá»©t TileMap cho player
        player.setTileMap(tileMap);
        hud = new HUD(player);
        generateEnemies();
        bgMusic.startMusic();
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
        Monster2 m2 = new Monster2(tileMap, hardLevel);
        m2.setPosition(700,900);
        enemies.add(m2);
        m2.setTarget(player);

        Fly fly = new Fly(tileMap, player, hardLevel);
        fly.setPos(700, 1000);
        enemies.add(fly);

        Fly fly2 = new Fly(tileMap, player, hardLevel);
        fly2.setPos(500, 700);
        enemies.add(fly2);

        for (Point2D point : points) {
            s = new Snail(tileMap, hardLevel);
            f = new Fly(tileMap, player, hardLevel);
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
        tileMap.setPos(camPosX,camPosY);

        tileMap.tick();
        player.tick();
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (player.intersects(e)) player.changeHP(-5);
            if (player.getKey().skill1 == 1) {
                if (player.getSkill1().intersects(e)) {
                    e.getHit(2);
                }
            }
            if (player.getKey().skill2 == 1) {
                if (player.getSkill2().intersects(e)) {
                    e.getHit(1);
                }
            }
            e.tick();

            if(e.isDead()) {
                Dropping d = new Dropping(tileMap, e);
                droppings.add(d);
                enemies.remove(i);
                i--;
            }
        }
        
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
        tileMap.OpenNextMap(enemies.size());
        changeMap();
    }
    public void changeMap(){
        if(enemies.size()==0) isclear = true;    ///if there no enemy the map is clear
        //Check to move to next map
        if(isclear&&player.intersects(gatetoNextMap)){   //Move to nextMap
            bgMusic.pauseMusic();
            gsm.nextMap();            // move to next map
            gsm.setNextMap(true);     //It mean the player is being move to the NEXT map
            gsm.setPlayer(player);    //set player to next map
            gsm.setNextMap(false);
        }
        if(player.intersects(gateToPreviousMap)){
            bgMusic.pauseMusic();
            gsm.previousMap();     //gsm.getNextMap is false in defalt so setPlayer will set player to returnPos
            gsm.setPlayer(player);
        }
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
        tileMap.draw(g);
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
        gateToPreviousMap.render(g);
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
