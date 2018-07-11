package battleObject;
/**
 * This class is used for implementing
 * gun for tanks and other fighters
 *
 * @author Mohamad Chaman-Motlagh
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import Map.* ;

public class Gun {
    protected BufferedImage level1Image;
    protected BufferedImage bulletImage;
    public BufferedImage currentModImage;
    protected ArrayList<Bullet> bullets;

    public Gun(BufferedImage level1Image, BufferedImage bulletImage){
        this.level1Image = level1Image;
        currentModImage = level1Image;
        this.bulletImage = bulletImage;
        bullets = new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while(bulletIterator.hasNext()){
            if (Map.checkBulletCollision(bulletIterator.next()))
                bulletIterator.remove();
        }
        return bullets;
    }

    public void addBullets(int targetX, int targetY, int locX, int locY) {
        bullets.add(new Bullet(targetX,targetY,locX,locY,bulletImage));
    }


}
