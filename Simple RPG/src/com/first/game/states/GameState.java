package com.first.game.states;

import java.awt.Graphics2D;

import com.first.game.util.KeyHandler;
import com.first.game.util.MouseHandler;

public abstract class GameState {
	
	private GameStateManager gsm;
    public GameState(GameStateManager gsm) {
    	this.gsm= gsm;
}  
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}