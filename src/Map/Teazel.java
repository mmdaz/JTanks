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
    private BufferedImage image ;
    private Rectangle2D rectangle2D ;


    public Teazel(int locX , int locY)  {

        try {
            image = ImageIO.read(new File("Resources/Images/teazel.png")) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.locX = locX ;
        this.locY = locY ;
        rectangle2D = new Rectangle(locX , locY , 100 , 100 ) ;

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
        return rectangle2D;
    }


}
