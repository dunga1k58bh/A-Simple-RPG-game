package tilemap;

import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class TileMap {
    //Pos
    private double x;
    private double y;
    private double xmin,ymin,xmax,ymax;
    //TileSet
    private int tileSize;
    private Image tileset;
    public Tile[][] tiles;
    private int tilesetCol;
    private int tilesetRow;
    //TileMap
    private int map[][];
    private int width;
    private int height;
    private int mapRow;
    private int mapCol;
    //For draw
    private int numTileWidth;
    private int numTileHeight;
    private int beginDrawHeight;
    private int beginDrawWidth;
    private double camSpeed;
    public TileMap(int tileSize){
        this.tileSize = tileSize;
       numTileWidth = Main.width/tileSize +2;
       numTileHeight = Main.height/tileSize +2;
       camSpeed = 0.8;

    }
    public void loadTileSet (String s){
        tileset = new Image(s);
        tilesetRow = (int) tileset.getHeight()/tileSize;
        tilesetCol = (int) tileset.getWidth()/tileSize;
        System.out.println(tilesetRow+" "+tilesetCol);
        tiles = new Tile[tilesetRow][tilesetCol];
        Image temp;
        int type = 0;
        for (int i = 0;i<tilesetRow;i++){
            for (int j = 0;j<tilesetCol;j++){
                temp = getCropImage(tileset,j*tileSize,i*tileSize,tileSize,tileSize);
                tiles[i][j] = new Tile(temp,type);
            }
            type++;
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getType(int row, int col){
        int rc = map[row][col];
        int r= rc/tilesetCol;
        int c= rc%tilesetCol;
        return tiles[r][c].getType();
    }

    public void loadMap(String s){
        try {
            FileInputStream in = new FileInputStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            mapCol = Integer.parseInt(br.readLine());
            mapRow = Integer.parseInt(br.readLine());
            map = new int[mapRow][mapCol];
            width = mapCol* tileSize;
            height = mapRow*tileSize;
            xmin = Main.width - width;
            xmax = 0;
            ymin = Main.height - height;
            ymax = 0;
            String del ="\s+";
            for (int row =0;row <mapRow;row ++){
                String line = br.readLine();
                if (line == null) break;
                String[] tokens = line.split(del);
                for (int col = 0 ; col < mapCol;col ++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                    System.out.print(map[row][col]+" ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Image getCropImage(Image image,double x, double y,double tagetWidth, double tagetHeight){
        Rectangle2D cropArea = new Rectangle2D(x,y,tagetWidth,tagetHeight);
        ImageView imageView= new ImageView(image);
        imageView.setViewport(cropArea);
        imageView.setFitWidth(tagetWidth);
        imageView.setFitHeight(tagetHeight);
        return  imageView.snapshot(null,null);
    }
    public void setPos(double x, double y){
        this.x += (x - this.x) * camSpeed;
        this.y += (y - this.y) * camSpeed;
        if (this.x<xmin) this.x = xmin;
        if (this.x>xmax) this.x = xmax;
        if (this.y<ymin) this.y = ymin;
        if (this.y>ymax) this.y = ymax;
        beginDrawWidth = (int)-this.x / tileSize;
        beginDrawHeight= (int)-this.y /tileSize;

    }
    public void draw(GraphicsContext g){
        for (int row = beginDrawHeight;row <beginDrawHeight+numTileHeight;row ++) {
                if (row<0) row = 0;
                if(row>=mapRow) break;
            for (int col = beginDrawWidth; col < beginDrawWidth+numTileWidth; col++) {
                if (col<0) col = 0;
                if (col>=mapCol) break;
                if (map[row][col]==0) continue;
                int rc = map[row][col];
                int r= rc/tilesetCol;
                int c= rc%tilesetCol;
                g.drawImage(tiles[r][c].getImage(),(int)x+col*tileSize,(int)y+row*tileSize);
            }
        }
    }
}
