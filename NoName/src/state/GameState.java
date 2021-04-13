package state;



import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;



// tao lop triu tuong GameState , ve sau moi state se la mot Parent(chua nhieu node) trong Scene graph
public abstract class GameState extends Parent {        
	@SuppressWarnings("unused")
	private GameStateManager gsm;
	public GameState(GameStateManager gsm) {
		this.gsm=gsm;
	}

    public abstract void  init();
    public abstract void update();
    public abstract void draw(GraphicsContext g);
    public abstract void keyPressed( int k);
    public abstract void keyReleased(int k);
}
