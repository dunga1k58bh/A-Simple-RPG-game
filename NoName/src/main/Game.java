package main;



import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import state.GameStateManager;



public class Game extends Application {
	int x=0,y=0;
	//kich thuoc cua scene
	public static final int width = 1000;
	public static final int height = 600;
    //gameLoop
	public boolean running = false;
    private double timeLast ;
    private int countFrame = 0;
	private int oldFrame = 0;
	
	//GameStateManager
	GameStateManager gsm;
	
	// image
	private Image image ;
	private GraphicsContext g,g2;
	
	//
	Group root;
	Canvas canvas;
	Scene gameScene;
	
	//Ham start cua javafx tu chay theo main 
	@Override
	public void start(Stage gameStage) {
	
		
       
        
		init();
		//Loop game nhe :Ơ
		AnimationTimer gameLoop = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
	            x++;
	            y++;
				run();
				
			}
		};
		gameLoop.start();
		//stage
		gameStage.initStyle(StageStyle.DECORATED);
		gameStage.setTitle("No Name");
		gameStage.setScene(gameScene);
		
	    gameStage.show();
	}
	
	@Override // Khoi tao 
	public void init() {
		//Statge
		Group root = new Group();
	    gameScene = new Scene(root,1000,600);
		gameScene.setFill(Color.GREENYELLOW);
		
		
	    canvas=new Canvas(width, height);
		root.getChildren().add(canvas);
		//Fps
		running  = true;
		timeLast = System.currentTimeMillis();
		countFrame = 0;
		oldFrame = 0;
		//GameStateManager
		gsm = new GameStateManager();
		//
		image = new Image("/map/nj.png");
	}
	//1 lan chay 
	public void run()  {
		//GameFPS 
		countFrame++;//FPS game
		double t = System.currentTimeMillis();
		if (t-timeLast >1000) {
			timeLast =t;
			if(countFrame != oldFrame) {
			System.out.println(countFrame);
			oldFrame = countFrame;
			}
			countFrame=0;
		}
		
		update();
		draw();
		
		drawToScreen();

	}
	public void update() {
		gsm.update();
	}
	public void draw() {
		gsm.draw(g);
	}
	public void drawToScreen() {
		g2 = canvas.getGraphicsContext2D();

		g2.drawImage(image, x, y);
		
	}
	public void prepare() {
		g2.getFill();
		g2.clearRect(0, 0, width, height);
		
	}
	//Main 
	public static void main(String args[]) {
	
        launch(args);
       
	}

}
