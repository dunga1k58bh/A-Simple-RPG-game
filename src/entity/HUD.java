package entity;

import java.io.FileInputStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class HUD {
	private Player player;
	private Font font;
	private Image image;
	
	public HUD(Player p) {
		player = p;
		try {
			image = new Image(new FileInputStream("res/hud/topbar.png"));
			font = new Font("Arial", 20);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(image, 0, 0);
		g.setFont(font);
		//g.fillText(player, 0, 0);
	}
}
