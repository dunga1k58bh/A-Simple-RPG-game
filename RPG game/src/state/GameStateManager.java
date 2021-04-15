package state;


import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
public class GameStateManager {
	
     
   public ArrayList<GameState> gameStates;
   public int currentState;   
     
     public GameStateManager() {
         gameStates= new ArrayList<GameState>();
         gameStates.add(new MenuState(this));
         currentState = 0;
         
     }
     public void setState(int state) {
    	 currentState = state;
    	 gameStates.get(currentState).init();
    	 
     }

     public void update() {
    	gameStates.get(currentState).update();
     }
     public void draw(GraphicsContext g) {
    	 gameStates.get(currentState).draw(g);
     }
     public void keyPressed( KeyEvent k) {
    	gameStates.get(currentState).keyPressed(k); 
     }
     public void keyReleased(KeyEvent k) {
    	gameStates.get(currentState).keyReleased(k);
     }
     public void keyType(KeyEvent k) {
     	gameStates.get(currentState).keyReleased(k);
      }
}
