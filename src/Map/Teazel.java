package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that keep a Teazel information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */

public class Teazel {

    private int locX ;
    private int locY ;


    public Teazel(int locX , int locY)  {

        this.locX = locX ;
        this.locY = locY ;

    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public Rectangle2D getRectangle2D() {
        return new Rectangle(locX , locY , 100 , 100 );
    }


}
