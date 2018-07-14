package Map;

import utility.Images;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * The class that keep CannonShell information on the map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */


public class CannonShell implements Serializable{

    private int locX ;
    private int locY ;
    private boolean status  ;



    public CannonShell(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        status = true ;

    }

    /**
     * Sets LocX of CannonShells
     * @param locX
     */
    public void setLocX(int locX) {
        this.locX = locX;
    }

    /**
     * Sets LocY of CannonShells
     * @param locY
     */
    public void setLocY(int locY) {
        this.locY = locY;
    }

    /**
     * @return rectangle of current cannonShell
     */
    public Rectangle2D getRectangle2D() {

        return new Rectangle(locX , locY , 80 , 80) ;
    }

    /**
     * Sets status of cannonShell
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return status of cannonShell
     */
    public boolean getStatus() {

        return status;
    }

    /**
     * @return LocY of cannonShell
     */
    public int getLocY() {
        return locY;
    }

    /**
     * @return locX of cannonShell
     */
    public int getLocX() {
        return locX;
    }



}
