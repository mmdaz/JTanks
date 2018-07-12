package battleObject;
/**
 * This class is used to create each Enemy tank
 *
 * @author Mohamad Chaman-Motlagh
 */
import Map.Map;
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private int activationDistance;
    private int health = 80;

    public EnemyTank(int activationDistance,int locX, int locY) throws IOException {
        gun = new EnemyGun(ImageIO.read(new File("Resources/Images/BigEnemyGun.png")) , ImageIO.read(new File("Resources/Images/Enemy2Bullet.png")),30);
        tankBody = ImageIO.read(new File("Resources/Images/BigEnemy.png"));

        this.locX = locX;
        this.locY = locY;

        this.activationDistance = activationDistance;
    }
    /*
     * Paint gun
     */
    private void paintCurrentGun() {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
        double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset), targetX - (locX + Map.xOffset + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(gun.getGunImage(), gunAT,null);

    }

    @Override
    public void setG2d(Graphics2D g2d){
        this.g2d = g2d;
    }

    public void setTarget(int X, int Y){
        targetX = X;
        targetY = Y;
    }

    /*
     * Paint Thank body every moment
     * @throws IOException
     */
    private void paintTank() throws IOException {
        //paint the tank
        g2d.drawImage(tankBody,null,locX + Map.xOffset, locY + Map.yOffset);
    }

    @Override
    public void render() throws IOException {
        paintTank();
        paintCurrentGun();
    }

    public void fire(ArrayList<Drawable> drawables){
        if((Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance)) {
            if (System.currentTimeMillis() - lastShootTime > 500) {
                gun.addBullets(targetX, targetY, locX + Map.xOffset, locY + Map.yOffset);
                lastShootTime = System.currentTimeMillis();
                new SoundPlayer("Resources/Sounds/enemyshot.wav").run();
            }
        }
    }

    public EnemyGun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        if(health > 0)
            return true;
        return false;
    }


    @Override
    public void damage(int damge) {
        health -= damge;
    }

    @Override
    public Rectangle2D getRect() {
        Rectangle2D tankRect = new Rectangle( locX , locY , 100 , 100 ) ;
        return tankRect ;
    }

    public void checkIntersect(Drawable drawable) {

        for (Bullet bullet : gun.bullets ) {
            if (drawable.getRect().intersects(bullet.getRect()) && drawable instanceof UserTank); {
                drawable.damage(gun.damage);
            }

        }

    }

}
