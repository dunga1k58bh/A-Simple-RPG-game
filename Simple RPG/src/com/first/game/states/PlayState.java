package com.first.game.states;

import com.first.game.graphic.Font;
import com.first.game.graphic.Sprite;
import com.first.game.util.KeyHandler;
import com.first.game.util.MouseHandler;
import com.first.game.util.Vector2f;
import com.first.game.entity.*;
import java.awt.Graphics2D;

public class PlayState extends GameState{
	  private int x=0;
	  private int y=0;
	  private Font font;
      private Player player;
      public PlayState(GameStateManager gsm) {
    	  super(gsm);
    	  player =new Player(new Sprite("entity/linkformatted.png"), new Vector2f(200,300), 75);
    	  font = new Font("font/ZeldaFont.png", 16, 16);
      }
      
      
      
      
      public void update() {
    	  player.update();
      }
      public void input(MouseHandler mouse, KeyHandler key) {
    	 player.input(mouse, key);
      }
          
      public void render(Graphics2D g) {
          Sprite.drawArray(g, font, "HELLO MOTHER FUCKER", new Vector2f(x,y), 32, 32, 25, 0);
          player.render(g);
      }
}