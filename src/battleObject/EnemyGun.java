package battleObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EnemyGun {
    private BufferedImage gunImage;

    public EnemyGun(BufferedImage gunImage){
        this.gunImage = gunImage;
    }

    public BufferedImage getGunImage() {
        return gunImage;
    }
}
