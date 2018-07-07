package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class create and design map of the game .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 */

public class Map {

    private int[][] mapResource ;
    private GameState state ;
    private Graphics2D g2d ;
    private BufferedImage wall ;
    private BufferedImage teazel ;
    public static int preLocX ;
    public static int preLocY ;


    public Map ( GameState state , Graphics2D g2d ) throws IOException {

        mapResource = new int[50][50] ;
        this.state = state ;
        this.g2d = g2d ;
        wall = ImageIO.read(new File("Resources/Images/softWall.png")) ;
        teazel = ImageIO.read( new File("Resources/Images/teazel.png")) ;

        mapResource[0][0] = 1 ;
        mapResource[1][0] = -1 ;
        mapResource [5][5] = 1 ;
        for (int i = 5 ; i < 20 ; i ++ ) {
            mapResource[7][i] = 1 ;
        }

    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap () {



        int startY = Math.max(0 , state.locY/100 -4 ) ;
        int finishY = Math.min(50 ,  state.locY/100 + 4 ) ;
        int startX = Math.max(0 , state.locX/100 - 6 ) ;
        int finishX = Math.min(50 , state.locX /100 + 6) ;


        for (int i = startY; i < finishY ; i ++ ) {
            for (int j = startX ; j < finishX ; j ++ ) {

                if (mapResource[i][j] == 1) {
                    g2d.drawImage(wall , null , i*100 , j * 100) ;
                }
                else if (mapResource[i][j] == -1 ) {
                    g2d.drawImage(teazel , null , i*100 , j * 100 );

                }

            }

        }

    }
}
