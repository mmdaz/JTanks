package Map;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
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


    public Map(GameState state, Graphics2D g2d) throws IOException {

        mapResource = new int[30][15];
        this.state = state;
        this.g2d = g2d;

        hardWalls = new ArrayList<HardWall>() ;
        softWalls = new ArrayList<SoftWall>() ;
        plants  = new ArrayList<Plant>() ;
        teazels = new ArrayList<Teazel>() ;

        wall = ImageIO.read(new File("Resources/Images/softWall.png"));
        teazel = ImageIO.read(new File("Resources/Images/teazel.png"));
        area = ImageIO.read(new File("Resources/Images/RedEarth.png"));
        plant = ImageIO.read(new File("Resources/Images/plant.png"));
        hardWall = ImageIO.read(new File("Resources/Images/hardWall.png"));

        initializeMap();

    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap() {

        hardWalls.clear();
        softWalls.clear();
        plants.clear();
        teazels.clear();

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
                    g2d.drawImage(wall, null, i * 100 + xOffset, j * 100 + yOffset);
                    hardWalls.add(new HardWall(i * 100 + xOffset , j * 100 + yOffset ));
                }

                else if (mapResource[i][j] == 1) {
                    g2d.drawImage(hardWall, null, i * 100 + xOffset, j * 100 + yOffset) ;
                    softWalls.add( new SoftWall( i*100 + xOffset , j * 100 + yOffset)) ;

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

    public static boolean checkHitWithObjects() {



//        if (mapResource[xIindexForCheck ][yIndexForCheck] != 0) return false;

        System.out.println(hardWalls.size());

        for (HardWall hardWall : hardWalls) {

            if (hardWall.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                return false ;
            }

        }

        for (SoftWall softWall : softWalls ) {

            if (softWall.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                return false ;
            }
        }


        for (HardWall hardWall : hardWalls) {

            if (hardWall.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                return false ;
            }
        }

        for (Teazel teazel : teazels) {

            if (teazel.getRectangle2D().intersects(state.locX , state.locY , 100 , 100 ) ) {
                return false ;
            }

        }

        return true;
    }

}

