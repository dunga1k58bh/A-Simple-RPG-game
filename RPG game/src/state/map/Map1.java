package state.map;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.GameState;
import state.GameStateManager;

public class Map1 extends GameState {
    //BackGround    Image bg= new Image("/res/")
//
    //
    private Player player = new Player();
    public Map1(GameStateManager gsm){
        super(gsm);
    }
    @Override
    public void init() {

    }

    @Override
    public void update() {
    }

    @Override
    public void draw(GraphicsContext g) {
        player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        player.keyIn(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
}
