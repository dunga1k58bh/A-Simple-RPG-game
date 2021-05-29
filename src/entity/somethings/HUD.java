package entity.somethings;

import java.io.FileInputStream;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUD {
	private Player player;
	private Font fontHP, fontMP, potFont;
	private Image image, HPpot, MPpot, button;
	
	public HUD(Player p) {
		player = p;
		try {
			image = new Image(new FileInputStream("res/hud/topbar.png"));
			fontHP = Font.loadFont("file:res/Font/GameFont.otf", 20);
			fontMP =  Font.loadFont("file:res/Font/GameFont.otf", 15);
			HPpot = new Image(new FileInputStream("res/hud/hp.png"));
			MPpot = new Image(new FileInputStream("res/hud/mp.png"));
			button = new Image(new FileInputStream("res/hud/button_hpmp.png"));
			potFont = Font.loadFont("file:res/Font/GameFont.otf", 15);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(155, 20, 250, 30);
		g.setFill(Color.RED);
		g.fillRect(155, 20, 250 * (double)player.getHP() / player.maxHP, 30);
		g.setFill(Color.BLACK);
		g.fillRect(155, 70, 175, 20);
		g.setFill(Color.BLUE);
		g.fillRect(155, 70, 175 * (double)player.getMP() / player.maxMP, 20);
		g.drawImage(image, 0, 0);
		g.setFill(Color.WHITE);
		g.setFont(fontMP);
		g.fillText(String.valueOf(player.getMP()) + " / " + String.valueOf(player.maxHP), 180, 85);
		g.setFont(fontHP);
		g.fillText(String.valueOf(player.getHP()) + " / " + String.valueOf(player.maxMP), 180, 40);
		g.fillText(String.format("%4.2f", (double)player.curEXP / player.levelEXP) +"%", 42, 95);
		g.setFill(Color.YELLOW);
		g.fillText("Level " + player.level, 42, 65);
		g.drawImage(button, 160, 100);
		g.drawImage(button, 220, 100);
		g.drawImage(HPpot, 165, 102);
		g.drawImage(MPpot, 225, 102);
		g.setFill(Color.WHITE);
		g.setFont(potFont);
		g.fillText(String.valueOf(player.HPpotNum), 190, 135);
		g.fillText(String.valueOf(player.MPpotNum), 250, 135);
	}
}
