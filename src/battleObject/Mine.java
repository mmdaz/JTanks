package battleObject;
/**
 * This class is used to create and draw a tank
 *
 * @author Mohamad Chaman-Motlagh
 */

import Map.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mine implements Drawable{

    private int locX;
    private int locY;
    private BufferedImage mineImage;
    private Graphics2D g2d;
    private int health = 20;

    public Mine(int locX, int locY) throws IOException {
        this.locX = locX;
        this.locY = locY;

        mineImage = ImageIO.read(new File("Resources/Images/mine.png"));
    }
    @Override
    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void render() throws IOException {
        g2d.drawImage(mineImage,null,locX +  Map.xOffset,locY + Map.yOffset);
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
        return new Rectangle( locX + 30, locY + 30, 50 , 50 ) ;
    }

    public void checkIntersect(Drawable drawable) {

        if (drawable.getRect().intersects(getRect())) {
            drawable.damage(50);
            health = 0;
        }

    }
}
