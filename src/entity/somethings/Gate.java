package entity.somethings;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tilemap.TileMap;

import java.io.FileInputStream;

public class Gate extends Entity {
    Image image;
    private double posLinkX;
    private double posLinkY;
    public Gate(TileMap tileMap){
        super(tileMap);
        try {
            image = new Image(new FileInputStream("res/Map/gate.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        setEntityBoxSize(48,192);
    }
    public void setPosLinkX(double x, double y){
        posLinkX = x;
        posLinkY = y;
    }


    @Override
    public void render(GraphicsContext graphicsContext) {
        setMapPosittion();
        graphicsContext.drawImage(image,posX-xmap-24,posY-ymap-192);
    }

    @Override
    public void tick() {

    }
}
