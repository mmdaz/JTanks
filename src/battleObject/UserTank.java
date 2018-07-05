package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private UserTankGun mainGun;
    private UserTankGun secondGun;
    private UserTankGun currentGun;
    private BufferedImage currentGunImage;
    private boolean isMainGun;
    private Graphics2D g2d;
    private MouseHandler tankMouseHandler;
    private AffineTransform at;


    public UserTank(GameState state, Graphics2D g2d) throws IOException {
        this.state = state;
        this.g2d = g2d;

        BufferedImage ownTank = ImageIO.read(new File("Resources/Images/tank.png"));

        at = new AffineTransform();

        AffineTransform tankAt = new AffineTransform();
        tankAt.setToTranslation(state.locX + 50, state.locY + 50);
        tankAt.rotate(state.tankAngle);
        tankAt.translate(-50, -50);
        //paint the tank
        g2d.drawImage(ownTank,tankAt,null);

        mainGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun01.png")) , ImageIO.read(new File("Resources/Images/tankGun1.png")));

        secondGun = new UserTankGun( ImageIO.read(new File("Resources/Images/tankGun02.png")) , ImageIO.read(new File("Resources/Images/tankGun2.png")));

        currentGun = mainGun;
        currentGunImage = mainGun.currentModImage;
        isMainGun = true;

        paintCurrentGun();

        tankMouseHandler = new MouseHandler();
        }

    /*
     * Paint current gun
     */
    private void paintCurrentGun() {
        at.setToTranslation(state.locX + 50, state.locY + 50);
        at.rotate(state.angle);
        at.translate(-30, -30);
        g2d.drawImage(currentGunImage,at,null);

    }

    /*
     * Changes the current gun
     * and paint the new gun
     */
    private void changeGun(){
        if(isMainGun) {
            currentGun = secondGun;
            currentGunImage = secondGun.currentModImage;
            isMainGun = false;
        }
        else {
            currentGun = mainGun;
            isMainGun = true;
        }
    }
    /**
     * The mouse handler for tank actions
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent mouseEvent){
            if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
                changeGun();
            }
        }
    }

    /**
     * @return tank mouse handler
     */
    public MouseHandler getTankMouseHandler() {
        return tankMouseHandler;
    }
}
