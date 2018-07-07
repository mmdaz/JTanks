package battleObject;
/**
 * This class is used for implementing
 * gun for tanks and other fighters
 *
 * @author Mohamad Chaman-Motlagh
 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Gun {
    protected BufferedImage level1Image;
    protected BufferedImage bulletImage;
    public BufferedImage currentModImage;

    public Gun(BufferedImage level1Image, BufferedImage bulletImage){
        this.level1Image = level1Image;
        currentModImage = level1Image;
        this.bulletImage = bulletImage;
    }

}
