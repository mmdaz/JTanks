package Map;

import battleObject.Bullet;
import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class create and design map of the game .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 */

public class Map {

    public static int[][] mapResource;
    public static GameState state;
    private Graphics2D g2d;
    private BufferedImage wall;
    private BufferedImage teazel;
    private BufferedImage area;
    private BufferedImage plant;
    private BufferedImage hardWall;
    public static ArrayList<HardWall> hardWalls;
    public static ArrayList<SoftWall> softWalls ;
    public static ArrayList<Plant> plants ;
    public static ArrayList<Teazel> teazels ;
    public static int xOffset;
    public static int yOffset;
    public static int intersectedLocx ;
    public static int intersectedLocY ;
    public static int softWallMode = 1 ;


    public Map() throws IOException {

        mapResource = new int[30][15];

        hardWalls = new ArrayList<HardWall>() ;
        softWalls = new ArrayList<SoftWall>() ;

        for (int i = 0; i < 40 ; i++)
            softWalls.add(new SoftWall(0, 0));

        plants  = new ArrayList<Plant>() ;
        teazels = new ArrayList<Teazel>() ;

        teazel = ImageIO.read(new File("Resources/Images/teazel.png"));
        area = ImageIO.read(new File("Resources/Images/RedEarth.png"));
        plant = ImageIO.read(new File("Resources/Images/plant.png"));

        initializeMap();

        System.out.println(softWalls.size());



    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap() {

        hardWalls.clear();
//        softWalls.clear();
        plants.clear();
        teazels.clear();
        int index = 0 ;

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {

//                System.out.printf("(%d , %d)\n" , i * 100 + xOffset , j * 100 + yOffset);

                // avoiding out of range of xOffset and YOffset :

                if (xOffset > 0) xOffset = 0;
                if (yOffset > 0) yOffset = 0;
                if (yOffset + 1500 < 600) yOffset = -900;
                if (xOffset + 3000 < 1200) xOffset = -1800;


                // draw map :

                if (mapResource[i][j] == 2) {
                    HardWall hardWall = new HardWall(i * 100 + xOffset , j * 100 + yOffset) ;
                    hardWalls.add(hardWall);
                    g2d.drawImage(hardWall.getImage(), null, hardWall.getLocX(), hardWall.getLocY());

                }

                else if (mapResource[i][j] == 1) {
                    softWalls.get(index).setLocX(i * 100 + xOffset);
                    softWalls.get(index).setLocY(j * 100 + yOffset);


                    if (softWalls.get(index).getMode() == 1)
                        g2d.drawImage(softWalls.get(index).getImageMode1(), softWalls.get(index).getLocX(), softWalls.get(index).getLocY() , null);
                    else if (softWalls.get(index).getMode() == 2 )
                        g2d.drawImage(softWalls.get(index).getImageMode2() , softWalls.get(index).getLocX() , softWalls.get(index).getLocY() , null);
                    else if (softWalls.get(index).getMode() == 3)
                        g2d.drawImage(softWalls.get(index).getImageMode3() , softWalls.get(index).getLocX() , softWalls.get(index).getLocY() , null);
                    else if (softWalls.get(index).getMode() == 4)
                        g2d.drawImage(softWalls.get(index).getImageMode4() , softWalls.get(index).getLocX() , softWalls.get(index).getLocY() , null);
                    else
                        g2d.drawImage(area, softWalls.get(index).getLocX(), softWalls.get(index).getLocY(), null);

                    index ++ ;

                }

                else if (mapResource[i][j] == 5) {
                    g2d.drawImage(teazel, null, i * 100 + xOffset, j * 100 + yOffset);
                    teazels.add( new Teazel(i * 100 + xOffset , j * 100 + yOffset ));
                }

                else if (mapResource[i][j] == 4) {
                    g2d.drawImage(plant, null, i * 100 + xOffset, j * 100 + yOffset);
                    plants.add( new Plant( i * 100 + xOffset , j * 100 + yOffset));
                }
                // enemy tank ...
                else {
                    g2d.drawImage(area, null, i * 100 + xOffset, j * 100 + yOffset);
                }
            }

        }




    }

    /**
     * The Method that read map data from file and initialize mapResource array .
     *
     */
    private void initializeMap() {

        try {
            File map = new File("Resources/Save/Map.txt");
            FileReader fileReader = new FileReader(map);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int j = 0;

            while ((line = bufferedReader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {
//                    System.out.printf("(%d , %d) \n" , i , j);
                    mapResource[i][j] = Character.getNumericValue(line.charAt(i)) ;
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     * The Method that check if tank hit with map objects .
     *
     * @return if tank hits return false and if does not hit return true .
     */
    public static boolean checkHitWithObjects() {



//        System.out.println(hardWalls.size());

        for (HardWall hardWall : hardWalls) {

            if (hardWall.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;
//                state.locX = state.preLocX ;
//                state.locY = state.preLocY ;
                return false ;
            }

        }

        for (SoftWall softWall : softWalls ) {

/*
            System.out.println(softWall.getLocY() - state.locY);
*/

            if ( softWall.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) && softWall.getMode() <= 4) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;
//                state.locX = state.preLocX ;
//                state.locY = state.preLocY ;

                return false ;
            }
        }



        for (Teazel teazel : teazels) {

            if (teazel.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;
//                state.locX = state.preLocX ;
//                state.locY = state.preLocY ;
                return false ;
            }

        }

        return true ;

    }


    /**
     * The Method that check Bullet collision .
     *
     * @return {@code true} in case of collision, {@code false} otherwise
     */

    public static boolean checkBulletCollision (Bullet bullet )  {

        Rectangle2D bulletRect = new Rectangle( bullet.getX() , bullet.getY() , 23 , 9 ) ;

        for (SoftWall softWall : softWalls) {

            if (bulletRect.intersects(softWall.getLocX() , softWall.getLocY() , 100 , 100 ) && softWall.getMode() <= 4) {
                softWall.setMode(softWall.getMode() + 1);
                softWallMode ++ ;
                return true ;
            }
        }

        for (HardWall hardWall : hardWalls ) {
            if (bulletRect.intersects(hardWall.getLocX() , hardWall.getLocY() , 100 , 100)) return true ;
        }

        for (Teazel teazel : teazels ) {
            if (bulletRect.intersects(teazel.getLocX() , teazel.getLocY() , 100 , 100 )) return true ;
        }

        return false ;

    }


    public static void setState(GameState state) {
        Map.state = state;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }
}

