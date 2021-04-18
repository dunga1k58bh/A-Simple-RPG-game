package main;




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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import state.GameStateManager;




public class Game extends Application {
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
	private  Group root;
	private Canvas canvas;
	private Scene gameScene;
	
	//Gamelope
	Timeline gameLoop;
	
	//Ham start cua javafx tu chay theo main 
	@Override
	public void start(Stage gameStage) {
		init();
		//Loop game nhe :Ơ
        KeyFrame kf = new KeyFrame(
        Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>()
                {   
                    public void handle(ActionEvent ae)
                    {   
                   
                        run();
                    }
                });
            
            gameLoop.getKeyFrames().add( kf );
            gameLoop.play();
        //KeyControl    
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent k) {
        		if (k.getCode()== KeyCode.A) {
        			System.out.println("Hello");
//        		update();
        		}
        	}
		});    
		//stage    
		gameStage.initStyle(StageStyle.DECORATED);
		gameStage.setTitle("No Name");
		gameStage.setScene(gameScene);
		
	    gameStage.show();
	}
	
	@Override // Khoi tao 
	public void init() {
		//Statge
		root = new Group();
	    gameScene = new Scene(root,width,height);
		gameScene.setFill(Color.BLACK);
		
		//Lope
	    gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        //gamesceen
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
		g2 = canvas.getGraphicsContext2D();
	}
	//1 lan chay 
	public void run()  {
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
		g=canvas.getGraphicsContext2D();
		gsm.draw(g);
	}
	public void drawToScreen() {
	
		g2.clearRect(0, 0, width, height);
		g2.drawImage(image, 0, 0, width,height);	
		
	}
	//Main 
	public static void main(String args[]) {
	
        launch(args);
       
	}

}
