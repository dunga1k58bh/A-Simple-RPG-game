package entity;

import application.Main;
import entity.enemies.Enemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import tilemap.Tile;
import tilemap.TileMap;

public abstract class Entity {
    protected int HP;
    protected int MP;
    protected boolean dead;


    protected double posX = 0;
    protected double posY = 0;
    protected double xmin;
    protected double xmax;
    protected double ymax;
    protected double ymin;
    protected double dx;
    protected double dy;
    protected  boolean falling;

    //Time entity
    private long timeLoad;
    
    //flag
    public boolean onGround;

    //TileMap
    protected TileMap tileMap;
    protected int tileSize;
    protected  double xmap;
    protected  double ymap;
    protected  int currRow;
    protected  int currCol;

    //Left or right ??
    protected boolean facingRight;

    //if entity being attack it cant be attack for a while
    protected boolean flinching;
    protected long flinchTimer;
    protected boolean beingHit;


    //Entity box size
    protected int cheight;
    protected int cwidth;
    // dimensions
    protected int width;
    protected int height;


    //Check TileMap Collision (Loại Tile )4 góc Hcn
    protected  int BottomLeft1;  //present status
    protected int BottomRight1;

    protected int TopLeft;    //nexts
    protected int TopRight;
    protected int BottomLeft;
    protected int BottomRight;


    public Entity(TileMap tileMap){
        this.tileMap = tileMap;
        this.tileSize = tileMap.getTileSize();
    }
    public Entity(){

    }


    public abstract void render(GraphicsContext graphicsContext);
    public abstract void tick();


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public void changeHP(int c) {
    	this.HP += c;
    	if (HP <= 0)	HP = 0;
    }
    public void getHit(int damage) {
        if(dead || flinching) return;
        HP -= damage;
        if(HP < 0) HP = 0;
        if(HP == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();
        beingHit = true;
    }
    public boolean isDead(){
        return dead;
    }
    public void setDead(boolean b){
         dead = b;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }
    public void changeMP(int c) {
    	this.MP += c;
    	if (MP <= 0) MP = 0;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public  void setPos(double x, double y){
        this.posX = x;
        this.posY = y;
    }

    public void setTimeLoad(long timeLoad){
        this.timeLoad = timeLoad;
    }

    public long getTimeLoad(){
        return timeLoad;
    }


    public void setTileMap(TileMap tileMap){
        this.tileMap = tileMap;
        this.tileSize = tileMap.getTileSize();
        this.xmin = cwidth/2+1;
        this.xmax = tileMap.getWidth()-cwidth/2-1;
        this.ymin = cheight+1;
        this.ymax = tileMap.getHeight() -1;

    }
    public void setEntityBoxSize(int cwidth, int cheight){
        this.cwidth = cwidth;
        this.cheight = cheight;
    }
    //Hàm tính loại Tile mà 4 góc HCN của Entity đang ở khi Entity ở tọa độ x,y
    public void CaculateCorrners(double x, double y){
        int LeftCol = (int)(x-cwidth/2)/tileSize;
        int RightCol = (int) (x+cwidth/2)/tileSize;
        int TopRow = (int) (y-cheight)/tileSize;
        int BottomRow = (int)(y/tileSize);
        int BottomRow1 = (int) ((y-3*dy)/tileSize);

        TopLeft = tileMap.getType(TopRow,LeftCol); //Hàm này trả về loại Tile
        TopRight = tileMap.getType(TopRow,RightCol);
        BottomLeft = tileMap.getType(BottomRow,LeftCol);
        BottomRight = tileMap.getType(BottomRow,RightCol);
        BottomLeft1 = tileMap.getType(BottomRow1,LeftCol);
        BottomRight1 = tileMap.getType(BottomRow1,RightCol);
    }
    //Ý tưởng thì khá đơn giản : Tính 4 góc của hcn bao quanh nhân vật, xem 4 góc đó đang nằm ở Tile loại nào?
    //(trạng thái (posX+dx,posY+dy) tức là trạng thái sau (xem có cho phép không)) và tìm cách xử lý phù hợp
    public void CheckTileMapCollision(){
        currCol = (int)posX/tileSize;
        currRow = (int)posY/tileSize;

        if(posX+dx>xmax||posX+dx<xmin) dx = 0;
        if(posY+3*dy>ymax||posY+3*dy<ymin) {
            if(dy>0) onGround = true;
//            if(dy<0) onRoof = true;
            dy = 0;
        }
        CaculateCorrners(posX,posY+ 3*dy); //LMAO IELTS 10.0
        //Sau đây là 4 trường hợp chính
        if(dy!=0) {
            if ((BottomRight == Tile.BLOCK || BottomLeft == Tile.BLOCK)
                    && (BottomLeft1 == Tile.ALLOW || BottomRight == Tile.ALLOW)) {
                onGround = true;
            } else {
                onGround = false;
            }
        }
//        System.out.println(BottomLeft1+" "+BottomRight1+" "+BottomLeft+" "+BottomRight);


        if (dy>0){//falling
            if ((BottomRight == Tile.BLOCK||BottomLeft == Tile.BLOCK)
                    &&(BottomLeft1==Tile.ALLOW||BottomRight==Tile.ALLOW||BottomRight1==Tile.DEAD||BottomLeft1==Tile.DEAD)){
                dy = 0;
                posY = (currRow+1) * tileSize-1;
                falling = false;
                onGround = true;
//                System.out.println("Onground");
            }
        }
        if(dy<0){
            onGround = false;
        }
        CaculateCorrners(posX+dx,posY);
        if (dx>0){ //Sang trái
            if (TopRight == Tile.BLOCK && BottomRight == Tile.BLOCK){
                dx = 0;
                posX = (currCol+1)*tileSize - cwidth/2-1;
            }
        }
        if (dx<0){//Sang Phải
            if (TopLeft == Tile.BLOCK && BottomLeft == Tile.BLOCK){
                dx=0;
                posX = currCol*tileSize +cwidth/2+1;
            }
        }
        if (BottomLeft == Tile.DEAD || BottomRight == Tile.DEAD){
            getHit(200);
        }
    }
    public void setMapPosittion(){
        xmap = tileMap.getCameraPosX();
        ymap = tileMap.getCameraPosY();
    }
    public boolean notOnScreen() {
        return posX - xmap- width > Main.width ||
                posX - xmap + width < 0 ||
                posY - ymap + height < 0 ||
                posY - ymap - height > Main.height;
    }

    //intersects beetween enemy and the entity object. If intersected return true;
    public boolean intersects(Entity e) {
        Rectangle2D r1 = getRectangle();
        Rectangle2D r2 = e.getRectangle();
        return r1.intersects(r2);
    }
    //Return a rectangle surround the Entity for check intersects
    public Rectangle2D getRectangle() {
        return new Rectangle2D((int)posX - cwidth/2, (int)posY - cheight, cwidth, cheight);
    }

}
