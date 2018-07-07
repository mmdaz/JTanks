package battleObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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

    public UserTankGun(BufferedImage Level1Image, BufferedImage level2Image,BufferedImage bulletImage){
        super(Level1Image, bulletImage);
        currentLevel = 1;
        this.level2Image = level2Image;
        currentModImage = Level1Image;
    }

    public boolean upgradeWeapon() {
        if(currentLevel >= 2)
            return false;
        currentLevel++;
        currentModImage = level2Image;
        return true;
    }
    @Override
    public void fire(int mouseX, int mouseY, int locationX, int locationY, Graphics2D g2d, double angle){
        AffineTransform bulletAT = new AffineTransform();
        bulletAT.setToTranslation(mouseX + 30, mouseY + 30);
        bulletAT.rotate(angle);
        bulletAT.translate(-30, -30);
        g2d.drawImage(bulletImage, bulletAT,null);
    }

}
