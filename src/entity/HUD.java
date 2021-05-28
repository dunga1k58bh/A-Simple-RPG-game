package entity;

import java.io.FileInputStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUD {
	private Player player;
	private Font fontHP, fontMP;
	private Image image;
	
	public HUD(Player p) {
		player = p;
		try {
			image = new Image(new FileInputStream("res/hud/topbar.png"));
			fontHP = new Font("Arial", 20);
			fontMP = new Font("Arial", 15);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(155, 20, 250, 30);
		g.setFill(Color.RED);
		g.fillRect(155, 20, 250 * (double)player.HP / player.maxHP, 30);
		g.setFill(Color.BLACK);
		g.fillRect(155, 70, 175, 20);
		g.setFill(Color.BLUE);
		g.fillRect(155, 70, 175 * (double)player.MP / player.maxMP, 20);
		g.drawImage(image, 0, 0);
		g.setFill(Color.WHITE);
		g.setFont(fontMP);
		g.fillText(String.valueOf(player.MP) + " / " + String.valueOf(player.maxHP), 180, 85);
		g.setFont(fontHP);
		g.fillText(String.valueOf(player.HP) + " / " + String.valueOf(player.maxMP), 180, 40);
		g.fillText(String.format("%4.2f", (double)player.curEXP / player.levelEXP) +"%", 42, 95);
		g.setFill(Color.YELLOW);
		g.fillText("Level " + player.level, 42, 65);
	}
}
