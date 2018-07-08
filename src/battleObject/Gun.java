package battleObject;
/**
 * This class is used for implementing
 * gun for tanks and other fighters
 *
 * @author Mohamad Chaman-Motlagh
 */

import bufferstrategy.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Gun {
    protected BufferedImage level1Image;
    protected BufferedImage bulletImage;
    public BufferedImage currentModImage;
    protected ArrayList<Bullet> bullets;

    public Gun(BufferedImage level1Image, BufferedImage bulletImage){
        this.level1Image = level1Image;
        currentModImage = level1Image;
        this.bulletImage = bulletImage;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
