package entity;

import javafx.scene.image.Image;

public class Animation {
	
	private Image[] frames;
	private int curFrame;
	private long startTime;
	private long delay;
	private boolean playedOnce;
	
	public Animation() {
		playedOnce = false;
	}
	
	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { curFrame = i; }
	public int getFrame() { return curFrame; }
	public Image getImage() { return frames[curFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }
	
	public void setFrames(Image[] frames) {
		this.frames = frames;
		curFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}

	
	public void update() {
		
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			curFrame++;
			startTime = System.nanoTime();
		}
		if(curFrame == frames.length) {
			curFrame = 0;
			playedOnce = true;
		}
	}
}