package com.first.game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.first.game.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener{
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public MouseHandler(GamePanel game) {
		game.addMouseListener(this);
		
	}
	public int getX() {
		return mouseX;
	}
	public int getY() {
		return mouseY;
	}
	public int getButton() {
		return mouseB;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseB= e.getButton();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseB= -1;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	



}
