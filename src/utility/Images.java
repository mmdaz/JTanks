package utility;

import javax.imageio.ImageIO;
import java.awt.*;
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
    public static BufferedImage userTank ;
    public static BufferedImage turret ;
    public static BufferedImage mine ;
    public static BufferedImage khengEnemy ;
    public static BufferedImage enemyTank ;
    public static BufferedImage bigEnemyGun ;
    public static BufferedImage turretHeadGun ;
    public static BufferedImage tankGun01 ;
    public static BufferedImage tankGun1 ;
    public static BufferedImage tankGun02 ;
    public static BufferedImage tankGun2 ;


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
           userTank = ImageIO.read( new File("Resources/Images/tank.png"));
           khengEnemy = ImageIO.read( new File("Resources/Images/KhengEnemy.png"));
           enemyTank = ImageIO.read( new File("Resources/Images/BigEnemy.png"));
           mine = ImageIO.read( new File("Resources/Images/mine.png"));
           turret = ImageIO.read( new File("Resources/Images/TurretBody.png"));
           turretHeadGun = ImageIO.read( new File("Resources/Images/TurretGun.png"));
           bigEnemyGun = ImageIO.read( new File("Resources/Images/BigEnemyGun.png"));
           tankGun1 = ImageIO.read( new File("Resources/Images/tankGun1.png"));
           tankGun01 = ImageIO.read( new File("Resources/Images/tankGun01.png"));
           tankGun2 = ImageIO.read( new File("Resources/Images/tankGun2.png"));
           tankGun02 = ImageIO.read( new File("Resources/Images/tankGun02.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
