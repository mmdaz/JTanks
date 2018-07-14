package Map;

import utility.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class that keep a Hard Wall information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */
public class HardWall implements Serializable{

    private int locX ;
    private int locY ;


    public HardWall(int locX , int locY)  {

        this.locX = locX ;
        this.locY = locY ;

    }

    /**
     * Sets LocX
     * @param locX
     */
    public void setLocX(int locX) {
        this.locX = locX;
    }

    /**
     * Sets LocY
     * @param locY
     */
    public void setLocY(int locY) {
        this.locY = locY;
    }

    /**
     * @return LocX
     */
    public int getLocX() {
        return locX;
    }

    /**
     * @return Y
     */
    public int getLocY() {
        return locY;
    }

    /**
     * @return rectangle of current object
     */
    public Rectangle2D getRectangle2D() {
        return new Rectangle(locX , locY , 100 , 100 );
    }

    /**
     * @return Image of hardWall
     */
    public BufferedImage getImage() {
        return Images.hardWall;
    }
}
