package state;


import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
public class GameStateManager {
	
     
   private ArrayList<GameState> gameStates;
   private int currentState;   
     
     public GameStateManager() {
         gameStates= new ArrayList<GameState>();
         gameStates.add(new MenuState(this));
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
     public void keyPressed( int k) {
    	gameStates.get(currentState).keyPressed(k); 
     }
     public void keyReleased(int k) {
    	gameStates.get(currentState).keyReleased(k);
     }
     
}
