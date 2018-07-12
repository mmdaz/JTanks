package Map;

import utility.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that keep Repair Item information on the map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */

public class RepairPackItem {

    private int locX ;
    private int locY ;
    private BufferedImage image ;
    private Rectangle2D rectangle2D ;



    public RepairPackItem(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        image = Images.repairItem ;
        rectangle2D = new Rectangle(locx , locY , 100 , 100 ) ;

    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle2D getRectangle2D() {
        return rectangle2D;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }


}
