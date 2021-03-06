package battleObject;
/**
 * This class is used to create and draw
 * a fixed turret
 *
 * @author Mohamad Chaman-Motlagh
 */
import Map.Map;
import utility.Images;
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Turret implements Drawable{
    private BufferedImage turretBody;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private AffineTransform gunAT;
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

    /**
     * Paint each Turret using G2D
     * @param g2d is given G2D
     * @throws IOException
     */
    @Override
    public void render(Graphics2D g2d) throws IOException {
        paintTurret(g2d);
        paintCurrentGun(g2d);
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
     * Damage turret by given value
     * @param damage is the given value
     */
    @Override
    public void damage(int damage) {
        health -= damage;
    }

    /**
     * @return rectangle for current turret
     */
    @Override
    public Rectangle2D getRect() {
        Rectangle2D turrectRect = new Rectangle( locX + Map.xOffset, locY + Map.yOffset , 100 ,100 ) ;
        return turrectRect ;
    }
    /**
     * Sets target for an enemy Tank
     * @param X is given X position of target
     * @param Y is given Y position of target
     */
    public void setTarget(int X, int Y){
        targetX = X;
        targetY = Y;
    }

    private void paintCurrentGun(Graphics2D g2d) {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
        double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset), targetX - (locX + Map.xOffset + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(Images.turretHeadGun, gunAT,null);

    }

    private void paintTurret(Graphics2D g2d) throws IOException {
        //paint the turret
        g2d.drawImage(turretBody,null,locX + Map.xOffset, locY + Map.yOffset);
    }

    /**
     * Fires a shot from enemy tank
     */
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

    /**
     * Checks bullets intersect with drawables and damages them
     * @param drawable is given drawables
     */
    public void checkIntersect(Drawable drawable) {

        Iterator<Bullet> bulletIterator = gun.getBullets().iterator();
        while (bulletIterator.hasNext() ) {
            if (drawable.getRect().intersects(bulletIterator.next().getRect())) {
                drawable.damage(gun.damage);
                bulletIterator.remove();
            }
        }


    }

    /**
     * @return gun of enemy
     */
    public EnemyGun getGun() {
        return gun;
    }
}
