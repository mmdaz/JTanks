package Map;

import utility.Images;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The class that keep CannonShell information on the map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */


public class CannonShell {

    private int locX ;
    private int locY ;
    private BufferedImage image ;
    private Rectangle2D rectangle2D ;
    private boolean status  ;



    public CannonShell(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        image = Images.cannonShell;
        rectangle2D = new Rectangle(locx , locY , 100 , 100 ) ;
        status = true ;

    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setRectangle2D(Rectangle2D rectangle2D) {
        this.rectangle2D = rectangle2D;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle2D getRectangle2D() {


        rectangle2D = new Rectangle(locX , locY , 80 , 80) ;
        return rectangle2D;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {

        return status;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }



}
