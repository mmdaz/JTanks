package Map;

import utility.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that keep a Soft Wall information for draw it on the Map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 *
 */

public class SoftWall {

    private int locX ;
    private int locY ;
    private Rectangle2D rectangle2D ;
    private int mode ;

    public SoftWall (int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;

        mode = 1 ;

    }



    public Rectangle2D getRectangle2D() {
        return new Rectangle(locX , locY , 100 , 100 );
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }


    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setLocX(int locX) {
        this.locX = locX;

        rectangle2D = new Rectangle(locX , locY , 100 , 100 ) ;
    }

    public void setLocY(int locY) {
        this.locY = locY;

        rectangle2D = new Rectangle(locX , locY , 100 , 100 ) ;
    }
}
