package state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class PlayState extends GameState{
    public PlayState(GameStateManager gsm){
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
          g.strokeText("PlayState",300,300);
    }

    @Override
    public void keyPressed(KeyEvent k) {

    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
}
