package battleObject;

import bufferstrategy.GameState;
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to create user played tank tanks
 *
 * @author MohamadCM
 */
public class UserTank implements Drawable {
    private GameState state;
    private UserTankGun mainGun;
    private UserTankGun secondGun;
    private UserTankGun currentGun;
    private boolean isMainGun;
    private Graphics2D g2d;
    private MouseHandler tankMouseHandler;
    private AffineTransform gunAT;
    private long lastShootTime;
    public boolean mousePressed;
    private BufferedImage ownTank;
    private int numberOfHeavyBullet = 50;
    private int numberOfLightBullet = 200;
    private BufferedImage numberOfHeavyBulletImage;
    private BufferedImage numberOfLightBulletImage;



    public UserTank() throws IOException {
        //paintTank();

        mainGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun01.png")) , ImageIO.read(new File("Resources/Images/tankGun1.png")) , ImageIO.read(new File("Resources/Images/HeavyBullet.png")));

        secondGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun02.png")) , ImageIO.read(new File("Resources/Images/tankGun2.png")) , ImageIO.read(new File("Resources/Images/LightBullet.png")));

        currentGun = mainGun;

        isMainGun = true;

        //paintCurrentGun();

        tankMouseHandler = new MouseHandler();

        ownTank = ImageIO.read(new File("Resources/Images/tank.png"));

        numberOfHeavyBulletImage = ImageIO.read(new File("Resources/Images/NumberOfHeavyBullet.png"));

        numberOfLightBulletImage = ImageIO.read(new File("Resources/Images/NumberOfLightBullet.png"));


    }

    /**
     * Paint Thank body every moment
     * @throws IOException
     */
    public void paintTank() throws IOException {
        AffineTransform tankAt = new AffineTransform();
        tankAt.setToTranslation(state.locX + 50, state.locY + 50);
        tankAt.rotate(state.tankAngle);
        tankAt.translate(-50, -50);
        //paint the tank
        g2d.drawImage(ownTank,tankAt,null);
    }

    /**
     * Paint current gun
     */
    public void paintCurrentGun() {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(state.locX + 50, state.locY + 50);
        gunAT.rotate(state.angle);
        gunAT.translate(-30, -30);
        g2d.drawImage(currentGun.currentModImage, gunAT,null);

    }

    /*
     * Changes the current gun
     * and paint the new gun
     */
    private void changeGun(){
        if(isMainGun) {
            currentGun = secondGun;
            currentGun.currentModImage = secondGun.currentModImage;
            isMainGun = false;
        }
        else {
            currentGun = mainGun;
            isMainGun = true;
        }
    }
    /**
     * The mouse handler for tank actions
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON3)
                changeGun();
            if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isMainGun && numberOfHeavyBullet > 0) {
                if (System.currentTimeMillis() - lastShootTime > 500) {
                    currentGun.addBullets(state.mouseX, state.mouseY, state.locX, state.locY);
                    numberOfHeavyBullet -= 1;
                    lastShootTime = System.currentTimeMillis();
                    new SoundPlayer("Resources/Sounds/heavygun.wav").run();
                    }
                }else if(numberOfHeavyBullet <= 0)
                new SoundPlayer("emptyGun.wav").run();
                if(mouseEvent.getButton() == MouseEvent.BUTTON1)
                    mousePressed = true;
            }
        @Override
        public void mouseReleased(MouseEvent mouseEvent){
            mousePressed = false;
        }
    }

    /**
     * @return tank mouse handler
     */
    public MouseHandler getTankMouseHandler() {
        return tankMouseHandler;
    }

    public void setState(GameState state){
        this.state = state;
    }

    public void setG2d(Graphics2D g2d){
        this.g2d = g2d;
    }

    public UserTankGun getCurrentGun() {
        return currentGun;
    }

    public UserTankGun getMainGun() {
        return mainGun;
    }

    public UserTankGun getSecondGun() {
        return secondGun;
    }

    public void fireSecondGun(){
        if(System.currentTimeMillis() - lastShootTime > 200)
                if(mousePressed && getCurrentGun() == getSecondGun() && numberOfLightBullet > 0) {
                    getSecondGun().addBullets(state.mouseX,state.mouseY,state.locX,state.locY);
                    numberOfLightBullet -= 1;
                    lastShootTime = System.currentTimeMillis();

                    new SoundPlayer("Resources/Sounds/mashingun.wav").run();
                } else if(numberOfLightBullet <= 0)
                    new SoundPlayer("emptyGun.wav").run();
            }




    public void render() throws IOException {

        g2d.setColor(Color.ORANGE);

        g2d.setFont(g2d.getFont().deriveFont(20.0f));

        g2d.drawImage(numberOfHeavyBulletImage,null,50,50);

        g2d.drawString("" + numberOfHeavyBullet ,120,90);

        g2d.drawImage(numberOfLightBulletImage, null, 55, 110);

        g2d.drawString("" + numberOfLightBullet, 125, 145);



        paintTank();

        paintCurrentGun();

    }
}
