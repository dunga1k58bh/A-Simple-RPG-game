package state;

import application.Main;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class SettingState extends GameState{
    private Image image;
    private int currentOption= 1;
    private Font font,titleFont ;
    private int PlayState=1,
            SettingState=2;
    private  String[] options;
    public SettingState(GameStateManager gsm){
        super(gsm);
        try {
            image = new Image(new FileInputStream("res/Menu/menuBg.png"));
            font = Font.loadFont("file:res/Font/njnaruto.ttf", 48);
            titleFont = Font.loadFont("file:res/Font/njnaruto.ttf", 100);
        }catch (Exception e){
            e.printStackTrace();
        }
        options = new String[]{
                "NORMAL",
                "HARD",
        };
    }

    @Override
    public void setPlayer(Player player) {

    }

    @Override
    public void tick() {
         if(currentOption == 1){
             gsm.setHardLevel(1);       //mutilple to status of enemy
         }
         if(currentOption == 2){
             gsm.setHardLevel(2);
         }

    }

    @Override
    public void render(GraphicsContext g) {
        //draw backgound;
        g.drawImage(image, 0, 0, Main.width, Main.height);
        //draw GameTitle
        g.setStroke(Color.BLACK);
        g.setFill(Color.DEEPSKYBLUE);
        g.setFont(titleFont);
        g.strokeText("Setting",Main.width/2 -100,Main.height/4);
        g.fillText("Setting",Main.width/2 -100,Main.height/4);
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

    @Override
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
            //Select Current Option
            gsm.setState(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
}
