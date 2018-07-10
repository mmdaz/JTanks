package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SoftWall {

    private int locX ;
    private int locY ;
    private BufferedImage image ;
    private Rectangle2D rectangle2D ;

    public SoftWall (int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        try {
            image = ImageIO.read(new File("Resources/Images/softWall.png")) ;
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
