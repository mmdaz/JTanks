package battleObject;
/**
 * This class is used to create and draw a tank
 *
 * @author Mohamad Chaman-Motlagh
 */

import Map.Map;
import utility.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mine implements Drawable{

    private int locX;
    private int locY;
    private int health = 20;

    public Mine(int locX, int locY) throws IOException {
        this.locX = locX;
        this.locY = locY;

    }

    @Override
    public void render(Graphics2D g2d) throws IOException {
        g2d.drawImage(Images.mine,null,locX +  Map.xOffset,locY + Map.yOffset);
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
        return new Rectangle( locX  + Map.xOffset, locY + Map.yOffset, 50 , 50 ) ;
    }

    public void checkIntersect(Drawable drawable) {
        Rectangle2D tankRect = new Rectangle((int)drawable.getRect().getX(), (int)drawable.getRect().getY(), 60, 60);

        if (tankRect.intersects(getRect())) {
            drawable.damage(50);
            health = 0;
        }

    }
}
