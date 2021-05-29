package state;


import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.map.*;
import java.util.ArrayList;
public class GameStateManager {
	
     
   protected static ArrayList<GameState> gameStates;
   protected static ArrayList<GameState> mapStates;
   private int currentState;
   private int currentMap;
     public GameStateManager()  {

         gameStates= new ArrayList<>();
         mapStates = new ArrayList<>();
         mapStates.add(new Map1(this));//////0
         mapStates.add(new Map2(this));//////1
         gameStates.add(new MenuState(this));// 0
         gameStates.add(new PlayState(this));// 1
         gameStates.add(new SettingState(this));//2
         currentMap = 0;
         currentState = 1;
     }
     public void setState(int state) {
    	 currentState = state;
     }
     public void addMap(GameState state){
         mapStates.add(state);
     }
     public void nextMap(){
         currentMap=1;
     }
     public ArrayList<GameState> getmapStates(){
         return mapStates;
     }
     public int getCurrentMap(){
         return currentMap;
     }
     public void setPlayer(Player player){
         mapStates.get(currentMap).setPlayer(player);
     }

     public void tick() {
    	gameStates.get(currentState).tick();
     }
     public void render(GraphicsContext g) {
    	 gameStates.get(currentState).render(g);
     }
     public void keyPressed( KeyEvent k) {
    	gameStates.get(currentState).keyPressed(k); 
     }
     public void keyReleased(KeyEvent k) {
    	gameStates.get(currentState).keyReleased(k);
     }
     public void keyTyped(KeyEvent k) {
     	gameStates.get(currentState).keyTyped(k);
      }
}
