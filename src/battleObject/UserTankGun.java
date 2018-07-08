package battleObject;

import bufferstrategy.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is used for implementing
 * gun for user tank
 * this class inherits Gun class
 *
 * @author Mohamad Chaman-Motlagh
 */

public class UserTankGun extends Gun{
    private BufferedImage level2Image;
    private int currentLevel;
    BufferedImage bulletImage;

    public UserTankGun(BufferedImage Level1Image, BufferedImage level2Image, BufferedImage bulletImage){
        super(Level1Image, bulletImage);
        currentLevel = 1;
        this.level2Image = level2Image;
        currentModImage = Level1Image;
        bullets = new ArrayList<Bullet>();
        this.bulletImage = bulletImage;
    }

    public boolean upgradeWeapon() {
        if(currentLevel >= 2)
            return false;
        currentLevel++;
        currentModImage = level2Image;
        return true;
    }

    public void addBullets(GameState state) {
        bullets.add(new Bullet(state.mouseX,state.mouseY,state.locX,state.locY,bulletImage));
    }

}
