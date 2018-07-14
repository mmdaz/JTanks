package Map;

import battleObject.*;
import bufferstrategy.GameFrame;
import bufferstrategy.GameState;
import utility.Images;

import utility.SoundPlayer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;

/**
 * This class create and design map of the game .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 */

public class Map implements Serializable{

    private int[][] mapResource;
    public static GameState state;
    public static ArrayList<HardWall> hardWalls;
    public static ArrayList<SoftWall> softWalls ;
    public static ArrayList<Plant> plants ;
    public static ArrayList<Teazel> teazels ;
    public static ArrayList<RepairPackItem> repairPackItems ;
    public static ArrayList<CannonShell> cannonShells;
    public static ArrayList<MachineGunShell> machineGunShells;
    public static ArrayList<UpgradeWeapon> upgradeWeapons;
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
        cannonShells = new ArrayList<CannonShell>() ;
        machineGunShells = new ArrayList<MachineGunShell>();
        upgradeWeapons = new ArrayList<UpgradeWeapon>();



        for (int i = 0; i < 16 ; i++)
            softWalls.add(new SoftWall(0, 0));

        for (int i = 0; i < 5 ; i++) {
            repairPackItems.add(new RepairPackItem(0,0)) ;
        }

        for (int i = 0; i < 2 ; i++) {
            cannonShells.add( new CannonShell(0 , 0 )) ;
        }
        for (int i = 0; i < 2 ; i++) {
            machineGunShells.add( new MachineGunShell(0 , 0 )) ;
        }
        for (int i = 0; i < 2 ; i++) {
            upgradeWeapons.add( new UpgradeWeapon(0 , 0 )) ;
        }
        initializeMap();

    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap(Graphics2D g2d) {


        hardWalls.clear();
//        softWalls.clear();
        plants.clear();
        teazels.clear();
        int softWallindex = 0 ;
        int repairIndex = 0 ;
        int cannonIndex = 0 ;
        int machineGunIndex = 0;
        int upgraderIndex = 0;

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
                        g2d.drawImage(Images.softWall1, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 2 )
                        g2d.drawImage(Images.softWall2, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 3)
                        g2d.drawImage(Images.softWall3, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    else if (softWalls.get(softWallindex).getMode() == 4)
                        g2d.drawImage(Images.softWall4, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    else {
                        if(machineGunIndex <= 1) {
                            machineGunShells.get(machineGunIndex).setLocX(i * 100 + xOffset);
                            machineGunShells.get(machineGunIndex).setLocY(j * 100 + yOffset);

                            if (machineGunShells.get(machineGunIndex).getStatus())
                                g2d.drawImage(Images.machineGunShell, null, i * 100 + xOffset, j * 100 + yOffset);
                            else
                                g2d.drawImage(Images.area, null, i * 100 + xOffset, j * 100 + yOffset);

                            machineGunIndex++;
                        } else
                            g2d.drawImage(Images.area, softWalls.get(softWallindex).getLocX(), softWalls.get(softWallindex).getLocY() , null);
                    }
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
                    cannonShells.get(cannonIndex).setLocX(i * 100 + xOffset);
                    cannonShells.get(cannonIndex).setLocY(j * 100 + yOffset);

                    if (cannonShells.get(cannonIndex).getStatus())
                        g2d.drawImage(Images.cannonShell, null , i * 100 + xOffset , j * 100 + yOffset);
                    else
                        g2d.drawImage(Images.area , null , i * 100 + xOffset , j *100 + yOffset) ;

                    cannonIndex++ ;

                }  else if (mapResource[i][j] == 8 ) {
                    machineGunShells.get(machineGunIndex).setLocX(i * 100 + xOffset);
                    machineGunShells.get(machineGunIndex).setLocY(j * 100 + yOffset);

                    if (machineGunShells.get(machineGunIndex).getStatus())
                        g2d.drawImage(Images.machineGunShell, null , i * 100 + xOffset , j * 100 + yOffset);
                    else
                        g2d.drawImage(Images.area , null , i * 100 + xOffset , j *100 + yOffset) ;

                    machineGunIndex ++;

                }
                else if (mapResource[i][j] == 9) {
                    upgradeWeapons.get(upgraderIndex).setLocX(i * 100 + xOffset) ;
                    upgradeWeapons.get(upgraderIndex).setLocY(j * 100 + yOffset) ;



                    if (upgradeWeapons.get(upgraderIndex).getStatus())
                        g2d.drawImage(Images.upgradeWeapon , null , i * 100 + xOffset , j * 100 + yOffset ) ;
                    else
                        g2d.drawImage(Images.area , null , i * 100 + xOffset , j *100 + yOffset) ;

                    upgraderIndex ++ ;
                }

                else {
                    g2d.drawImage(Images.area, null, i * 100 + xOffset, j * 100 + yOffset) ;
                }
            }

        }


