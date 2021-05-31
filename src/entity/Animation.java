package entity;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Animation {
	
	private Image[] frames;
	private int length;
	private int curFrame;
	private long startTime;
	private long delay;
	private boolean playedOnce;
    private int maxConsercutiveAnimation;        // max number of consercutive runs of animation

	private int width;
	private int height;

	public Animation() {
		playedOnce = false;
	}

	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { curFrame = i; }
	public int getFrame() { return curFrame; }
	public Image getImage() { return frames[curFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }
	public void setPlayedOnce(boolean b){this.playedOnce=b;}
    public void setWidthHeight(int width, int height){
		this.width = width;
		this.height = height;
	}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public void setMaxConsercutiveAnimation(int i){
		maxConsercutiveAnimation =i;
	}
	public int getMaxConsercutiveAnimation(){
		return maxConsercutiveAnimation;
	}
	public int getLength(){
		return frames.length;
	}

	public void setFrames(String s,int length){
		this.length = length;
		try {
			BufferedImage spritesheet = ImageIO.read(
					new FileInputStream(s)
			);

			frames = new Image[length];
			for(int i = 0; i < frames.length; i++) {
				frames[i] = SwingFXUtils.toFXImage(spritesheet.getSubimage(
						i * width,
						0,
						width,
						height),
						null
				);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		curFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;

	}
	public void setFrames(String s,int length,int row,int col){
		try {
			BufferedImage spritesheet = ImageIO.read(
					new FileInputStream(s)
			);

			frames = new Image[length];
			for(int i = 0 ;i< row;i++) {
				for (int j = 0;j< col;j++) {
					//System.out.println("1");
					if (i* col+j<length) {
						frames[i * col +j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height),
								null
						);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		curFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;

	}
	
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