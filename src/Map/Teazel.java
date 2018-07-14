package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class that keep a Teazel information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */

public class Teazel implements Serializable{

    private int locX ;
    private int locY ;


    public Teazel(int locX , int locY)  {

        this.locX = locX ;
        this.locY = locY ;

    }

    /**
     * set LocX of Teazel
     * @param locX
     */
    public void setLocX(int locX) {
        this.locX = locX;
    }

    /**
     * Set locY of
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
     * @return LocY
     */
    public int getLocY() {
        return locY;
    }

    /**
     * @return rectangle of Teazel
     */
    public Rectangle2D getRectangle2D() {
        return new Rectangle(locX , locY , 100 , 100 );
    }


}
