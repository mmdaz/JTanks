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
    private BufferedImage image ;
    private Rectangle2D rectangle2D ;

    public Plant(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        try {
            image = ImageIO.read(new File("Resources/Images/plant.png")) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        rectangle2D = new Rectangle(locx , locY , 100 , 100 ) ;
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
