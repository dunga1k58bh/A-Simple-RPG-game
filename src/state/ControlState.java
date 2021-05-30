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

public class ControlState extends GameState{
    Image image;
    private int currentOption= 1;
    private Font font,titleFont,descriptionFont ;
    private int PlayState=1,
            SettingState=2;
    private  String[] options;
    private  String[] keycontrol;
    public ControlState(GameStateManager gsm){
        super(gsm);
        try {
            image = new Image(new FileInputStream("res/Menu/menuBg.png"));
            font = Font.loadFont("file:res/Font/njnaruto.ttf", 48);
            titleFont = Font.loadFont("file:res/Font/njnaruto.ttf", 100);
            descriptionFont = Font.loadFont("file:res/Font/GameFont.otf", 30);

        }catch (Exception e){
            e.printStackTrace();
        }
        options = new String[]{
                "BACK",
        };
        keycontrol = new String[]{
             "MOVE       : left right",
             "JUMP      : up",
             "ATTACK   : q",
             "SKILL 1   : w",
             "SKILL 2   : e",
             "Use HP    : num 1",
             "Use Mp    : num 2",
        };

    }

    @Override
    public void setPlayer(Player player) {

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(GraphicsContext g) {
        //draw backgound;
        g.drawImage(image, 0, 0, Main.width, Main.height);
        //draw GameTitle
        g.setStroke(Color.BLACK);
        g.setFill(Color.DEEPSKYBLUE);
        g.setFont(titleFont);
        g.strokeText("Control Key",Main.width/2 -200,Main.height/4);
        g.fillText("Control Key",Main.width/2 -200,Main.height/4);
        int dy = 0;
        g.setFont(descriptionFont);
        g.setFill(Color.BLACK);
        for (int i = 0; i< keycontrol.length ; i++){
            g.strokeText(keycontrol[i], Main.width/2 - 200,Main.height/2  +dy- 50);
            g.fillText(keycontrol[i], Main.width/2 - 200,Main.height/2  +dy-50);
            dy+= 35;
        }

        //draw options
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
        }
        if (k.getCode() ==KeyCode.DOWN){
            currentOption = currentOption +1;
            if (currentOption > options.length) currentOption = options.length;
        }
        //seliction
        if (k.getCode() == KeyCode.ENTER){
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
