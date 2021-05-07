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
    private Image bg= new Image("bg/bgMap1.png");
    private Player player; //Both player and tilemap can move, map can move then player stays, map can't move and player will move
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
    public void tick() {
        map1.setPos(0,0);
        map1.tick();
        player.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(bg,0,0, Main.width, Main.height);
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
