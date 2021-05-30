package entity.skills;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import tilemap.TileMap;

public class AttackBox extends Entity{
	private boolean remove;
	private final int damage = 10;
	
	public AttackBox(TileMap tm) {
		super(tm);
		width = 100;
		height = 60;
		setEntityBoxSize(100, 60);
	}
	
    public int getDamage() {
    	return damage;
    }
	
    public boolean shouldRemove() {
    	return remove;
    }
    
    public void setRemove() {
    	remove = true;
    }
    
    public void setPos(double x, double y){
            super.setPos(x + 50*facing, y);
    }
    
	@Override
	public void render(GraphicsContext graphicsContext) {
	}

	@Override
	public void tick() {
	}
}
