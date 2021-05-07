package state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class AboutState extends GameState{
    public AboutState(GameStateManager gsm){
        super(gsm);
    }
    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(GraphicsContext g) {
        g.strokeText("AboutState",300,400);
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
