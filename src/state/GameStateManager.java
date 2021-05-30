package state;


import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
public class GameStateManager {

   private int hardlevel;
   protected GameState[] gameStates; // State mutilple choice
   protected static ArrayList<GameState> mapStates;  // Map State
   private int currentState;
   private int currentMap;
   private boolean nextMap;  // if not it previous
     public GameStateManager()  {
    	 hardlevel = 1;
    	 gameStates = new  GameState[5];
         mapStates = new ArrayList<>();
         gameStates[0] = new MenuState(this);       // MenuState is creat first number 0
                                                          // number 1  Playstate will be creat in MenuState// number 2 Setting is same
         gameStates[3] = new ControlState(this);
         gameStates[4] = new ThanksState(this);     //Thanks is creat number 3
         currentMap = 0;
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
     public void setCurrentMap(int i){
         this.currentMap = i;
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

     public int getHardLevel() {
    	 return hardlevel;
     }
     
     public void setHardLevel(int hardlevel){
         this.hardlevel = hardlevel;
     }

     public void tick() {
    	gameStates[currentState].tick();
     }
     public void render(GraphicsContext g) {
    	 gameStates[currentState].render(g);
     }
     public void keyPressed( KeyEvent k) {
    	gameStates[currentState].keyPressed(k);
     }
     public void keyReleased(KeyEvent k) {
    	gameStates[currentState].keyReleased(k);
     }
     public void keyTyped(KeyEvent k) {
     	gameStates[currentState].keyTyped(k);
      }
}
