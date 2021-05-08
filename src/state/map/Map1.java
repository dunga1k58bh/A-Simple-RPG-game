package state.map;

import application.Main;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;
import tilemap.TileMap;

public class Map1 extends GameState {
    //this class own tile map obj and store a reference to player object which playstate owns
    private Image bg= new Image("bg/bgMap1.png");

    //only reference
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move

    private TileMap tilemap1;

    //starting position of player on map (On-map cord)
    public final double playerStartingPosX = 300; //TODO
    public final double playerStartingPosY = 500; //TODO

    //player's on-map position
    private double playerPosX = playerStartingPosX;
    private double playerPosY = playerStartingPosY; // they are equal because camera is 0 0 on construction

    //Camera position (On-map cord)
    private double camPosX = 0;
    private double camPosY = 0;

    public Map1(GameStateManager gsm){
        super(gsm);
        tilemap1 = new TileMap(48);
        tilemap1.loadMap("res/Map/Map1.map");
        tilemap1.loadTileSet("Map/TileSet.png");
        tilemap1.setPos(camPosX,camPosY);
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setPosX(playerStartingPosX);
        player.setPosY(playerStartingPosY);
    }

    @Override
    public void init() {

    }
    @Override
    public void tick() {
        playerPosX += player.getDx();
        //System.out.println(playerPosX);
        playerPosY += player.getDy();
        //System.out.println(playerPosY);
        tilemap1.tick();
        player.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
        tilemap1.draw(g);
        player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        player.keyIn(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {
        player.keyIn(k);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        player.keyIn(k);
    }
}
