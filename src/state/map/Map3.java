package state.map;

import java.io.FileInputStream;
import java.util.ArrayList;
import Audio.Music;
import application.Main;
import entity.enemies.Enemy;
import entity.enemies.Fly;
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
    private  TileMap tilemap3;
    private Gate gatetoNextMap;
    private Gate gateToPreviousMap;
    private HUD hud;

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
            bg= new Image(new FileInputStream("res/bg/bgMap3.png"));
            bgMusic = new Music("res/Audio/bgMusic0.wav");
            tilemap3 = new TileMap(48);
            tilemap3.loadTileSet("Map/TileSet.png");
            tilemap3.loadMap("res/Map/Map3.map");
        }catch (Exception e){
            e.printStackTrace();
        }

        tilemap3.setPos(camPosX,camPosY);
        //generateEnemies();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.1);
        bgMusic.startMusic();
        //the gate
        gateToPreviousMap = new Gate(tilemap3);
        gateToPreviousMap.setPos(24,240);
        gatetoNextMap = new Gate(tilemap3);
        gatetoNextMap.setPos(1368,192);
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
        //System.out.println("playerSet: " + player);
        player.setPosX(playerStartingPosX);
        player.setPosY(playerStartingPosY);
        //Vá»©t TileMap cho player
        player.setTileMap(tilemap3);
        player.initSkill();
        hud = new HUD(player);
        generateEnemies();
    }

    private void generateEnemies() {
        enemies = new ArrayList<>();
        Snail s;
        Fly f;
        Point2D[] points = new Point2D[] {
                new Point2D(200, 900),
                new Point2D(860, 200),
                new Point2D(1525, 200),
                new Point2D(1680, 200),
                new Point2D(1800, 200)
        };
        Monster2 m2 = new Monster2(tilemap3);
        m2.setPosition(700,900);
        enemies.add(m2);
        m2.setTarget(player);
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
        tilemap3.setPos(camPosX,camPosY);

        tilemap3.tick();
        player.tick();
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (player.intersects(e)) player.changeHP(-5);
            if(player.getSkill1().intersects(e)) {
                e.getHit(1);
                e.tick();
            }
            if(e.isDead()) {
                enemies.remove(i);
                i--;
            }
        }
        //Check to open gate
        tilemap3.OpenNextMap(enemies.size());
        changeMap();
    }
    public void changeMap(){
        if(enemies.size()==0) isclear = true;    ///if there no enemy the map is clear
        //Check to move to next map
        if(isclear&&player.intersects(gatetoNextMap)){   //Move to nextMap
            gsm.nextMap();            // move to next map
            gsm.setNextMap(true);     //It mean the player is being move to the NEXT map
            gsm.setPlayer(player);    //set player to next map
            gsm.setNextMap(false);
        }
        if(player.intersects(gateToPreviousMap)){
            gsm.previousMap();     //gsm.getNextMap is false in defalt so setPlayer will set player to returnPos
            gsm.setPlayer(player);
        }
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
        tilemap3.draw(g);
        for (Enemy enemy : enemies) {
//		    System.out.println(enemies.get(0).getPosX()+" "+ enemies.get(0).getPosY());
            enemy.render(g);
        }
        player.render(g);
        hud.render(g);
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

