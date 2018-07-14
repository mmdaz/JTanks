package battleObject;
/**
 * This class is used to create each Enemy tank
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

public class EnemyTank implements Drawable {
    public EnemyGun gun;
    private AffineTransform gunAT;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private long lastShootTime;
    private int activationDistance;
    private int health = 80;

    public EnemyTank(int activationDistance,int locX, int locY) throws IOException {
        gun = new EnemyGun(ImageIO.read(new File("Resources/Images/BigEnemyGun.png")) , ImageIO.read(new File("Resources/Images/Enemy2Bullet.png")),30);

        this.locX = locX;
        this.locY = locY;

        this.activationDistance = activationDistance;
    }
    /*
     * Paint gun
     */
    private void paintCurrentGun(Graphics2D g2d) {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
        double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset), targetX - (locX + Map.xOffset + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(Images.bigEnemyGun, gunAT,null);

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

    /*
     * Paint Thank body every moment
     * @throws IOException
     */
    private void paintTank(Graphics2D g2d) throws IOException {
        //paint the tank
        g2d.drawImage(Images.enemyTank,null,locX + Map.xOffset, locY + Map.yOffset);
    }

    /**
     * Renders EnemyThank at each using G2D
     * @param g2d is given G2D
     * @throws IOException
     */
    @Override
    public void render(Graphics2D g2d) throws IOException {
        paintTank(g2d);
        paintCurrentGun(g2d);
        forward();
    }

    /**
     * Fires a shot from enemy tank
     */
    public void fire(){
        if((Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance)) {
            if (System.currentTimeMillis() - lastShootTime > 500) {
                gun.addBullets(targetX, targetY, locX + Map.xOffset, locY + Map.yOffset);
                lastShootTime = System.currentTimeMillis();
                new SoundPlayer("Resources/Sounds/enemyshot.wav").run();
            }
        }
    }

    /**
     * Moves the tank in needed direction
     */
    private void forward(){
        if((Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance)) {
            if(targetX > (locX + Map.xOffset))
                locX += 2;
            else if (targetX < (locX + Map.xOffset))
                locX -= 2;
        }
    }

    /**
     * @return EnemyGun
     */
    public EnemyGun getGun() {
        return gun;
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
     * Damages a tank by given value
     * @param damge is the given value
     */
    @Override
    public void damage(int damge) {
        health -= damge;
    }

    /**
     * @return rectangle of tank
     */
    @Override
    public Rectangle2D getRect() {
        Rectangle2D tankRect = new Rectangle( locX + Map.xOffset , locY + Map.yOffset , 100 , 100 ) ;
        return tankRect ;
    }

    /**
     * Checks bullets intersect with drawables and damages them
     * @param drawable is given drawables
     */
    public void checkIntersect(Drawable drawable) {

        Iterator<Bullet> bulletIterator = gun.getBullets().iterator();

        while (bulletIterator.hasNext()) {
            if (drawable.getRect().intersects(bulletIterator.next().getRect())) {
                drawable.damage(gun.damage);
                bulletIterator.remove();
            }

        }
    }



}
