package utils.animation.attackAnimation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.animation.Animation;

public class meleeGreateSword extends Animation {
    private final Image blade0;
    private final Image blade1;
    private final Image blade2;
    private final Image blade3_0;
    private final Image blade3_1;
    private final Image blade3_2;
    private final Image blade4;
    private final Image blade5_0;
    private final Image blade5_1;
    private final Image blade5_2;
    private final Image blade6;
    private final Image blade7;
    private final Image blade8_0;
    private final Image blade8_1;
    private final Image blade9;

    public meleeGreateSword() {
        //eff1: start:287 350 w:73 h:37
        //eff2: 292 422 79 31
        setNumberOfStep(14);
        setSelfLock(false);
        setInterval(2);
        blade0 = new Image("Skill/skillEffect-GreatSword/Small254.png");
        blade1 = new Image("Skill/skillEffect-GreatSword/Small255.png");
        blade2 = new Image("Skill/skillEffect-GreatSword/Small256.png");
        blade3_0 = new Image("Skill/skillEffect-GreatSword/Small257.png");
        blade3_1 = new Image("Skill/skillEffect-GreatSword/eff1.png");
        blade3_2 = new Image("Skill/skillEffect-GreatSword/eff2.png");
        blade4 = new Image("Skill/skillEffect-GreatSword/Small257-1.png");
        blade5_0 = new Image("Skill/skillEffect-GreatSword/Small258.png");
        blade5_1 = new Image("Skill/skillEffect-GreatSword/darkeff1.png");
        blade5_2 = new Image("Skill/skillEffect-GreatSword/darkeff2.png");
        blade6 = new Image("Skill/skillEffect-GreatSword/Small296.png");
        blade7 = new Image("Skill/skillEffect-GreatSword/Small297.png");
        blade8_0 = new Image("Skill/skillEffect-GreatSword/Small298.png");
        blade8_1 = new Image("Skill/skillEffect-GreatSword/darkeff3.png");
        blade9 = new Image("Skill/skillEffect-GreatSword/eff3.png");
        upper.add(0, new Image("char/Small18-resources.assets-5458.png"));
        upper.add(1, upper.get(0));
        upper.add(2, new Image("char/Small19-resources.assets-3791.png"));
        upper.add(3, new Image("char/Small21-resources.assets-4561.png"));
        upper.add(4, upper.get(3));
        upper.add(5, new Image("char/Small16-resources.assets-4604.png"));
        upper.add(6, upper.get(3));
        upper.add(7, upper.get(3));
        upper.add(8, upper.get(0));
        upper.add(9, upper.get(5));
        upper.add(10, upper.get(3));
        upper.add(11, upper.get(3));
        upper.add(12, upper.get(0));
        upper.add(13, upper.get(4));
        for (int i = 0; i < getNumberOfStep();i++) {
            lower.add(i, new Image("char/Small31-resources.assets-1259.png"));
        }
        for (int i = 0; i < getNumberOfStep(); i++) {
            if (i<2) {
                head.add(i, new Image("char/Small32-resources.assets-4440.png"));
            }
            else {
                head.add(i, new Image("char/Small33-resources.assets-14326.png"));
            }
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext, double posX, double posY, double zzX, double zzY) {
        switch (animationStep) {
            case 0 -> {
                double headOffset = -13.5 - 6;
                double lowerOffset = - 5;
                double upperOffset = -23.5 - 4.0;
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -38.0  -2.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(blade0, 0 ,0, blade0.getWidth(), blade0.getHeight(),posX+-59.5*facing,posY+-46.0, blade0.getWidth()*facing, blade0.getHeight());
            }
            case 1 -> {
                double headOffset = -13.5 - 6;
                double lowerOffset = - 5;
                double upperOffset = -28.0;
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 + -6.0, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -65, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(blade1, 0 ,0, blade1.getWidth(), blade0.getHeight(),posX+-59.5*facing,posY+-46.0, blade1.getWidth()*facing, blade1.getHeight());
            }
            case 2 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = - 5;
                double upperOffset = -15.5 + -12.0;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY - 67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -32.5 + -8.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade2, 0 ,0, blade2.getWidth(), blade2.getHeight(),posX+-59.5*facing,posY+-42.0, blade2.getWidth()*facing, blade2.getHeight());
            }
            case 3 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 + -2, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade3_0, 0 ,0, blade3_0.getWidth(), blade3_0.getHeight(),posX+-31.0*facing,posY+-63.0, blade3_0.getWidth()*facing, blade3_0.getHeight());
                graphicsContext.drawImage(blade3_1, 0 ,0, blade3_1.getWidth(), blade3_1.getHeight(),posX+-5.0*facing,posY+-52.5, blade3_1.getWidth()*facing, blade3_1.getHeight());
                graphicsContext.drawImage(blade3_2, 0 ,0, blade3_2.getWidth(), blade3_2.getHeight(),posX+3.5*facing,posY+-19.5, blade3_2.getWidth()*facing, blade3_2.getHeight());
            }
            case 4 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 + -2, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade4, 0 ,0, blade4.getWidth(), blade4.getHeight(),posX+-31.0*facing,posY+-63.0, blade4.getWidth()*facing, blade4.getHeight());
                graphicsContext.drawImage(blade3_1, 0 ,0, blade3_1.getWidth(), blade3_1.getHeight(),posX+-5.0*facing,posY+-52.5, blade3_1.getWidth()*facing, blade3_1.getHeight());
                graphicsContext.drawImage(blade3_2, 0 ,0, blade3_2.getWidth(), blade3_2.getHeight(),posX+3.5*facing,posY+-19.5, blade3_2.getWidth()*facing, blade3_2.getHeight());
            }
            case 5 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3 + 3.5;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 -2 + 2.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade5_0, 0 ,0, blade5_0.getWidth(), blade5_0.getHeight(),posX+-47.0*facing,posY+-45.0, blade5_0.getWidth()*facing, blade5_0.getHeight());
                graphicsContext.drawImage(blade5_1, 0 ,0, blade5_1.getWidth(), blade5_1.getHeight(),posX+1.0*facing,posY+-61.5, blade5_1.getWidth()*facing, blade5_1.getHeight());
                graphicsContext.drawImage(blade5_2, 0 ,0, blade5_2.getWidth(), blade5_2.getHeight(),posX+-1.5*facing,posY+-18.5, blade5_2.getWidth()*facing, blade5_2.getHeight());
            }
            case 6 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(blade6, 0 ,0, blade6.getWidth(), blade6.getHeight(),posX+-46.5*facing,posY+-11.0, blade6.getWidth()*facing, blade6.getHeight());
                graphicsContext.drawImage(blade3_1, 0 ,0, blade3_1.getWidth(), blade3_1.getHeight(),posX+-5.0*facing,posY+-52.5, blade3_1.getWidth()*facing, blade3_1.getHeight());
            }
            case 7 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(blade5_1, 0 ,0, blade5_1.getWidth(), blade5_1.getHeight(),posX+1.0*facing,posY+-61.5, blade5_1.getWidth()*facing, blade5_1.getHeight());
                graphicsContext.drawImage(blade7, 0 ,0, blade7.getWidth(), blade7.getHeight(),posX+-45.5*facing,posY+-50.5, blade7.getWidth()*facing, blade7.getHeight());
            }
            case 8 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = - 5;
                double upperOffset = -23.5 - 4.0;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -38.0  -2.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade8_0, 0 ,0, blade8_0.getWidth(), blade8_0.getHeight(),posX+-53.5*facing,posY+-53.5, blade8_0.getWidth()*facing, blade8_0.getHeight());
                graphicsContext.drawImage(blade8_1, 0 ,0, blade8_1.getWidth(), blade8_1.getHeight(),posX+-11.0*facing,posY+-48.0, blade8_1.getWidth()*facing, blade8_1.getHeight());
            }
            case 9 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 -2 + 2.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade5_0, 0 ,0, blade5_0.getWidth(), blade5_0.getHeight(),posX+-47.0*facing,posY+-45.0, blade5_0.getWidth()*facing, blade5_0.getHeight());
                graphicsContext.drawImage(blade9, 0 ,0, blade9.getWidth(), blade9.getHeight(),posX+-34.5*facing,posY+-40.0, blade9.getWidth()*facing, blade9.getHeight());
            }
            case 10 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 + -2, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade3_2, 0 ,0, blade3_2.getWidth(), blade3_2.getHeight(),posX+9.5*facing,posY+-11.0, blade3_2.getWidth()*facing, blade3_2.getHeight());
            }
            case 11 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing +lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5 + -2, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade7, 0 ,0, blade7.getWidth(), blade7.getHeight(),posX+-45.5*facing,posY+-50.5, blade7.getWidth()*facing, blade7.getHeight());
            }
            case 12 -> {
                double headOffset = -13.5 - 6;
                double lowerOffset = - 5;
                double upperOffset = -23.5 - 4.0;
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -38.0  -2.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(blade8_0, 0 ,0, blade8_0.getWidth(), blade8_0.getHeight(),posX+-53.5*facing,posY+-53.5, blade8_0.getWidth()*facing, blade8_0.getHeight());
            }
            case 13 -> {
                double headOffset = -13.5 - 6 - 2;
                double lowerOffset = -5;
                double upperOffset = -26.0 + 3;
                graphicsContext.drawImage(getImageThisFrame("lower"), 0, 0, getImageThisFrame("lower").getWidth(), getImageThisFrame("lower").getHeight(), posX - getImageThisFrame("lower").getWidth() / 2 * facing + lowerOffset*facing, posY - getImageThisFrame("lower").getHeight(), getImageThisFrame("lower").getWidth() * facing, getImageThisFrame("lower").getHeight());
                graphicsContext.drawImage(getImageThisFrame("upper"), 0, 0, getImageThisFrame("upper").getWidth(), getImageThisFrame("upper").getHeight(), posX + upperOffset*facing, posY + -34.5, getImageThisFrame("upper").getWidth() * facing, getImageThisFrame("upper").getHeight());
                graphicsContext.drawImage(getImageThisFrame("head"), 0, 0, getImageThisFrame("head").getWidth(), getImageThisFrame("head").getHeight(), posX + headOffset*facing, posY + -67, getImageThisFrame("head").getWidth() * facing, getImageThisFrame("head").getHeight());
                graphicsContext.drawImage(blade9, 0 ,0, blade9.getWidth(), blade9.getHeight(),posX+-15.5*facing,posY+-27.0, blade9.getWidth()*facing, blade9.getHeight());
            }
        }
    }
}
