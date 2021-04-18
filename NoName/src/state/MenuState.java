package state;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MenuState extends GameState{
	public MenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub

		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyType(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getCode()==KeyCode.UP) {
			System.out.println("UP");
		}
		if (k.getCode()==KeyCode.DOWN) {
			System.out.println("DOWN");
		}
		if (k.getCode()==KeyCode.ENTER) {
			System.out.println("Enter");
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}





}
