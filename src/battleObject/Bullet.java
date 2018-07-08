package battleObject;
/**
 * This class is used for creating and moving bullets
 *
 * @author Mohamad Chaman-Motlagh
 *
 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet{

    private double angle;
    private BufferedImage bulletImage;
    private int X;
    private int Y;
    private int speed;

    public Bullet(int mouseX, int mouseY, int locationX, int locationY, BufferedImage bulletImage) {
        this.bulletImage = bulletImage;
        X = locationX + 50;
        Y = locationY + 50;
        angle = Math.atan2(mouseY - (locationY + 50), mouseX - (locationX + 50));

        speed = 8;
        for (int i = 0; i < 8; i++) {
            forward();
        }
    }

    public void paint(Graphics2D g2d) {
        forward();
        AffineTransform bulletAT = new AffineTransform();
        bulletAT.setToTranslation(X , Y);
        bulletAT.rotate(angle);
        g2d.drawImage(bulletImage, bulletAT, null);
    }

    public void forward(){
        X += Math.cos(angle) * speed;
        Y += Math.sin(angle) * speed;
    }

}