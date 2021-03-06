package battleObject;

import bufferstrategy.GameState;
import utility.Images;
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to create user played tank tanks
 * Each tank needs a starting health
 *
 * @author MohamadCM
 */
public class UserTank implements Drawable {
    private GameState state;
    private UserTankGun mainGun;
    private UserTankGun secondGun;
    private static UserTankGun currentGun;
    private boolean isMainGun;
    private MouseHandler tankMouseHandler;
    private AffineTransform gunAT;
    private long lastShootTime;
    public boolean mousePressed;
    public static int numberOfHeavyBullet = 20;
    public static int numberOfLightBullet = 50;
    private BufferedImage numberOfHeavyBulletImage;
    private BufferedImage numberOfLightBulletImage;
    public static int health;


    public UserTank(int health) throws IOException {
        this.health = health;
        //paintTank();

        mainGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun01.png")) , ImageIO.read(new File("Resources/Images/tankGun1.png")) , ImageIO.read(new File("Resources/Images/HeavyBullet.png")),50);

        secondGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun02.png")) , ImageIO.read(new File("Resources/Images/tankGun2.png")) , ImageIO.read(new File("Resources/Images/LightBullet.png")), 20);

        currentGun = mainGun;

        isMainGun = true;

        //paintCurrentGun();

        tankMouseHandler = new MouseHandler();


        numberOfHeavyBulletImage = ImageIO.read(new File("Resources/Images/NumberOfHeavyBullet.png"));

        numberOfLightBulletImage = ImageIO.read(new File("Resources/Images/NumberOfLightBullet.png"));



    }

    /**
     * Paint Thank body every moment
     * @throws IOException
     */
    public void paintTank(Graphics2D g2d) throws IOException {
        AffineTransform tankAt = new AffineTransform();
        tankAt.setToTranslation(state.locX + 50, state.locY + 50);
        tankAt.rotate(state.tankAngle);
        tankAt.translate(-50, -50);
        //paint the tank
        g2d.drawImage(Images.userTank,tankAt,null);

    }

    /**
     * Paint current gun
     */
    public void paintCurrentGun(Graphics2D g2d) {
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
                new SoundPlayer("Resources/Sounds/emptyGun.wav").run();
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

    /**
     * @return {@code true} if enemy is alive, {@code false} otherwise
     */
    @Override
    public boolean isAlive() {
        if(health > 0)
            return true;
        return false;
    }

    /**
     * Damage user thank by given value
     * @param damage is the given value
     */
    @Override
    public void damage(int damage) {
        health -= damage;
    }

    /**
     * @return rectangle of userTank
     */
    @Override
    public Rectangle2D getRect() {
        Rectangle2D tankRect = new Rectangle( state.locX , state.locY , 100 , 100 ) ;
        return tankRect ;
    }

    /**
     * Checks bullets intersect with drawables and damages them
     * @param drawable is given drawables
     */
    @Override
    public void checkIntersect(Drawable drawable) {

        Iterator<Bullet> bulletIterator = currentGun.getBullets().iterator();

        while (bulletIterator.hasNext() ) {
            if (drawable.getRect().intersects(bulletIterator.next().getRect())) {
                drawable.damage(currentGun.damage);
                bulletIterator.remove();
            }

        }

    }

    /**
     * @return current selected gun of tank
     */
    public static UserTankGun getCurrentGun() {
        return currentGun;
    }

    /**
     * @return main gun of tank
     */
    public UserTankGun getMainGun() {
        return mainGun;
    }

    /**
     * @return second gun of tank
     */
    public UserTankGun getSecondGun() {
        return secondGun;
    }

    /**
     * Fires second gun of tank (If mouse is pressed)
     */
    public void fireSecondGun(){
        if(System.currentTimeMillis() - lastShootTime > 200)
                if(mousePressed && getCurrentGun() == getSecondGun() && numberOfLightBullet > 0) {
                    getSecondGun().addBullets(state.mouseX,state.mouseY,state.locX,state.locY);
                    numberOfLightBullet -= 1;
                    lastShootTime = System.currentTimeMillis();

                    new SoundPlayer("Resources/Sounds/mashingun.wav").run();
                }
            }


    /**
     * Paint tank and health and bullet stats of tank using G2D
     * @param g2d is given G2D
     * @throws IOException
     */
    public void render(Graphics2D g2d) throws IOException {

        paintTank(g2d);

        paintCurrentGun(g2d);


        g2d.setColor(Color.ORANGE);

        g2d.setFont(g2d.getFont().deriveFont(20.0f));

        g2d.drawImage(numberOfHeavyBulletImage,null,50,50);

        g2d.drawString("" + numberOfHeavyBullet ,120,90);

        g2d.drawImage(numberOfLightBulletImage, null, 55, 110);

        g2d.drawString("" + numberOfLightBullet, 125, 145);

        if(health >= 1000)
            g2d.drawImage(Images.health5,null,600, 30);

        else if(health >= 750)
            g2d.drawImage(Images.health4,null,600, 30);

        else if(health >= 500)
            g2d.drawImage(Images.health3,null,600, 30);

        else if(health >= 250)
            g2d.drawImage(Images.health2,null,600, 30);

        else if(health > 0)
            g2d.drawImage(Images.health1,null,600, 30);
    }
}
