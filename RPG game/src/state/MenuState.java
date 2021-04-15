package state;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MenuState extends GameState{
    private Image image;
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		image = new Image("Menu/menubg.jpg");
	    
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(image, 0, 0,Main.width, Main.height);
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getCode()==KeyCode.UP) {
			System.out.println("up");
		}
		if (k.getCode()==KeyCode.DOWN) {
			System.out.println("down");
		}
		if (k.getCode()==KeyCode.ENTER) {
			System.out.println("Enter");
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyType(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

}
