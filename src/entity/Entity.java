package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected int HP;
    protected int MP;
    protected int posX = 0;
    protected int posY = 0;
    protected int dx;
    protected int dy;
    protected int deacc;


    public abstract void render(GraphicsContext graphicsContext);

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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
