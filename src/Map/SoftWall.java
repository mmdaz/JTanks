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
    private BufferedImage imageMode1 ;
    private BufferedImage imageMode2 ;
    private BufferedImage imageMode3 ;
    private BufferedImage imageMode4 ;
    private Rectangle2D rectangle2D ;
    private int mode ;

    public SoftWall (int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;

        mode = 1 ;

        imageMode1 = Images.softWall1 ;
        imageMode2 = Images.softWall2 ;
        imageMode3 = Images.softWall3 ;
        imageMode4 = Images.softWall4 ;

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

    public BufferedImage getImageMode1() {
        return imageMode1;
    }

    public BufferedImage getImageMode2() {
        return imageMode2;
    }

    public BufferedImage getImageMode3() {
        return imageMode3;
    }

    public BufferedImage getImageMode4() {
        return imageMode4;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }
}
