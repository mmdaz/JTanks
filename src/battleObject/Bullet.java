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

    public Bullet(int mouseX, int mouseY, int locationX, int locationY, BufferedImage bulletImage){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.locationX = locationX;
        this.locationY = locationY;
        this.angle = angle;
        this.bulletImage = bulletImage;
        X = locationX + 50;
        Y = locationY  + 50;
        angle = Math.atan2(mouseY - (locationY + 50) , mouseX - (locationX + 50) );

        slope = -(mouseY - locationY)/(mouseX - locationX);
        /*if((mouseX - locationX) > 0)
            slope = -slope;*/
        speed = 8;
        for (int i = 0; i < 6; i++) {
            forward();
        }
    }
    public void forward(){
        X += speed/Math.sqrt(1 + Math.pow(slope,2));
        Y -= (speed * slope)/Math.sqrt(1 + Math.pow(slope,2));
    }
    public void paint(Graphics2D g2d) {
        forward();
        AffineTransform bulletAT = new AffineTransform();
        bulletAT.setToTranslation(X, Y);
        bulletAT.rotate(angle);
        g2d.drawImage(bulletImage, bulletAT, null);
    }
}