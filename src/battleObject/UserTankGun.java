package battleObject;

import java.awt.image.BufferedImage;

/**
 * This class is used for implementing
 * gun for user tank
 * this class inherits Gun class
 *
 * @author Mohamad Chaman-Motlagh
 */

public class UserTankGun extends Gun{
    private BufferedImage mod2Image;
    private int currentMod;

    public UserTankGun(BufferedImage mod1Image, BufferedImage mod2Image){
        super(mod1Image);
        currentMod = 1;
        this.mod2Image = mod2Image;
        currentModImage = mod1Image;
    }

    public boolean upgradeWeapon() {
        if(currentMod >= 2)
            return false;
        currentMod++;
        currentModImage = mod2Image;
        return true;
    }


}
