package Map;

import utility.Images;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapLevel2 extends Map {



    public MapLevel2 () {
        super();
//        mapResource = new int[15][30] ;

    }


    @Override

    protected void initializeMap() {

        mapResource = new int[15][30] ;

        try {
            File map = new File("Resources/Save/Map2.txt");
            FileReader fileReader = new FileReader(map);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int j = 0;

            while ((line = bufferedReader.readLine()) != null) {

//                System.out.println(line.length());

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

    @Override

    public void paintMap ( Graphics2D g2d ) {

        hardWalls.clear();
//        softWalls.clear();
        plants.clear();
        teazels.clear();
        int softWallindex = 0 ;
        int repairIndex = 0 ;
        int cannonIndex = 0 ;
        int machineGunIndex = 0 ;
        int upgraderIndex = 0 ;

        for (int i = 0; i < 15 ; i++) {
            for (int j = 0; j < 30 ; j++) {

//                System.out.printf("(%d , %d)\n" , i * 100 + xOffset , j * 100 + yOffset);

                // avoiding out of range of xOffset and YOffset :

                if (xOffset > 0) xOffset = 0;
                if (yOffset > 0) yOffset = 0;
                if (yOffset + 3000 < 800) yOffset = -2200;
                if (xOffset + 1500 < 1400) xOffset = -100;

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


}
