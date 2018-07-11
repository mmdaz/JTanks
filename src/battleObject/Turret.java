package battleObject;
/**
 * This class is used to create and draw
 * a fixed turret
 *
 * @author Mohamad Chaman-Motlagh
 */
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Turret implements Drawable{
    private BufferedImage turretBody;
    private int locX;
    private int locY;
    private int targetX;
    private int targetY;
    private AffineTransform gunAT;
    private Graphics2D g2d;
    public EnemyGun gun;
    private long lastShootTime;

    public Turret(int locX, int locY){
        try {
            turretBody = ImageIO.read(new File("Resources/Images/TurretBody.png"));
            gun = new EnemyGun(ImageIO.read(new File("Resources/Images/TurretGun.png")) , ImageIO.read(new File("Resources/Images/EnemyBullet1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.locX = locX;
        this.locY = locY;
    }




    @Override
    public void render() throws IOException {
        paintTurret();
        paintCurrentGun();
    }

    @Override
    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void setTarget(int X, int Y){
        targetX = X;
        targetY = Y;
    }

    private void paintCurrentGun() {
        gunAT = new AffineTransform();
        gunAT.setToTranslation(locX + 50, locY + 50);
        double angle = Math.atan2(targetY - (locY + 50), targetX - (locX + 50));
        gunAT.rotate(angle);
        gunAT.translate(-20, -20);
        g2d.drawImage(gun.getGunImage(), gunAT,null);

    }

    private void paintTurret() throws IOException {
        //paint the turret
        g2d.drawImage(turretBody,null,locX, locY);
    }

    public void fire(){
        if(System.currentTimeMillis() - lastShootTime > 200) {
            gun.addBullets(targetX,targetY,locX,locY);
            new SoundPlayer("Resources/Sounds/enemyshot.wav").run();
            lastShootTime = System.currentTimeMillis();
        }
    }

    public EnemyGun getGun() {
        return gun;
    }
}
