package state;

import Audio.Music;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.map.Map1;

public class PlayState extends GameState{

    //OVERALL DESCRIPTION:
    //Playstate owns unique player object and several maps obj. player obj will be passed to map obj for control
    private Player player = new Player();

    private Map1 map1 = new Map1(gsm);
    public PlayState(GameStateManager gsm){
        super(gsm);
        map1.setPlayer(player); //pass player obj to map obj for control
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        map1.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        map1.render(g);
          //g.strokeText("PlayState",300,300);
    }


    @Override
    public void keyPressed(KeyEvent k) {
        map1.keyPressed(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {
        map1.keyTyped(k);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        map1.keyReleased(k);
    }
}
