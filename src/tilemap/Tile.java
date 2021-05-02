package tilemap;

import javafx.scene.image.Image;


public class Tile {
    private Image image;
    private int type;

    private static final int NORMAL = 0;
    private static final  int BLOCK = 1;

    public Tile(Image image, int type){
      this.image= image;
      this.type = type;
    }
    public Image getImage(){return image;}
    public int getType(){return type;}
}
