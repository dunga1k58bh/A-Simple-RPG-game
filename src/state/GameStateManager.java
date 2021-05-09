package state;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import state.map.*;
import java.util.ArrayList;
public class GameStateManager {
	
     
   protected static ArrayList<GameState> gameStates;
   private int currentState;

     public GameStateManager()  {

         gameStates= new ArrayList<>();
         gameStates.add(new MenuState(this));//0
         gameStates.add(new PlayState(this));//1
         gameStates.add(new LoadState(this));//2
         gameStates.add(new AboutState(this));//3
         //gameStates.add(new Map1(this));

         currentState = 1;
         
     }
     public void setState(int state) {
    	 currentState = state;
     }
     public void tick() {
    	gameStates.get(currentState).tick();
     }
     public void draw(GraphicsContext g) {
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
