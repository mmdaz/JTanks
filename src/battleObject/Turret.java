package battleObject;
/**
 * This class is used to create and draw
 * a fixed turret
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

public class Turret implements Drawable{
    private BufferedImage turretBody;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private AffineTransform gunAT;
    private Graphics2D g2d;
    public EnemyGun gun;
    private long lastShootTime;
    private int activationDistance;
    private int health = 100;

    public Turret(int activationDistance,int locX, int locY){
        try {
            turretBody = ImageIO.read(new File("Resources/Images/TurretBody.png"));
            gun = new EnemyGun(ImageIO.read(new File("Resources/Images/TurretGun.png")) , ImageIO.read(new File("Resources/Images/EnemyBullet1.png")), 35);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.locX = locX;
        this.locY = locY;
        this.activationDistance = activationDistance;

    }


    @Override
    public void render() throws IOException {
        paintTurret();
        paintCurrentGun();
    }

    @Override
    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public boolean isAlive() {
        if(health > 0)
            return true;
        return false;
    }


    @Override
    public void damage(int damage) {
        health -= damage;
    }

    @Override
    public Rectangle2D getRect() {
        Rectangle2D turrectRect = new Rectangle( locX , locY , 100 ,100 ) ;
        return turrectRect ;
    }

    public void setTarget(int X, int Y){
        targetX = X;
        targetY = Y;
    }

    private void paintCurrentGun() {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
        double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset), targetX - (locX + Map.xOffset + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(gun.getGunImage(), gunAT,null);

    }

    private void paintTurret() throws IOException {
        //paint the turret
        g2d.drawImage(turretBody,null,locX + Map.xOffset, locY + Map.yOffset);
    }

    public void fire() {
        if(Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance)
        {
            if (System.currentTimeMillis() - lastShootTime > 500) {
                gun.addBullets(targetX, targetY, locX + Map.xOffset, locY + Map.yOffset);
                lastShootTime = System.currentTimeMillis();
                new SoundPlayer("Resources/Sounds/enemyshot.wav").run();
            }
        }
    }

    public void checkIntersect(Drawable drawable) {

        for (Bullet bullet : gun.bullets ) {
            if (drawable.getRect().intersects(bullet.getRect())); {
                drawable.damage(gun.damage);
            }
        }

    }

    public EnemyGun getGun() {
        return gun;
    }
}
