package main;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;



public class Game extends Application {
	//kich thuoc cua scene
	public static final int width = 1000;
	public static final int height = 600;
    //gameLoop
	public boolean running = false;
    private double timeLast ;
    private int countFrame = 0;
	private int oldFrame = 0;
	
	
	public void start(Stage gameStage) {
		
		
		Group root = new Group();
		Scene gameScene = new Scene(root,1000,600);
		gameScene.setFill(Color.GREENYELLOW);
		gameStage.initStyle(StageStyle.DECORATED);
		gameStage.setTitle("No Name");
		gameStage.setScene(gameScene);
		
	    Canvas canvas=new Canvas(width, height);
		root.getChildren().add(canvas);
		
        timeLast = System.currentTimeMillis();
        countFrame = 0;
		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
        
		init();
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),
				new EventHandler<ActionEvent>() 
				{
				    public void handle(ActionEvent ae) {
				    run();
				    }
		
				});
		
		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	    gameStage.show();
	}
	
	@Override
	public void init() {
		running  = true;
		timeLast = System.currentTimeMillis();
		countFrame = 0;
		oldFrame = 0;
	}
	public void run()  {
		//GameFPS 
		countFrame++;
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
		render();
		draw();
	}
	public void update() {
		
	}
	public void render() {
		
	}
	public void draw() {
		
	}
	
	public static void main(String args[]) {
        launch(args);
		
	}

}
