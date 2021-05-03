package state.map;

import application.Main;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;
import tilemap.TileMap;

public class Map1 extends GameState {

    //BackGround    Image bg= new Image("/res/")
    private  Player player;
    private TileMap map1;
    public Map1(GameStateManager gsm){
        super(gsm);
        player = new Player();
        map1 = new TileMap(48);
        map1.loadMap("res/Map/Map1.map");
        map1.loadTileSet("Map/TileSet.png");
        map1.setPos(0,0);
    }
    @Override
    public void init() {

    }

    @Override
    public void update() {
        map1.setPos(Main.width/2 -player.getPosX(), Main.height/2-player.getPosY());
    }

    @Override
    public void draw(GraphicsContext g) {
        map1.draw(g);
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