        g2d.drawImage(Images.endFlag,null, 850 + Map.xOffset,1300 + Map.yOffset);

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


        for (RepairPackItem repairPackItem : repairPackItems ) {

            if ( repairPackItem.getRectangle2D().intersects( state.locX , state.locY , 100 , 100 ) && repairPackItem.getStatus()  ) {

                if(UserTank.health < 500)
                    UserTank.health += 500 ;
                else
                    UserTank.health = 1000;
                repairPackItem.setStatus(false) ;

                new SoundPlayer("Resources/Sounds/repair.wav").run();

            }

        }

    }

    /**
     * The method that handle intersect with thank and cannon shell .
     */
    public void intersectWithCannonShell() {

        for (CannonShell cannonShell : cannonShells) {

            if (cannonShell.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) && cannonShell.getStatus()) {

                UserTank.numberOfHeavyBullet += 10 ;
                cannonShell.setStatus(false);

            }

        }

    }

    /**
     * THe method that handled intersect with tank and machine gun shell .
     */

    public void intersectWithMachineGunShell() {

        for (MachineGunShell machineGunShell : machineGunShells) {

            if (machineGunShell.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) && machineGunShell.getStatus()) {

                UserTank.numberOfLightBullet += 30 ;
                machineGunShell.setStatus(false);

            }

        }

    }

    /**
     * The method that handled intersect tank and upgrader .
     */

    public void intersectWithUpgrader() {

        for (UpgradeWeapon upgradeWeapon : upgradeWeapons) {

            if (upgradeWeapon.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) && upgradeWeapon.getStatus()) {

                UserTank.getCurrentGun().upgradeWeapon();
                upgradeWeapon.setStatus(false);

            }

        }

    }



    public static boolean collisionUserTankAndEnemy () {

        for (Drawable drawable : GameFrame.drawables) {

            if ( drawable instanceof EnemyTank || drawable instanceof Turret) {

                if ( drawable.getRect().intersects( state.locX , state.locY , 100 , 100  ) ) {

                    intersectedLocx = state.locX ;
                    intersectedLocY = state.locY ;
                    return false ;

                }
            }

        }

        return true ;

    }

    public static void setState(GameState state) {
        Map.state = state;
    }

    public static ArrayList<HardWall> getHardWalls() {
        return hardWalls;
    }

    public static void setHardWalls(ArrayList<HardWall> hardWalls) {
        Map.hardWalls = hardWalls;
    }

    public static ArrayList<SoftWall> getSoftWalls() {
        return softWalls;
    }

    public static void setSoftWalls(ArrayList<SoftWall> softWalls) {
        Map.softWalls = softWalls;
    }

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public static void setPlants(ArrayList<Plant> plants) {
        Map.plants = plants;
    }

    public static ArrayList<Teazel> getTeazels() {
        return teazels;
    }

    public static void setTeazels(ArrayList<Teazel> teazels) {
        Map.teazels = teazels;
    }

    public static ArrayList<RepairPackItem> getRepairPackItems() {
        return repairPackItems;
    }

    public static void setRepairPackItems(ArrayList<RepairPackItem> repairPackItems) {
        Map.repairPackItems = repairPackItems;
    }

    public static ArrayList<CannonShell> getCannonShells() {
        return cannonShells;
    }

    public static void setCannonShells(ArrayList<CannonShell> cannonShells) {
        Map.cannonShells = cannonShells;
    }

    public static ArrayList<MachineGunShell> getMachineGunShells() {
        return machineGunShells;
    }

    public static void setMachineGunShells(ArrayList<MachineGunShell> machineGunShells) {
        Map.machineGunShells = machineGunShells;
    }

    public static ArrayList<UpgradeWeapon> getUpgradeWeapons() {
        return upgradeWeapons;
    }

    public static void setUpgradeWeapons(ArrayList<UpgradeWeapon> upgradeWeapons) {
        Map.upgradeWeapons = upgradeWeapons;
    }
}

