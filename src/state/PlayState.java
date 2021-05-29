package state;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class PlayState extends GameState{

    //OVERALL DESCRIPTION:
    //Playstate owns unique player object and several maps obj. player obj will be passed to map obj for control
    private Player player ;
    private int currentMap;
    private  ArrayList<GameState> mapStates ;
    public PlayState(GameStateManager gsm){
        super(gsm);
        this.currentMap = gsm.getCurrentMap();
        this.mapStates = gsm.getmapStates();
        player = new Player();
        mapStates.get(currentMap).setPlayer(player);
    }

    @Override
    public void setPlayer(Player player) {

    }

    public void nextMap(){
    }

    @Override
    public void tick() {
        this.currentMap = gsm.getCurrentMap();
        System.out.println(currentMap);
        mapStates.get(currentMap).tick();
    }

    @Override
    public void render(GraphicsContext g) {
        mapStates.get(currentMap).render(g);
          //g.strokeText("PlayState",300,300);
    }


    @Override
    public void keyPressed(KeyEvent k) {
        mapStates.get(currentMap).keyPressed(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {
       mapStates.get(currentMap).keyTyped(k);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        mapStates.get(currentMap).keyReleased(k);
    }
}
