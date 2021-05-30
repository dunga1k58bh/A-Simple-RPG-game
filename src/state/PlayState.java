package state;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.map.*;

import java.util.ArrayList;

public class PlayState extends GameState{

    //OVERALL DESCRIPTION:
    //Playstate owns unique player object and several maps obj. player obj will be passed to map obj for control
    private Player player ;
    private int currentMap;
    private  ArrayList<GameState> mapStates ;   ///mapStates have been init in GamstateManager

    public PlayState(GameStateManager gsm){
        super(gsm);
        player = new Player();                    /// the unique Player
        this.currentMap = gsm.getCurrentMap();    /// the current Map
        this.mapStates = gsm.getmapStates();      /// the array of map
        mapStates.add(new Map1(gsm));   //0
        mapStates.add(new Map2(gsm));   //1
        mapStates.add(new Map3(gsm));   //2
        mapStates.add(new Map4(gsm));   //3
        mapStates.add(new Map5(gsm));   //4
        mapStates.get(currentMap).setPlayer(player);   ///pass the player to current MapState
    }



    @Override       //not use
    public void setPlayer(Player player) {

    }


    @Override
    public void tick() {
        this.currentMap = gsm.getCurrentMap(); // Update the current mapstate if the mapState  changes
//        System.out.println(currentMap);
          mapStates.get(currentMap).tick();
        this.currentMap = gsm.getCurrentMap();
        if (currentMap>=mapStates.size()){
            gsm.setState(4);   // move to thanksState
        }
    }

    @Override
    public void render(GraphicsContext g) {            //render the current map
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
