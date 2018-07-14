package battleObject;
/**
 * This class is used to create a gun for
 * enemies that have gun
 *
 * @author Mohamad Chaman-Motlagh
 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EnemyGun extends Gun{
    private BufferedImage gunImage;

    public EnemyGun(BufferedImage gunImage, BufferedImage bulletImage, int damage){
        super(gunImage, bulletImage, damage);
        this.gunImage = gunImage;
    }

}
