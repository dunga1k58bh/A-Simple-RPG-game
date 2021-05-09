package tilemap;

import application.Main;
import entity.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.*;

// ->>
public class TileMap {

    // - Gốc tọa độ là góc trên cùng bên trái của cả map
    //Pos : Vị trị bắt đầu vẽ 1 phần map(góc trên cùng bên trái)
    private double x; //SCREEN position(topleft corner) on VIRTUAL MAP
    private double y;

    private Player player;
    private double playerPosX;
    private double playerPosY;

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
    private double rowDraw;
    private double colDraw;
    private int rowBeginDraw;
    private int colBeginDraw;
    private final double camSpeed = 0.8;
    public TileMap(int tileSize) {
        this.tileSize = tileSize;
       colDraw = Main.width/tileSize +2;
       rowDraw = Main.height/tileSize +2;
       //camSpeed = 0.8;
    }
    public void setPlayer(Player player) {
        this.player=player;
    }
    //Lay tileSet (la 1 Image)
    public void loadTileSet (String s){
        tileset = new Image(s);
        tilesetRow = (int) tileset.getHeight()/tileSize;
        tilesetCol = (int) tileset.getWidth()/tileSize;
        System.out.println(tilesetRow+" "+tilesetCol);
        tiles = new Tile[tilesetRow][tilesetCol];
        Image temp;
        for (int i = 0;i<tilesetRow;i++) {
            for (int j = 0; j < tilesetCol; j++) {
                temp = getCropImage(tileset, j * tileSize, i * tileSize, tileSize, tileSize);
                tiles[i][j] = new Tile(temp, 0);
            }
        }
        for (int i = 1;i<=6;i++) tiles[0][i].setType(Tile.BlOCKDOWN);
        for (int i= 10;i<15;i++) tiles[0][i].setType(Tile.BlOCKDOWN);
        tiles[1][1].setType(Tile.DEAD);
        tiles[1][3].setType(Tile.BLOCK);
        tiles[1][4].setType(Tile.BLOCK);
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
    //file .map là 1 file 2 dòng đầu là cột n, hàng m, mảng m*n là số hiệu của các tile trong tileSet
    public void loadMap(String s){
        try {
            //Doc File
            FileInputStream in = new FileInputStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            mapCol = Integer.parseInt(br.readLine());
            mapRow = Integer.parseInt(br.readLine());
            map = new int[mapRow][mapCol];

            width = mapCol* tileSize; //Kích cỡ map
            height = mapRow*tileSize;
            xmin = 0;
            xmax = width - Main.width;
            ymin = 0;
            ymax = height - Main.height;
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

    //Cắt ảnh, gốc ảnh trên cùng bên trái,
    //(x,y) điểm bắt đầu cắt
    //(targetWidth,targetHeight) :V
    public Image getCropImage(Image image,double x, double y,double tagetWidth, double tagetHeight){
        Rectangle2D cropArea = new Rectangle2D(x,y,tagetWidth,tagetHeight);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        ImageView imageView= new ImageView(image);
        imageView.setViewport(cropArea);
        imageView.setFitWidth(tagetWidth);
        imageView.setFitHeight(tagetHeight);
        return  imageView.snapshot(params,null);
    }

    public void tick() {

    }


    //Anchor point: top left of VIRTUAL map, not screen
    //return: binary value which has 8 bit. Pay attention to the last 2 bits:
    //first bit is set to 1 if map cannot move along X
    //second bit is set to 1 if map cannot move along Y
    public int setPos(double x, double y){
        byte thisIsReturnValue = 0b00000000;
        this.x +=(x-this.x)*1;
        this.y +=(y-this.y)*1;
        //Đoạn này để đảm bảo chỉ vẽ những thứ có trong map
        if (this.x<xmin) {
            this.x = xmin;
            thisIsReturnValue |= 0b00000010;
        }
        if (this.x>xmax) {
            this.x = xmax;
            thisIsReturnValue |= 0b00000010;
        }
        if (this.y<ymin) {
            this.y = ymin;
            thisIsReturnValue |= 0b00000001;
        }
        if (this.y>ymax) {
            this.y = ymax;
            thisIsReturnValue |= 0b00000001;
        }

        //cột hàng bắt đầu vẽ
        colBeginDraw = (int)  this.x /tileSize;
        rowBeginDraw= (int)  this.y /tileSize;

        return thisIsReturnValue;
    }
    
    public void draw(GraphicsContext g){
        for (int row = rowBeginDraw;row <rowBeginDraw+rowDraw;row ++) {
                if(row>=mapRow) break;
            for (int col = colBeginDraw; col < colBeginDraw + colDraw; col++) {
                if (col>=mapCol) break;
                if (map[row][col]==0) continue;
                int rc = map[row][col];
                int r= rc/tilesetCol;
                int c= rc%tilesetCol;
                //Bản chất của 2 tham số tọa độ của hàm dưới đây là phép dịch gốc tọa độ!!
                g.drawImage(tiles[r][c].getImage(),(int)-x+col*tileSize,(int)-y+row*tileSize);
            }
        }
    }

    public double getCameraPosX() {
        return x;
    }
    public double getCameraPosY() {
        return y;
    }
}
