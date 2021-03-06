package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class that keep a Plant information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */

public class Plant implements Serializable{

    private int locX ;
    private int locY ;

    public Plant(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;

    }

    /**
     * @return LocY
     */
    public int getLocY() {
        return locY;
    }

    /**
     * @return LocX
     */
    public int getLocX() {
        return locX;
    }


}
