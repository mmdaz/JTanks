package utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that read images of the game .
 * @author Azhdari Muhammd & Muhamad Chaman-Motlagh
 * @since summer 2018
 * @version 1.0
 *
 */

public class Images {

    public static BufferedImage softWall1 ;
    public static BufferedImage softWall2 ;
    public static BufferedImage softWall3 ;
    public static BufferedImage softWall4 ;
    public static BufferedImage hardWall ;
    public static BufferedImage repairItem ;
    public static BufferedImage teazel ;
    public static BufferedImage area ;
    public static BufferedImage plant ;
    public static BufferedImage cannonShell;
    public static BufferedImage machineGunShell;
    public static BufferedImage upgradeWeapon;
    public static BufferedImage health5;
    public static BufferedImage health4;
    public static BufferedImage health3;
    public static BufferedImage health2;
    public static BufferedImage health1;
    public static BufferedImage endFlag;

    public Images () {
        try {
            softWall1 = ImageIO.read(new File("Resources/Images/softWall.png")) ;
            softWall2 = ImageIO.read(new File("Resources/Images/softWall1.png")) ;
            softWall3 = ImageIO.read(new File("Resources/Images/softWall2.png")) ;
            softWall4 = ImageIO.read(new File("Resources/Images/softWall3.png")) ;
            hardWall = ImageIO.read(new File("Resources/Images/hardWall.png")) ;
            repairItem = ImageIO.read( new File("Resources/Images/RepairFood.png")) ;
            teazel = ImageIO.read(new File("Resources/Images/teazel.png"));
            area = ImageIO.read(new File("Resources/Images/RedEarth.png"));
            plant = ImageIO.read(new File("Resources/Images/plant.png"));
            cannonShell = ImageIO.read( new File("Resources/Images/CannonFood.png")) ;
            machineGunShell = ImageIO.read( new File("Resources/Images/MashinGunFood.png")) ;
            upgradeWeapon = ImageIO.read( new File("Resources/Images/upgrader.png")) ;
            health5 = ImageIO.read( new File("Resources/Images/5health.png"));
            health4 = ImageIO.read( new File("Resources/Images/4health.png"));
            health3 = ImageIO.read( new File("Resources/Images/3health.png"));
            health2 = ImageIO.read( new File("Resources/Images/2health.png"));
            health1 = ImageIO.read( new File("Resources/Images/1health.png"));

           endFlag = ImageIO.read( new File("Resources/Images/EndFlag.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
