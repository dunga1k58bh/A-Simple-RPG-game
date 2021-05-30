package utils.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Animation {
    protected int facing;
    protected int numberOfStep;
    protected int interval;
    protected ArrayList<Image> upper = new ArrayList<Image>();
    protected ArrayList<Image> lower = new ArrayList<Image>();
    protected ArrayList<Image> head = new ArrayList<Image>();
    protected int count;
    protected int animationStep;

    public abstract void render(GraphicsContext g, double x, double y, double zzX, double zzY);

    public void tick() {
        if (interval != 1 && interval != 0 && count % interval == 0 && count != 0){
            animationStep++;
        }
        else if (interval == 1) {
            animationStep++;
        }
        count ++;
    }

    public Image getImageThisFrame(String s) {
        switch (s) {
            case "upper" -> {
                return upper.get(animationStep%numberOfStep);
            }
            case "lower" -> {
                return lower.get(animationStep%numberOfStep);
            }
            case "head" -> {
                return head.get(animationStep%numberOfStep);
            }
            default -> {
                return null;
            }
        }
    }

    public void refresh() {
        animationStep = 0;
        count = 0;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getFacing() {
        return facing;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public ArrayList<Image> getUpper() {
        return upper;
    }

    public void setUpper(ArrayList<Image> upper) {
        this.upper = upper;
    }

    public ArrayList<Image> getLower() {
        return lower;
    }

    public void setLower(ArrayList<Image> lower) {
        this.lower = lower;
    }

    public ArrayList<Image> getHead() {
        return head;
    }

    public void setHead(ArrayList<Image> head) {
        this.head = head;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAnimationStep() {
        return animationStep;
    }

    public void setAnimationStep(int animationStep) {
        this.animationStep = animationStep;
    }
}
