package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

/**
 * This class is used to create user played tank tanks
 *
 * @author MohamadCM
 */
public class UserTank {
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


    public UserTank() throws IOException {
        //paintTank();

        mainGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun01.png")) , ImageIO.read(new File("Resources/Images/tankGun1.png")) , ImageIO.read(new File("Resources/Images/HeavyBullet.png")));

        secondGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun02.png")) , ImageIO.read(new File("Resources/Images/tankGun2.png")) , ImageIO.read(new File("Resources/Images/LightBullet.png")));

        currentGun = mainGun;

        isMainGun = true;

        //paintCurrentGun();

        tankMouseHandler = new MouseHandler();

        ownTank = ImageIO.read(new File("Resources/Images/tank.png"));

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
        public void mousePressed(MouseEvent mouseEvent){
            if(mouseEvent.getButton() == MouseEvent.BUTTON3)
                changeGun();
            if(mouseEvent.getButton() == MouseEvent.BUTTON1 && isMainGun) {
                if(System.currentTimeMillis() - lastShootTime > 500) {
                    currentGun.addBullets(state);
                    lastShootTime = System.currentTimeMillis();
                }
            }
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
            if(mousePressed && getCurrentGun() == getSecondGun()) {
                getSecondGun().addBullets(state);
                lastShootTime = System.currentTimeMillis();
            }
    }
}
