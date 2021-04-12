package com.first.game.graphic;

//import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.first.game.util.Vector2f;



public class Font {
    
	private  BufferedImage FONTSHEET= null;
	private BufferedImage[][]  spriteArray;
	private final int TILE_SIZE = 20;
	public int w;
	public int h;
	private int wLetter;
	private int hLetter;
	
	public Font(String file) {
		w = TILE_SIZE;
		h = TILE_SIZE;
		
		System.out.println("Loading: "+ file + "...");
		FONTSHEET = loadFont(file);
	}
	
	public Font(String file, int w, int h) {
		this.w=w;
		this.h=h;
		
		System.out.println("Loading: "+ file+"...");
		FONTSHEET = loadFont(file);
		
		wLetter = FONTSHEET.getWidth()/w;
		hLetter = FONTSHEET.getHeight()/h;
		loadFontArray();
	}
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
		
	}
	public void setWidth(int i) {
		w = i;
		wLetter = FONTSHEET.getWidth()/w;
	}
	public void setHeight(int i) {
		h = i;
		wLetter = FONTSHEET.getHeight()/h;
	}
	public int getWidth() {return w;}
	public int getHeight() {return h;}
	private BufferedImage loadFont(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
			
		}catch (Exception e) {
			System.out.println("ERROR : could not load file "+ file);
		}
		return sprite;
		
	}
	
	
	public void loadFontArray() {
		spriteArray = new BufferedImage[wLetter][hLetter];
		for (int x = 0; x<wLetter; x++) {
			for (int y=0; y <hLetter; y++) {
				spriteArray[x][y]= getLetter(x, y);
				
			}
		}
	}

	public BufferedImage getFontSheet() {return FONTSHEET;}
	
	public BufferedImage getLetter(int x, int y) {
		return FONTSHEET.getSubimage(x*w, y*h, w, h);
	}
	public BufferedImage getFont(char letter) {
		int value = letter - 65 ; 
		int x = value % wLetter;
		int y = value / wLetter;
		return getLetter(x, y);
	}
	
	
	 
	
}

