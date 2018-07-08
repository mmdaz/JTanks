package battleObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet{

    private int mouseX;
    private int mouseY;
    private int locationX;
    private int locationY;
    private double angle;
    private BufferedImage bulletImage;
    private int X;
    private int Y;
    private int speed;
    private int slope;

    public Bullet(int mouseX, int mouseY, int locationX, int locationY, BufferedImage bulletImage) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.locationX = locationX;
        this.locationY = locationY;
        this.bulletImage = bulletImage;
        X = locationX + 50;
        Y = locationY + 50;
        angle = Math.atan2(mouseY - (locationY + 50), mouseX - (locationX + 50));

        speed = 8;
        for (int i = 0; i < 6; i++) {
            forward();
        }
    }

    public void paint(Graphics2D g2d) {
        forward();
        AffineTransform bulletAT = new AffineTransform();
        bulletAT.setToTranslation(X + 10, Y + 10);
        bulletAT.rotate(angle);
        g2d.drawImage(bulletImage, bulletAT, null);
    }

    public void forward(){
        X += Math.cos(angle) * speed;
        Y += Math.sin(angle) * speed;
    }

}