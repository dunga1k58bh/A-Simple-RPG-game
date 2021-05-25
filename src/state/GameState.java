package state;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;



// tao lop triu tuong GameState
public abstract class GameState {        
	@SuppressWarnings("unused")
	protected GameStateManager gsm;
	public GameState(GameStateManager gsm) {
		this.gsm=gsm;
	}
    public abstract void  init();
    public abstract void tick();
    public abstract void render(GraphicsContext g);
    public abstract void keyPressed( KeyEvent k);
    public abstract void keyTyped( KeyEvent k);
    public abstract void keyReleased(KeyEvent k);
}
