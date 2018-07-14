package battleObject;
/**
 * This class is used for implementing
 * gun for tanks and other fighters
 *
 * @author Mohamad Chaman-Motlagh
 */

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import Map.* ;

public class Gun implements Serializable{
    protected BufferedImage level1Image;
    protected BufferedImage bulletImage;
    public BufferedImage currentModImage;
    public ArrayList<Bullet> bullets;
    public int damage;

    public Gun(BufferedImage level1Image, BufferedImage bulletImage,int damage){
        this.level1Image = level1Image;
        currentModImage = level1Image;
        this.bulletImage = bulletImage;
        bullets = new ArrayList<Bullet>();
        this.damage = damage;
    }

    /**
     * @return bullets for each gun and removes the in case of intersect with walls
     */
    public ArrayList<Bullet> getBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while(bulletIterator.hasNext()){
            if (Map.checkBulletCollision(bulletIterator.next()))
                bulletIterator.remove();
        }
        return bullets;
    }

    /**
     * Add a bullet to guns bullets
     * @param targetX is X position of bullet
     * @param targetY is Y position of bullets
     * @param locX is current X location of bullet
     * @param locY is current Y location of bullet
     */
    public void addBullets(int targetX, int targetY, int locX, int locY) {
        bullets.add(new Bullet(targetX,targetY,locX,locY,bulletImage,damage));
    }


}
