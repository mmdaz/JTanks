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

    private JButton continueButton;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JPanel mainPanel;

    public StartMenu()
    {
        super("JTANKS");
        setSize(979,606);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        mainPanel = new JPanel(null);
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.BLACK);

        continueButton = new JButton("Continue old game:)");
        continueButton.setFont(new Font("Arial",Font.BOLD,25));
        continueButton.setHorizontalAlignment(SwingConstants.LEFT);
        continueButton.setOpaque(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.setForeground(Color.WHITE);
        continueButton.addActionListener(new MyActionListener());
        continueButton.addMouseListener(new MyMouseListener());
        continueButton.addFocusListener(new MyFocusListener());

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

        continueButton.setSize(310,30);
        continueButton.setLocation(10,220);

        easyButton.setSize(240,30);
        easyButton.setLocation(10,270);

        mediumButton.setSize(290,30);
        mediumButton.setLocation(10,320);

        hardButton.setSize(240,30);
        hardButton.setLocation(10,370);

        mainPanel.add(continueButton);
        mainPanel.add(easyButton);
        mainPanel.add(mediumButton);
        mainPanel.add(hardButton);

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
            if (actionEvent.getActionCommand().equals(continueButton.getText())){
                new Start("continue");
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
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        new StartMenu();
        new Images() ;
        new MapEditor() ;
    }



}
