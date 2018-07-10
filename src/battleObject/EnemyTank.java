package battleObject;
/**
 * This class is used to create each Enemy tank
 *
 * @author Mohamad Chaman-Motlagh
 */
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyTank implements Drawable {
    public EnemyGun gun;
    private Graphics2D g2d;
    private AffineTransform gunAT;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private BufferedImage tankBody;
    private long lastShootTime;

    public EnemyTank(int locX, int locY) throws IOException {
        gun = new EnemyGun(ImageIO.read(new File("Resources/Images/BigEnemyGun.png")) , ImageIO.read(new File("Resources/Images/Enemy2Bullet.png")));
        tankBody = ImageIO.read(new File("Resources/Images/BigEnemy.png"));

        this.locX = locX;
        this.locY = locY;
    }
    /*
     * Paint gun
     */
    private void paintCurrentGun() {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50, locY + 50);
        double angle = Math.atan2(targetY - (locY + 50), targetX - (locX + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(gun.getGunImage(), gunAT,null);

    }


    public void setG2d(Graphics2D g2d){
        this.g2d = g2d;
    }

    public void setTarget(int X, int Y){
        targetX = X;
        targetY = Y;
    }

    /*
     * Paint Thank body every moment
     */
    private void paintTank() throws IOException {
        //paint the tank
        g2d.drawImage(tankBody,null,locX, locY);
    }

    @Override
    public void render() throws IOException {
        paintTank();
        paintCurrentGun();
    }

    public void fire(){
        if(System.currentTimeMillis() - lastShootTime > 500) {
                gun.addBullets(targetX,targetY,locX,locY);
                new SoundPlayer("Resources/Sounds/enemyshot.wav").run();
                lastShootTime = System.currentTimeMillis();
            }
    }

    public EnemyGun getGun() {
        return gun;
    }
}
