package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that keep a Plant information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */

public class Plant {

    private int locX ;
    private int locY ;

    public Plant(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;

    }

    public Rectangle2D getRectangle2D() {
        return new Rectangle(locX , locY , 100 , 100 ) ;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }


}
