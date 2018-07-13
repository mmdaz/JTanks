package Map;





import bufferstrategy.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 *This class is use to create a new map and play in it
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 */
public class MapEditor extends JFrame {


    private JButton save ;
    private JButton cancel ;
    int [][] mapResource ;


    public MapEditor () {

        setSize(1500 , 800);
        JPanel contemtPain = new JPanel( new BorderLayout()) ;
        JPanel buttonsPanel = new JPanel( new BorderLayout()) ;
        JPanel mapPanel = new JPanel(new GridLayout(30 ,15 , 2 , 2)) ;


        mapResource = new int[15][30] ;

        save = new JButton("Save Map & start a new game with it") ;
        cancel = new JButton("Cancel") ;
        buttonsPanel.add(save , BorderLayout.WEST) ;
        buttonsPanel.add(cancel , BorderLayout.EAST) ;


        String[] mapObjects = {"Area" , "Soft Wall" , "Hard Wall" , "Plant" , "Teazel" , "Cannon Shell" , "Machine Gun Shell" , "Upgrade Weapons" } ;

        JComboBox [][] comboBoxes = new JComboBox[15][30] ;



        for (int i = 0; i < 15 ; i++) {

            for (int j = 0; j < 30; j++) {

                JComboBox cm = new JComboBox(mapObjects);
                cm.setPreferredSize(new Dimension(20, 20));
                comboBoxes[i][j] = cm;
                mapPanel.add(cm);

            }
        }

            contemtPain.add(mapPanel , BorderLayout.CENTER) ;
            contemtPain.add(buttonsPanel , BorderLayout.SOUTH) ;

            add(contemtPain) ;

//            add(scrollPane) ;
            setVisible(true) ;


            // action listener :

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < 15 ; i++) {

                    for (int j = 0; j < 30 ; j++) {

                        if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Area"))
                            mapResource[i][j] = 0 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Soft Wall"))
                            mapResource[i][j] = 2 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Hard Wall"))
                            mapResource[i][j] = 1 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Plant"))
                            mapResource[i][j] = 4 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Teazel"))
                            mapResource[i][j] = 5 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Cannon Shell"))
                            mapResource[i][j] = 6 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Machine Gun Shell"))
                            mapResource[i][j] = 7 ;
                        else if (comboBoxes[i][j].getItemAt(comboBoxes[i][j].getSelectedIndex()).equals("Upgrade Weapons"))
                            mapResource[i][j] = 8 ;


                    }



                }


                try {

                    File map = new File("Resources/Save/Map.txt");
                    FileWriter fileWriter = new FileWriter(map) ;
                    BufferedWriter writer = new BufferedWriter(fileWriter) ;

                    for (int i = 0; i < 15 ; i++) {

                        for (int j = 0; j < 30 ; j++) {

                            String temp = new String(String.valueOf(mapResource[i][j])) ;
                            writer.write(temp);

                        }
                        writer.write("\n");
                        System.out.print("\n");

                    }


                    writer.close();
                    fileWriter.close();

                    new Start("easy");



                }catch (IOException ex) {
                    ex.printStackTrace();
                }


                dispose();
            }
        });

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

    }
}

