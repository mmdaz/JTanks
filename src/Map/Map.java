package Map;

import battleObject.Bullet;
import battleObject.Drawable;
import battleObject.UserTank;
import battleObject.UserTankGun;
import bufferstrategy.GameState;
import utility.Images;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
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
    public static ArrayList<HardWall> hardWalls;
    public static ArrayList<SoftWall> softWalls ;
    public static ArrayList<Plant> plants ;
    public static ArrayList<Teazel> teazels ;
    public static ArrayList<RepairPackItem> repairPackItems ;
    public static ArrayList<Wicket> wickets ;
    public static int xOffset;
    public static int yOffset;
    public static int intersectedLocx ;
    public static int intersectedLocY ;
    public static int softWallMode = 1 ;


    public Map() {

        mapResource = new int[30][15];
        hardWalls = new ArrayList<HardWall>() ;
        softWalls = new ArrayList<SoftWall>() ;
        repairPackItems = new ArrayList<RepairPackItem>() ;
        plants  = new ArrayList<Plant>() ;
        teazels = new ArrayList<Teazel>() ;
        wickets = new ArrayList<Wicket>() ;


        for (int i = 0; i < 16 ; i++)
            softWalls.add(new SoftWall(0, 0));

        for (int i = 0; i < 5 ; i++) {
            repairPackItems.add(new RepairPackItem(0,0)) ;
        }

        for (int i = 0; i < 2 ; i++) {
            wickets.add( new Wicket(0 , 0 )) ;
        }

        initializeMap();

    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap() {

        hardWalls.clear();
//        softWalls.clear();
        plants.clear();
        teazels.clear();
        int softWallindex = 0 ;
        int repairIndex = 0 ;
        int wicketIndex = 0 ;

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {

//                System.out.printf("(%d , %d)\n" , i * 100 + xOffset , j * 100 + yOffset);

                // avoiding out of range of xOffset and YOffset :

                if (xOffset > 0) xOffset = 0;
                if (yOffset > 0) yOffset = 0;
                if (yOffset + 1500 < 800) yOffset = -700;
                if (xOffset + 3000 < 1600) xOffset = -1400;


                // draw map :

                if (mapResource[i][j] == 2) {
                    HardWall hardWall = new HardWall(i * 100 + xOffset , j * 100 + yOffset) ;
                    hardWalls.add(hardWall);
                    g2d.drawImage(hardWall.getImage(), null, hardWall.getLocX(), hardWall.getLocY());

                }

                else if (mapResource[i][j] == 1) {
                    softWalls.get(softWallindex).setLocX(i * 100 + xOffset);
                    softWalls.get(softWallindex).setLocY(j * 100 + yOffset);


                    if (softWalls.get(softWallindex).getMode() == 1)
                        g2d.drawImage(softWalls.get(softWallindex).getImageMode1(), softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 2 )
                        g2d.drawImage(softWalls.get(softWallindex).getImageMode2() , softWalls.get(softWallindex).getLocX() , softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 3)
                        g2d.drawImage(softWalls.get(softWallindex).getImageMode3() , softWalls.get(softWallindex).getLocX() , softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 4)
                        g2d.drawImage(softWalls.get(softWallindex).getImageMode4() , softWalls.get(softWallindex).getLocX() , softWalls.get(softWallindex).getLocY() , null);
                    else
                        g2d.drawImage(Images.area, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY(), null);

                    softWallindex ++ ;
                }

                else if (mapResource[i][j] == 5) {
                    g2d.drawImage(Images.teazel, null, i * 100 + xOffset, j * 100 + yOffset);
                    teazels.add( new Teazel(i * 100 + xOffset , j * 100 + yOffset ));
                }

                else if (mapResource[i][j] == 4) {
                    g2d.drawImage(Images.plant, null, i * 100 + xOffset, j * 100 + yOffset);
                    plants.add( new Plant( i * 100 + xOffset , j * 100 + yOffset));
                }

                else if (mapResource[i][j] == 6) {
                    repairPackItems.get(repairIndex).setLocX(i * 100 + xOffset) ;
                    repairPackItems.get(repairIndex).setLocY(j * 100 + yOffset) ;



                    if (repairPackItems.get(repairIndex).getStatus())
                        g2d.drawImage(Images.repairItem , null , repairPackItems.get(repairIndex).getLocX() , repairPackItems.get(repairIndex).getLocY()  ) ;
                    else
                        g2d.drawImage(Images.area , null , i * 100 + xOffset , j *100 + yOffset) ;

                    repairIndex ++ ;
                }

                else if (mapResource[i][j] == 7 ) {
                    wickets.get(wicketIndex).setLocX(i * 100 + xOffset);
                    wickets.get(wicketIndex).setLocY(j * 100 + yOffset);

                    if (wickets.get(wicketIndex).getStatus())
                        g2d.drawImage(Images.wicket , null , i * 100 + xOffset , j * 100 + yOffset);
                    else
                        g2d.drawImage(Images.area , null , i * 100 + xOffset , j *100 + yOffset) ;

                    wicketIndex ++ ;

                }

                else {
                    g2d.drawImage(Images.area, null, i * 100 + xOffset, j * 100 + yOffset) ;
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

            if (hardWall.getRectangle2D().intersects(state.locX , state.locY , 80 , 80 ) ) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;
                return false ;
            }

        }

        for (SoftWall softWall : softWalls ) {

/*
            System.out.println(softWall.getLocY() - state.locY);
*/

            if ( softWall.getRectangle2D().intersects(state.locX , state.locY , 80 , 80 ) && softWall.getMode() <= 4) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;

                return false ;
            }
        }



        for (Teazel teazel : teazels) {

            if (teazel.getRectangle2D().intersects(state.locX , state.locY , 80 , 80 ) ) {
                intersectedLocx = state.locX ;
                intersectedLocY = state.locY ;

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

    public static boolean checkBulletCollision (Bullet bullet)  {

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

    /**
     * Check intersect repair object with tank and increase health of tank .
     */
    public  void intersectWithRepairObject() {


        System.out.println(repairPackItems.get(0).getLocX());

        for (RepairPackItem repairPackItem : repairPackItems ) {

            if ( repairPackItem.getRectangle2D().intersects( state.locX , state.locY , 100 , 100 ) && repairPackItem.getStatus()  ) {

                UserTank.health += 500 ;
                repairPackItem.setStatus(false) ;

            }

        }

    }


    public void intersectWithWicket () {

        for (Wicket wicket : wickets) {

            if (wicket.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) && wicket.getStatus()) {

                UserTank.numberOfHeavyBullet = 50 ;
                UserTank.numberOfLightBullet = 200 ;
                wicket.setStatus(false);

            }

        }

    }

    public static void setState(GameState state) {
        Map.state = state;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }
}

