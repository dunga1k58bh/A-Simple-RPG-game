package state;


import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.map.*;
import java.util.ArrayList;
public class GameStateManager {

   private double hardlevel;
   protected static ArrayList<GameState> gameStates; // State mutilple choice
   protected static ArrayList<GameState> mapStates;  // Map State


   private int currentState;
   private int currentMap;
   private boolean nextMap;  // if not it previous
     public GameStateManager()  {

         gameStates= new ArrayList<>();
         mapStates = new ArrayList<>();
         currentMap = 4;
         gameStates.add(new MenuState(this));// 0
         gameStates.add(new PlayState(this));// 1
         gameStates.add(new SettingState(this));//
         currentState = 0;
     }
     //for basic control
     public void setState(int state) {
    	 currentState = state;
     }

     //control the Mapstate
     public void nextMap(){
         currentMap++;
     }
     public void previousMap(){currentMap--;}
     public ArrayList<GameState> getmapStates(){
         return mapStates;
     }
     public int getCurrentMap(){
         return currentMap;
     }
     public void setPlayer(Player player){
         mapStates.get(currentMap).setPlayer(player);
     }
     public void setNextMap(boolean b){
         this.nextMap = b;
     }
     public boolean getNextMap(){
         return  nextMap;
     }

     //
    public void setHardlevel(double hardlevel){
         this.hardlevel = hardlevel;
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
