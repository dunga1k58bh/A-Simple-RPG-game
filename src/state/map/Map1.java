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

    //OVERALL DESCRIPTION:
    //this class owns tilemap obj and stores a reference to player object which playstate owns. Playstate will pass player obj to this class.
    //this class also owns player's on-map cord
    //this class owns camera position

    private Image bg= new Image("bg/bgMap1.png");

    //only reference
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move

    private TileMap tilemap1;

    //starting position of player on map (On-map coord)
    public final double playerStartingPosX = 300; //TODO
    public final double playerStartingPosY = 100; //TODO

//    //player's on-map position
//    private double playerPosX = playerStartingPosX;
//    private double playerPosY = playerStartingPosY; // they are equal because camera is 0 0 on construction

    //Camera position (On-map coord)
    private double camPosX = 0;
    private double camPosY = 0;

    //What should i comment? the names tell em'all ?
    //Lol, kidding. These are the coordinate camera will move to BASED ON PLAYER POSITION!
    private double newCamPosX = 0;
    private double newCamPosY = 0;

    private final double camSpeed = 0.8;

    public Map1(GameStateManager gsm){
        super(gsm);
        tilemap1 = new TileMap(48);
        tilemap1.loadTileSet("Map/TileSet.png");
        tilemap1.loadMap("res/Map/Map1.map");
        tilemap1.setPos(camPosX,camPosY);
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setPosX(playerStartingPosX);
        player.setPosY(playerStartingPosY);
        //Vứt TileMap cho player
        player.setTileMap(tilemap1);
    }

    @Override
    public void init() {

    }
    @Override
    public void tick() {
//        playerPosX += player.getDx();
//        playerPosY += player.getDy();
        //Lấy luôn posX luôn vì đã cho 2 cái giống nhau rồi
        newCamPosX = player.getPosX() - Main.width*1/3;
        newCamPosY = player.getPosY() - Main.height*2/3;
        camPosX += (newCamPosX - camPosX)*camSpeed;
        camPosY += (newCamPosY - camPosY)*camSpeed;
        //Đoạn này Player chỉ cần di chuyển thôi Map tự biết lúc nào dừng
        tilemap1.setPos(camPosX,camPosY);
//        if ((result & 0b00000010) == 0b00000010) { //map cannot move along X
//            player.moveX();
//        }
//        if ((result & 0b00000001) == 0b00000001) { //map cannot move along Y
//            player.moveY();
//        }
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
