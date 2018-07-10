package battleObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
        if((Math.abs(targetX - locX) < activationDistance && Math.abs(targetY - locY) < activationDistance) || isRendered) {
            AffineTransform khEnemyAT = new AffineTransform();
            khEnemyAT.setToTranslation(locX + 50, locY + 50);
            double angle = Math.atan2(targetY - (locY + 50), targetX - (locX + 50));
            khEnemyAT.rotate(angle);
            khEnemyAT.translate(-50, -50);
            //paint the tank
            isRendered = true;
            g2d.drawImage(khengEnemyImage, khEnemyAT, null);
        }
    }

    /**
     * Sets G2D
     * @param g2d is given G2D
     */
    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }
}
