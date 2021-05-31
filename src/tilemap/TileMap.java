package tilemap;

import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.*;

// ->>
public class TileMap {

    // - Gá»‘c tá»�a Ä‘á»™ lÃ  gÃ³c trÃªn cÃ¹ng bÃªn trÃ¡i cá»§a cáº£ map
    //Pos : Vá»‹ trá»‹ báº¯t Ä‘áº§u váº½ 1 pháº§n map(gÃ³c trÃªn cÃ¹ng bÃªn trÃ¡i)
    private double x; //SCREEN position(topleft corner) on VIRTUAL MAP
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
    private int rowDraw;
    private int colDraw;
    private int rowBeginDraw;
    private int colBeginDraw;
    private final double camSpeed = 0.05;




    public TileMap(int tileSize) {
        this.tileSize = tileSize;
       colDraw = (int)Main.width/tileSize +2;
       rowDraw = (int)Main.height/tileSize +2;
       //camSpeed = 0.8;
    }
    //Lay tileSet (la 1 Image)
    public void loadTileSet (String s){
        tileset = new Image(s);
        tilesetRow = (int) tileset.getHeight()/tileSize;
        tilesetCol = (int) tileset.getWidth()/tileSize;
        //System.out.println(tilesetRow+" "+tilesetCol);
        tiles = new Tile[tilesetRow][tilesetCol];
        Image temp;
        for (int i = 0;i<tilesetRow;i++) {
            for (int j = 0; j < tilesetCol; j++) {
                temp = getCropImage(tileset, j * tileSize, i * tileSize, tileSize, tileSize);
                tiles[i][j] = new Tile(temp, Tile.ALLOW);
            }
        }
        for (int i = 1;i<=21;i++) tiles[0][i].setType(Tile.BLOCK);
        tiles[1][2].setType(Tile.DEAD);
    }
    public void setTile(int row,int begincol,int endcol,int type){
        for (int i= begincol;i<=endcol;i++){
            tiles[row][i].setType(type);
        }
    }
    public void changeMap(int row){
        for (int i = 0;i < mapCol ; i++){
            map[row][i] = 24;
        }
    }
    public int getMapRow(){
        return mapRow;
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
    //file .map lÃ  1 file 2 dÃ²ng Ä‘áº§u lÃ  cá»™t n, hÃ ng m, máº£ng m*n lÃ  sá»‘ hiá»‡u cá»§a cÃ¡c tile trong tileSet
    public void loadMap(String s){
        try {
            //Doc File
            FileInputStream in = new FileInputStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            mapCol = Integer.parseInt(br.readLine());
            mapRow = Integer.parseInt(br.readLine());
            map = new int[mapRow][mapCol];

            width = mapCol* tileSize; //KÃ­ch cá»¡ map
            height = mapRow*tileSize;
            xmin = 0;
            xmax = width - Main.width;
            ymin = 0;
            ymax = height - Main.height;
            String del =",";
            for (int row =0;row <mapRow;row ++){
                String line = br.readLine();
                if (line == null) break;
                String[] tokens = line.split(del);
                for (int col = 0 ; col < mapCol;col ++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }

                }

            }

         catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Cáº¯t áº£nh, gá»‘c áº£nh trÃªn cÃ¹ng bÃªn trÃ¡i,
    //(x,y) Ä‘iá»ƒm báº¯t Ä‘áº§u cáº¯t
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

    public void OpenNextMap(int EnemyLength){     //If no Enemy open the gate to next map
        if (EnemyLength == 0) {
            for (int i = 19; i <= 21; i++) {
                tiles[0][i].setType(Tile.ALLOW);
            }
        }
    }
    public void setPos(double x, double y){
        this.x +=(x-this.x)*camSpeed;
        this.y +=(y-this.y)*camSpeed;
        //Ä�oáº¡n nÃ y Ä‘á»ƒ Ä‘áº£m báº£o chá»‰ váº½ nhá»¯ng thá»© cÃ³ trong map
        if (this.x<xmin) {
            this.x = xmin;
        }
        if (this.x>xmax) {
            this.x = xmax;
        }
        if (this.y<ymin) {
            this.y = ymin;
        }
        if (this.y>ymax) {
            this.y = ymax;
        }

        //cá»™t hÃ ng báº¯t Ä‘áº§u váº½
        colBeginDraw = (int)  this.x /tileSize;
        rowBeginDraw= (int)  this.y /tileSize;

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
                //Báº£n cháº¥t cá»§a 2 tham sá»‘ tá»�a Ä‘á»™ cá»§a hÃ m dÆ°á»›i Ä‘Ã¢y lÃ  phÃ©p dá»‹ch gá»‘c tá»�a Ä‘á»™!!
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
