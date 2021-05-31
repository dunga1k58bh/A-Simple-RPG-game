package state.map;

import java.io.FileInputStream;

import Audio.Music;
import application.Main;
import entity.somethings.Gate;
import entity.somethings.HUD;
import entity.Player;
import entity.enemies.Boss;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;
import tilemap.TileMap;

public class Map4 extends GameState {

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
    private Boss boss;
    private int hardLevel;
    //Music BackGround
    private  Music bgMusic;

    //starting position of player on map (On-map coord)
    public final double playerStartingPosX = 80;//TODO
    public final double playerStartingPosY = 200; //TODO
    //position of player on map if return the old Map
    private final double playerReturnPosX =  1150;
    private final double playerReturnPosY =  750;

    //Is this map clear ?
    public boolean isclear;

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    public Map4(GameStateManager gsm){
        super(gsm);
        try {
            bg= new Image(new FileInputStream("res/bg/bgMap4.png"));
            bgMusic = new Music("res/Audio/bgMusic5.mp3");
            tilemap = new TileMap(48);
            tilemap.loadTileSet("Map/TileSet.png");
            tilemap.loadMap("res/Map/Map4.map");
        }catch (Exception e){
            e.printStackTrace();
        }
        
        hardLevel = gsm.getHardLevel();
        tilemap.setPos(camPosX,camPosY);
        //generateEnemies();

        //Set Cycle music background and Play
        bgMusic.setCycle();
        bgMusic.setVolume(0.1);
        //the gate
        gateToPreviousMap = new Gate(tilemap);
        gateToPreviousMap.setPos(24,288);
        gatetoNextMap = new Gate(tilemap);
        gatetoNextMap.setPos(1368,864);
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
        // pass player to TileMap
        player.setTileMap(tilemap);
        hud = new HUD(player);
        generateEnemies();
        bgMusic.startMusic();
    }

    private void generateEnemies() {
        boss = new Boss(tilemap, hardLevel);
        boss.setPosition(800,600);
        boss.setTarget(player);
    }


    @Override
    public void tick(){                           //Tick of game
        if (player.getFacing() == 1) {
            camPosX = player.getPosX() - Main.width*1/3;
        }
        else {
            camPosX = player.getPosX() - Main.width*2/3;
        }
        camPosY = player.getPosY() - Main.height*2/3;
        tilemap.setPos(camPosX,camPosY);
        tilemap.tick();
        if (player.getKey().skill1 == 1) {
        	if (player.getSkill1().intersects(boss)) {
        		boss.getHit(player.getSkill1().getDamage());
        	}
        }
        if (player.getKey().skill2 == 1) {
        	if (player.getSkill2().intersects(boss)) {
        		boss.getHit(player.getSkill2().getDamage());
        	}
        }
        if (player.getKey().attack == 1 && player.getLock3() == true) {
        	if (player.getBox().intersects(boss)) {
            	boss.getHit(player.getBox().getDamage());
            }
        }
        boss.tick();
        if (player.intersects(boss.getLaserAttack())&&boss.getLaserAttack().getBeingUsed()){
            player.getHit(boss.getLaserAttack().getDamage());
        }
        if (player.intersects(boss)){
            player.getHit(20);
        }
        player.tick();
        if(player.isDead()){                    //if player dead revival him in the pos begin and resumoner enemy
            player.setDead(false);
            gsm.setNextMap(true);
            setPlayer(player);
            player.setHP(player.maxHP);
            player.setMP(player.maxMP);
            gsm.setNextMap(false);
        }
        //Check to open gate
        tilemap.OpenNextMap(boss.isDead()?0:1);
        changeMap();
    }
    public void changeMap(){
        if(boss.isDead()) isclear = true;    ///if there no enemy the map is clear
        //Check to move to next map
        if(isclear&&player.intersects(gatetoNextMap)){//Move to nextMap
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
        tilemap.draw(g);
        boss.render(g);
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

