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


    public Images () {
        try {
            softWall1 = ImageIO.read(new File("Resources/Images/softWall.png")) ;
            softWall2 = ImageIO.read(new File("Resources/Images/softWall1.png")) ;
            softWall3 = ImageIO.read(new File("Resources/Images/softWall2.png")) ;
            softWall4 = ImageIO.read(new File("Resources/Images/softWall3.png")) ;
            hardWall = ImageIO.read(new File("Resources/Images/hardWall.png")) ;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
