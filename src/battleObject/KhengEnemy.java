package battleObject;

import Map.Map;
import utility.Images;

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
    private boolean isRendered;
    private int speed = 4;
    private int health = 50;

    public KhengEnemy(int activationDistance, int locX, int locY) throws IOException {
        this.activationDistance = activationDistance;
        this.locX = locX;
        this.locY = locY;


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
    public void render(Graphics2D g2d) throws IOException {
        if((Math.abs(targetX - (locX + Map.xOffset)) < activationDistance && Math.abs(targetY - (locY + Map.yOffset)) < activationDistance) || isRendered) {
            AffineTransform khEnemyAT = new AffineTransform();
            khEnemyAT.setToTranslation(locX + 50 + Map.xOffset, locY + 50 + Map.yOffset);
            double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset * 2), targetX - (locX + 50 + Map.yOffset * 2));
            khEnemyAT.rotate(angle);
            khEnemyAT.translate(-50, -50);
            //paint the tank
            isRendered = true;
            g2d.drawImage(Images.khengEnemy, khEnemyAT, null);
            forward();
        }
    }

    private void forward(){
        if(isRendered) {
            double angle = Math.atan2(targetY - (locY + 50 + Map.yOffset * 2), targetX - (locX + 50 + Map.yOffset * 2));
            locX += Math.cos(angle) * speed;
            locY += Math.sin(angle) * speed;
        }
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
        return new Rectangle( locX + Map.xOffset, locY + Map.yOffset , 100 , 100 ) ;
    }

    public void checkIntersect(Drawable drawable) {

            if (drawable.getRect().intersects(getRect())) {
                drawable.damage(250);
                health = 0;
            //System.out.println("Shit");
            }

        }
}

