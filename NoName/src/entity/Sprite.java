package entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private Image image;
    private double posX;// Vitri toa do 2d
    private double posY;    
    private double vX;  //Van toc 2 chieu
    private double vY;
    private double width;
    private double height;

    public Sprite()
    {
        posX = 0;
        posY = 0;    
        vX = 0;
        vY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        posX = x;
        posY = y;
    }

    public void setVelocity(double x, double y)
    {
        vX = x;
        vY = y;
    }

    public void addVelocity(double x, double y)
    {
        vX += x;
        vY += y;
    }

    public void update(double time)
    {
        posX += vX * time;
        posY += vY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, posX, posY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(posX,posY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + posX + "," + posY + "]" 
        + " Velocity: [" + vX + "," + vY + "]";
    }
}
