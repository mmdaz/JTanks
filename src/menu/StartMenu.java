package menu;
/**
 * This class creates start menu
 * and is used to start the game
 *
 * @author Mohamad Chaman-Motlagh
 */

import Map.MapEditor;
import bufferstrategy.Start;
import utility.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import utility.* ;

public class StartMenu extends JFrame{

    private JButton mapEditor;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JPanel mainPanel;

    private JButton beServer;
    private JButton connectToServer;

    public StartMenu()
    {
        super("JTANKS");
        setSize(979,606);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        mainPanel = new JPanel(null);
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.BLACK);

        easyButton = new JButton("New Easy game");
        easyButton.setFont(new Font("Arial",Font.BOLD,25));
        easyButton.setHorizontalAlignment(SwingConstants.LEFT);
        easyButton.setOpaque(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setBorderPainted(false);
        easyButton.setForeground(Color.WHITE);
        easyButton.addActionListener(new MyActionListener());
        easyButton.addMouseListener(new MyMouseListener());
        easyButton.addFocusListener(new MyFocusListener());

        mediumButton = new JButton("New Medium game");
        mediumButton.setFont(new Font("Arial",Font.BOLD,25));
        mediumButton.setHorizontalAlignment(SwingConstants.LEFT);
        mediumButton.setOpaque(false);
        mediumButton.setContentAreaFilled(false);
        mediumButton.setBorderPainted(false);
        mediumButton.setForeground(Color.WHITE);
        mediumButton.addActionListener(new MyActionListener());
        mediumButton.addMouseListener(new MyMouseListener());
        mediumButton.addFocusListener(new MyFocusListener());

        hardButton = new JButton("New hard game");
        hardButton.setFont(new Font("Arial",Font.BOLD,25));
        hardButton.setHorizontalAlignment(SwingConstants.LEFT);
        hardButton.setOpaque(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setBorderPainted(false);
        hardButton.setForeground(Color.WHITE);
        hardButton.addActionListener(new MyActionListener());
        hardButton.addMouseListener(new MyMouseListener());
        hardButton.addFocusListener(new MyFocusListener());

        mapEditor = new JButton("Map editor");
        mapEditor.setFont(new Font("Arial",Font.BOLD,25));
        mapEditor.setHorizontalAlignment(SwingConstants.LEFT);
        mapEditor.setOpaque(false);
        mapEditor.setContentAreaFilled(false);
        mapEditor.setBorderPainted(false);
        mapEditor.setForeground(Color.WHITE);
        mapEditor.addActionListener(new MyActionListener());
        mapEditor.addMouseListener(new MyMouseListener());
        mapEditor.addFocusListener(new MyFocusListener());


        beServer = new JButton("Start 2 Player Game (As a server)");
        beServer.setFont(new Font("Arial",Font.BOLD,20));
        beServer.setHorizontalAlignment(SwingConstants.LEFT);
        beServer.setOpaque(false);
        beServer.setContentAreaFilled(false);
        beServer.setBorderPainted(false);
        beServer.setForeground(Color.WHITE);
        beServer.addActionListener(new MyActionListener());
        beServer.addMouseListener(new MyMouseListener());
        beServer.addFocusListener(new MyFocusListener());

        connectToServer = new JButton("Start 2 Player Game (Connect to a server)");
        connectToServer.setFont(new Font("Arial",Font.BOLD,20));
        connectToServer.setHorizontalAlignment(SwingConstants.LEFT);
        connectToServer.setOpaque(false);
        connectToServer.setContentAreaFilled(false);
        connectToServer.setBorderPainted(false);
        connectToServer.setForeground(Color.WHITE);
        connectToServer.addActionListener(new MyActionListener());
        connectToServer.addMouseListener(new MyMouseListener());
        connectToServer.addFocusListener(new MyFocusListener());



        easyButton.setSize(240,30);
        easyButton.setLocation(10,220);

        mediumButton.setSize(290,30);
        mediumButton.setLocation(10,270);

        hardButton.setSize(240,30);
        hardButton.setLocation(10,320);

        mapEditor.setSize(240,30);
        mapEditor.setLocation(10,370);

        beServer.setSize(400,30);
        beServer.setLocation(10,420);

        connectToServer.setSize(500,30);
        connectToServer.setLocation(10,470);

        mainPanel.add(mapEditor);
        mainPanel.add(easyButton);
        mainPanel.add(mediumButton);
        mainPanel.add(hardButton);
        mainPanel.add(beServer);
        mainPanel.add(connectToServer);

        JLabel jLabel = new JLabel(new ImageIcon(String.valueOf(new File("Resources/Images/Startup.png"))));
        jLabel.setSize(979,606);
        //jLabel.setLocation(0,0);
        mainPanel.add(jLabel);



        setVisible(true);

    }

    private class MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getActionCommand().equals(mapEditor.getText())){
                new MapEditor();
                dispose();
            }
            else if (actionEvent.getActionCommand().equals(easyButton.getText())){
                new Start("easy");
                dispose();
            }

            else if (actionEvent.getActionCommand().equals(mediumButton.getText())){
                new Start("medium");
                dispose();
            }

            else if (actionEvent.getActionCommand().equals(hardButton.getText())){
                new Start("hard");
                dispose();
            } else if(actionEvent.getActionCommand().equals(beServer.getText())){
                new Start("server");
                dispose();
            }else if(actionEvent.getActionCommand().equals(connectToServer.getText())){
                new Start("client");
                dispose();
            }
        }
    }

    public class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) { }

        @Override
        public void mousePressed(MouseEvent mouseEvent) { }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) { }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            ((JButton)mouseEvent.getSource()).setForeground(Color.decode("#FF9B28"));
            repaint();
            revalidate();
            new SoundPlayer("Resources/Sounds/select.wav").run();
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            ((JButton)mouseEvent.getSource()).setForeground(Color.WHITE);
            repaint();
            revalidate();
        }
    }

    public class MyFocusListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent focusEvent) {
            ((JButton)focusEvent.getSource()).setForeground(Color.decode("#FF9B28"));
            repaint();
            revalidate();

            new SoundPlayer("Resources/Sounds/select.wav").run();
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            ((JButton)focusEvent.getSource()).setForeground(Color.WHITE);
            repaint();
            revalidate();
        }
    }

    /**
     * Start of programme
     * @param args given start values
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new StartMenu();
        new Images() ;
//        new MapEditor() ;
    }



}
