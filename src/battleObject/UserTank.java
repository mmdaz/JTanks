package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
        g2d.drawImage(ownTank,state.locX,state.locY,null);

        currentGun = ImageIO.read(new File("Resources/Images/tankGun01.png"));

        AffineTransform at = new AffineTransform();
        at.setToRotation(state.angle, state.locX + 50, state.locY + 50);
        g2d.setTransform(at);

        g2d.drawImage(currentGun,state.locX + 20,state.locY + 20,null);
    }
}
