package tilemap;

import javafx.scene.image.Image;


public class Tile  {
    private Image image;
    private int type;

    public static final int ALLOW=0;
    public static final int BLOCK=1;
    public static final int BlOCKDOWN=2;
    public static final int DEAD =3;
//    private static final  int  ;
    public Tile(Image image, int type){
      this.image= image;
      this.type = type;
    }
    public Image getImage(){return image;}
    public int getType(){return type;}
    public void setType(int type){this.type = type;}
}
