package battleObject;
/**
 * This class is used to create and draw a tank
 *
 * @author Mohamad Chaman-Motlagh
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mine implements Drawable{

    private int locX;
    private int locY;
    private BufferedImage mineImage;
    private Graphics2D g2d;

    public Mine(int locX, int locY) throws IOException {
        this.locX = locX;
        this.locY = locY;

        mineImage = ImageIO.read(new File("Resources/Images/mine.png"));
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void render() throws IOException {
        g2d.drawImage(mineImage,null,locX,locY);
    }
}
