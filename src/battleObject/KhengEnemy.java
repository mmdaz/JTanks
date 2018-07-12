package battleObject;

import Map.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KhengEnemy implements Drawable {
    private int activationDistance;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private BufferedImage khengEnemyImage;
    private Graphics2D g2d;
    private boolean isRendered;
    private int speed = 8;
    private int health = 50;

    public KhengEnemy(int activationDistance, int locX, int locY) throws IOException {
        this.activationDistance = activationDistance;
        this.locX = locX;
        this.locY = locY;

        khengEnemyImage = ImageIO.read(new File("Resources/Images/KhengEnemy.png"));

    }

    /**
     * Sets target for this Enemy
     * @param targetX is X of target
     * @param targetY is Y of target
     */
    public void setTarget(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void render() throws IOException {
        if((Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance) || isRendered) {
            forward();
            AffineTransform khEnemyAT = new AffineTransform();
            khEnemyAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
            double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset * 2), targetX - (locX + 50 + Map.yOffset * 2));
            khEnemyAT.rotate(angle);
            khEnemyAT.translate(-50, -50);
            //paint the tank
            isRendered = true;
            g2d.drawImage(khengEnemyImage, khEnemyAT, null);
        }
    }

    private void forward(){ {
        if(isRendered) {
            double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset * 2), targetX - (locX + 50 + Map.yOffset * 2));
            locX += Math.cos(angle) * speed;
            locY += Math.sin(angle) * speed;
        }
        }
    }

    /**
     * Sets G2D
     * @param g2d is given G2D
     */
    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public boolean isAlive() {
        if(health > 0)
            return true;
        return false;
    }

    @Override
    public void damage(int damage) {
        health -= damage;
    }

    @Override
    public Rectangle2D getRect() {
        return new Rectangle( locX , locY , 100 , 100 ) ;
    }

    public void checkIntersect(Drawable drawable) {

            if (drawable.getRect().intersects(getRect())); {
                drawable.damage(50);
                health = 0;
            }

        }

}
