package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected int HP;
    protected int MP;
    protected double posX = 0;
    protected double posY = 0;
    protected int dx;
    protected int dy;
    protected int deacc;


    public abstract void render(GraphicsContext graphicsContext);
    public abstract void tick();
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
