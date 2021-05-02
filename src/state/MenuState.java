package state;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuState extends GameState{
    private Image image;
    private  String[] options;
    private int currentOption= 1;
	private Font font,titleFont ;
	private int PlayState=1,
	            LoadState=2,
	            AboutState=3;

	public MenuState(GameStateManager gsm) {
		super(gsm);
//		image = new Image("Menu/menubg.jpg");
		font = Font.loadFont("file:res/Font/njnaruto.ttf",48);
		titleFont = Font.loadFont("file:res/Font/njnaruto.ttf",100);
//
	    options = new String[]{
				"Play",
				"Load",
				"About",
				"Exit",
		};

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
		//draw backgound;
		g.drawImage(image, 0, 0, Main.width, Main.height);
        //draw GameTitle
		g.setStroke(Color.BLACK);
		g.setFill(Color.DEEPSKYBLUE);
		g.setFont(titleFont);
		g.strokeText("Ninja ...",Main.width/2 -100,Main.height/4);
		g.fillText("Ninja...",Main.width/2 -100,Main.height/4);
		//draw options
		g.setFont(font);
		int dy=0;
		g.setFont(font);
		for (int i = 0; i< options.length ; i++){
			if(i+1 == currentOption) {
				g.setStroke(Color.BLACK);
				g.setFill(Color.RED);
			}
			else {
				g.setStroke(Color.BLACK);
				g.setFill(Color.YELLOW);
			}
			g.strokeText(options[i], Main.width/2 - 50,Main.height/2  +dy);
			g.fillText(options[i], Main.width/2 - 50,Main.height/2  +dy);
			dy+= 75;
		}
	}
	public void keyPressed(KeyEvent k) {
		//Control
		if (k.getCode()== KeyCode.UP) {
			currentOption = currentOption - 1;
			if (currentOption < 1) currentOption = 1;;
			System.out.println("up");
		}
		if (k.getCode() ==KeyCode.DOWN){
			currentOption = currentOption +1;
			if (currentOption > options.length) currentOption = options.length;
		}
		//seliction
		if (k.getCode() == KeyCode.ENTER){
			if (currentOption ==3+1) System.exit(0);
			//Select Current Option
			gsm.setState(currentOption);
		}
	}

	@Override
	public void keyReleased(KeyEvent k){
		// TODO Auto-generated method s
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method s

	}

}
