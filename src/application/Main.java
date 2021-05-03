package application;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import state.GameStateManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	//kich thuoc cua scene
	    Scene scene;
		public static final double width = 1008;
		public static final double height = 576;
		 //gameLoop
	    private double timeLast ;
	    private int countFrame = 0;
		private int oldFrame = 0;
		
		//GameStateManager
		public GameStateManager gsm =new GameStateManager();
		
		// image
//		private Image image ;
		private Canvas canvas;
		private GraphicsContext g;	
		
		//Key COntrol, Mouse Control
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		canvas= new Canvas(width,height);
		g=canvas.getGraphicsContext2D();
        
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			//Root and Scene 
			BorderPane root = new BorderPane();
			root.getChildren().add(canvas);
		    scene = new Scene(root,width,height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//KeyControl
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	public void handle(KeyEvent k) {
	        		gsm.keyPressed(k);
	        	}
			});
			scene.setOnKeyTyped(new EventHandler<>() {
				public void handle(KeyEvent k) {
					gsm.keyTyped(k);
				}
			});
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        	public void handle(KeyEvent k) {
	        		gsm.keyReleased(k);
	        	}
			});
			
			//GameLope
		    Timeline gameLoop = new Timeline();
	        gameLoop.setCycleCount( Timeline.INDEFINITE );
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
	        //end Gameloop
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
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

	}
	public void update() {
		gsm.update();
	}
	public void draw() {
		g.clearRect(0, 0, width, height);
		gsm.draw(g);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
