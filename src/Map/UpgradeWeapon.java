package Map;
/**
 * The class that keep UpgradeWeapon information on the map .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */
import utility.Images;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class UpgradeWeapon implements Serializable{

    private int locX ;
    private int locY ;
    private boolean status  ;

    public UpgradeWeapon(int locx , int locY){
        this.locX = locx ;
        this.locY = locY ;
        status = true ;
    }

    /**
     * Sets LocX
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
     * @return rectangle of UpgradePack
     */
    public Rectangle2D getRectangle2D() {

        return new Rectangle(locX , locY , 100 , 100 );
    }

    /**
     * Set status of upgradePack
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return status of upgrade pack
     */
    public boolean getStatus() {

        return status;
    }

    /**
     * @return LocX
     */
    public int getLocY() {
        return locY;
    }

    /**
     * @return LocY
     */
    public int getLocX() {
        return locX;
    }



}
