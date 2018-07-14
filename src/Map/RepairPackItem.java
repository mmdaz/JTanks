package Map;

import utility.Images;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
 * The class that keep Repair Item information on the map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */

public class RepairPackItem implements Serializable{

    private int locX ;
    private int locY ;
    private boolean status  ;



    public RepairPackItem(int locx , int locY ) {

        this.locX = locx ;
        this.locY = locY ;
        status = true ;

    }

    /**
     * sets LocX for repairPackItem
     * @param locX
     */
    public void setLocX(int locX) {
        this.locX = locX;
    }

    /**
     * Sets LocY
     * @param locY
     */
    public void setLocY(int locY) {
        this.locY = locY;
    }

    /**
     * @return rectangle of current object
     */
    public Rectangle2D getRectangle2D() {


        return new Rectangle(locX , locY , 80 , 80 );
    }

    /**
     * Sets status for current rePairPackItem
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return status of current repairPackItem
     */
    public boolean getStatus() {

        return status;
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
