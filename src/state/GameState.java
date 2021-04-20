package state;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;



// tao lop triu tuong GameState , ve sau moi state se la mot Parent(chua nhieu node) trong Scene graph
public abstract class GameState {        
	@SuppressWarnings("unused")
	protected GameStateManager gsm;
	public GameState(GameStateManager gsm) {
		this.gsm=gsm;
	}

    public abstract void  init();
    public abstract void update();
    public abstract void draw(GraphicsContext g);
    public abstract void keyPressed( KeyEvent k);
    public abstract void keyTyped( KeyEvent k);
    public abstract void keyReleased(KeyEvent k);
}
