package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import utils.Key;

import java.util.ArrayList;

public class Player extends Entity{
    private Image standLower = new Image("char/Small23-resources.assets-11235.png");
    private Image standUpper = new Image("char/Small5-resources.assets-1687.png");
    private ArrayList<Image> head = new ArrayList<Image>();
    private ArrayList<Image> lowerBody = new ArrayList<Image>();
    private ArrayList<Image> upperBody = new ArrayList<Image>();
    private int animationStep = 0;
    private int animationStep2 = 0;
    private int count1 = 0;
    private int count2 = 0;
    private int offset = 0;
    //private final Image head = new Image("char/Small33-resources.assets-14326.png"); //default head
    //private final Image lowerBody = new Image("char/Small24-resources.assets-12840.png"); //down
    //private final Image upperBody = new Image("char/Small6-resources.assets-12601.png"); //up
    private int runningDirection = 0; //0 = not running, 1 = right, -1 = left
    private Key key= new Key();
    public Player() {
        setPosX(300);
        setPosY(300);

        //add default texture
        head.add(0,new Image("char/Small32-resources.assets-4440.png"));
        head.add(1,new Image("char/Small33-resources.assets-14326.png"));
        upperBody.add (0,new Image("char/Small6-resources.assets-12601.png"));
        upperBody.add (1,new Image("char/Small7-resources.assets-12452.png"));
        upperBody.add (2,new Image("char/Small8-resources.assets-1354.png"));
        upperBody.add (3,new Image("char/Small9-resources.assets-7446.png"));
        upperBody.add (4,new Image("char/Small10-resources.assets-8065.png"));
        lowerBody.add(0,new Image("char/Small24-resources.assets-12840.png"));
        lowerBody.add(1,new Image("char/Small25-resources.assets-6123.png"));
        lowerBody.add(2,new Image("char/Small26-resources.assets-6747.png"));
        lowerBody.add(3,new Image("char/Small27-resources.assets-6734.png"));
        lowerBody.add(4,new Image("char/Small28-resources.assets-5528.png"));
    }

    public Player(int x, int y) {
        this();
        setPosX(x);
        setPosY(y);
    }

    //private "default skin"
    @Override
    public void render(GraphicsContext graphicsContext) {
        //tick
        if (key.up == 0) {
            runningDirection = key.right - key.left;
        }
        if (runningDirection == 1) {
            posX += 5;
            count1++;
            count2++;
        } else if (runningDirection == -1) {
            posX -= 5;
            count1++;
            count2++;
        }
        else if (runningDirection == 0) {
            animationStep = -1;
            count2++;
        }

        if (count1 % 5 == 0 && count1!=0) {
            count1 = 0;
            animationStep ++;
        }
        if (count2 % 13 == 0 && count2!=0){
            count2 = 0;
            animationStep2 ++;
            offset = animationStep2%2*3;
        }
        //offset = 0;
        //animationStep ++;
        //render
        //Image image1 = new Image("char/Small33-resources.assets-14326.png"); //head
        switch (animationStep%5) {
            case -1: {

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //down
                graphicsContext.drawImage(standLower, posX - 7 , posY - 17);

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //up
                graphicsContext.drawImage(standUpper, posX - 14, posY - 35 + offset);

                graphicsContext.drawImage(head.get(0), posX - 15, posY - 68 + offset); //head
                break;
            }
            case 0: {
                graphicsContext.drawImage(head.get(1), posX - 15, posY - 68 + offset);

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //down
                graphicsContext.drawImage(lowerBody.get(animationStep % lowerBody.size()), posX - lowerBody.get(animationStep % upperBody.size()).getWidth() / 2, posY - lowerBody.get(animationStep % lowerBody.size()).getHeight());

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //up
                graphicsContext.drawImage(upperBody.get(animationStep % upperBody.size()), posX - 14, posY - 35 + offset);
                break;
            }
            case 1: {
                graphicsContext.drawImage(head.get(1), posX - 15, posY - 68 + offset);

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //lower
                graphicsContext.drawImage(lowerBody.get(animationStep % lowerBody.size()), posX - lowerBody.get(animationStep % upperBody.size()).getWidth() / 2 + 3, posY - lowerBody.get(animationStep % lowerBody.size()).getHeight());

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //upper
                graphicsContext.drawImage(upperBody.get(animationStep % upperBody.size()), posX - 19, posY - 35 + offset);
                break;
            }
            case 2: {
                graphicsContext.drawImage(head.get(1), posX - 15, posY - 68 + offset);

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //lower
                graphicsContext.drawImage(lowerBody.get(animationStep % lowerBody.size()), posX - lowerBody.get(animationStep % upperBody.size()).getWidth() / 2 + 2, posY - lowerBody.get(animationStep % lowerBody.size()).getHeight());

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //upper
                graphicsContext.drawImage(upperBody.get(animationStep % upperBody.size()), posX - 10, posY - 33 + offset);
                break;
            }
            case 3: {
                graphicsContext.drawImage(head.get(1), posX - 15, posY - 68 + offset);

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //lower
                graphicsContext.drawImage(lowerBody.get(animationStep % lowerBody.size()), posX - lowerBody.get(animationStep % upperBody.size()).getWidth() / 2 + 4, posY - lowerBody.get(animationStep % lowerBody.size()).getHeight());

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //upper
                graphicsContext.drawImage(upperBody.get(animationStep % upperBody.size()), posX - 13, posY - 33 + offset);
                break;
            }
            case 4: {
                graphicsContext.drawImage(head.get(1), posX - 15, posY - 68 + offset);

                //Image image2 = new Image("char/Small24-resources.assets-12840.png"); //lower
                graphicsContext.drawImage(lowerBody.get(animationStep % lowerBody.size()), posX - lowerBody.get(animationStep % upperBody.size()).getWidth() / 2 + 4, posY - lowerBody.get(animationStep % lowerBody.size()).getHeight());

                //Image image3 = new Image("char/Small6-resources.assets-12601.png"); //upper
                graphicsContext.drawImage(upperBody.get(animationStep % upperBody.size()), posX - 9, posY - 33 + offset);
                break;
            }
        }
    }

    private void drawWalkAnimation(int s) {

    }

    public void keyIn(KeyEvent keyEvent) {
        //System.out.println("Left");
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            System.out.println("Key pressed");
            switch (keyEvent.getCode()) {
                case UP -> {
                    key.up = 1;
                    break;
                }
                case DOWN -> {
                    key.down = 1;
                    break;
                }
                case LEFT -> {
                    key.left = 1;
                    break;
                }
                case RIGHT -> {
                    key.right = 1;
                    break;
                }
            }
        }
        else {
            System.out.println("Not key pressed");
            switch (keyEvent.getCode()) {
                case UP -> {
                    key.up = 0;
                    break;
                }
                case DOWN -> {
                    key.down = 0;
                    break;
                }
                case LEFT -> {
                    key.left = 0;
                    break;
                }
                case RIGHT -> {
                    key.right = 0;
                    break;
                }
            }
        }
    }

    //Method does not control animation
    private void jump() {

    }
    //Method does not control animation
    private void left() { //Method does not control animation

    }
    //Method does not control animation
    private void right(){

    }
}
