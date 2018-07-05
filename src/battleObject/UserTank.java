package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to create user played tank tanks
 *
 * @author MohamadCM
 */
public class UserTank {
    private GameState state;
    private Gun mainGun;
    private Gun secondGun;
    private BufferedImage currentGun;
    private Graphics2D g2d;
    public int tankCenterX;
    public int TankCenterY;

    public UserTank(GameState state, Graphics2D g2d) throws IOException {
        this.state = state;
        this.g2d = g2d;

        BufferedImage ownTank = ImageIO.read(new File("Resources/Images/tank.png"));

        AffineTransform at = new AffineTransform();
        at.setToTranslation(state.locX + 50, state.locY + 50);
        at.rotate(state.tankAngle);
        at.translate(-50, -50);
        //paint the tank
        g2d.drawImage(ownTank,at,null);

        currentGun = ImageIO.read(new File("Resources/Images/tankGun01.png"));
        paintCurrentGun(state, g2d);


    }
    //Paint current gun
    public void paintCurrentGun(GameState state, Graphics2D g2d){
        AffineTransform at = new AffineTransform();
        at.setToTranslation(state.locX + 50, state.locY + 50);
        at.rotate(state.angle);
        at.translate(-30, -30);
        g2d.drawImage(currentGun,at,null);

    }
}
