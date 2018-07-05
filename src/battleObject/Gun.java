package battleObject;
/**
 * This class is used for implementing
 * gun for tanks and other fighters
 *
 * @author Mohamad Chaman-Motlagh
 */

import java.awt.image.BufferedImage;

public class Gun {
    protected BufferedImage mod1Image;
    public BufferedImage currentModImage;

    public Gun(BufferedImage mod1Image){
        this.mod1Image = mod1Image;
        currentModImage = mod1Image;
    }
}
