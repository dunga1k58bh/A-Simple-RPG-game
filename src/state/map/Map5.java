package state.map;

import java.io.FileInputStream;

import Audio.Music;
import application.Main;
import entity.somethings.Gate;
import entity.somethings.HUD;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;
import tilemap.TileMap;

public class Map5 extends GameState {

    //OVERALL DESCRIPTION:
    //this class owns tilemap obj and stores a reference to player object which playstate owns. Playstate will pass player obj to this class.
    //this class also owns player's on-map cord
    //this class owns camera position

    private  Image bg;

    //only reference
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move
    private  TileMap tilemap;
    private Gate gatetoNextMap;
    private Gate gateToPreviousMap;
    private HUD hud;
    //Music BackGround
    public   Music bgMusic;
    private int begin ;
    private final int stop  = 12;
    private long startTime;
    private boolean vocano;
    //starting position of player on map (On-map coord)
    public final double playerStartingPosX = 100;//TODO
    public final double playerStartingPosY = 3500; //TODO

    //Is this map clear ?
    public boolean isclear;

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    public Map5(GameStateManager gsm){
        super(gsm);
        try {
            bg= new Image(new FileInputStream("res/bg/bgMap5.png"));
            bgMusic = new Music("res/Audio/Victory-TwoStepsFromHell.mp3");
            tilemap = new TileMap(48);
            tilemap.loadTileSet("Map/TileSet.png");
            tilemap.loadMap("res/Map/Map5.map");
        }catch (Exception e){
            e.printStackTrace();
        }
        tilemap.setPos(camPosX,camPosY);
        //generateEnemies();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.4);
        //the gate
        gateToPreviousMap = new Gate(tilemap);
        gateToPreviousMap.setPos(24,1680);
        gatetoNextMap = new Gate(tilemap);
        gatetoNextMap.setPos(1176,432);
        begin = tilemap.getMapRow()-2;

    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
        player.setHP(100);
        //System.out.println("playerSet: " + player);
        if(!isclear) {
            player.setPosX(playerStartingPosX);
            player.setPosY(playerStartingPosY);
        }
        // pass player to TileMap
        player.setTileMap(tilemap);
        hud = new HUD(player);
        bgMusic.startMusic();
        bgMusic.jumpTo(40);
        vocano = false;
        startTime = System.nanoTime();
    }
    @Override
    public void tick(){
        long elapsed1 = (System.nanoTime() - startTime) / 1000000;
        if(elapsed1> 5000){
            vocano = true;
        }
        if(vocano) {
            long elapsed = (System.nanoTime() - startTime) / 1000000;
            if (elapsed > 600/gsm.getHardLevel()) {
                tilemap.changeMap(begin);
                if (begin >= stop) begin--;
                if (begin == stop) isclear = true;
                startTime = System.nanoTime();
            }
        }
        //Tick of game
        if (player.getFacing() == 1) {
            camPosX = player.getPosX() - Main.width*1/3;
        }
        else {
            camPosX = player.getPosX() - Main.width*2/3;
        }
        camPosY = player.getPosY() - Main.height*3/7;
        tilemap.setPos(camPosX,camPosY);
        tilemap.tick();
        player.tick();
        if(player.isDead()){
            tilemap.loadMap("res/Map/Map5.map");
            begin = tilemap.getMapRow()-2;
            player.setPos(playerStartingPosX,playerStartingPosY);
            player.setDead(false);
            vocano = false;
            startTime = System.nanoTime();
        }
        //Check to open gate
        tilemap.OpenNextMap(0);
        changeMap();
    }
    public void changeMap(){
        isclear = true;    ///if there no enemy the map is clear
        //Check to move to next map
        if(isclear&&player.intersects(gatetoNextMap)){//Move to nextMap
            bgMusic.pauseMusic();
            gsm.nextMap();            // move to next map
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
        tilemap.draw(g);
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
    public void keyTyped(KeyEvent k) { player.keyIn(k); }
    @Override
    public void keyReleased(KeyEvent k) {
        player.keyIn(k);
    }

}

