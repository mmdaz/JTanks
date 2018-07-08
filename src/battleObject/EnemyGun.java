package battleObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EnemyGun extends Gun{
    private BufferedImage gunImage;

    public EnemyGun(BufferedImage gunImage, BufferedImage bulletImage){
        super(gunImage, bulletImage);
        this.gunImage = gunImage;
    }

    public BufferedImage getGunImage() {
        return gunImage;
    }
}
