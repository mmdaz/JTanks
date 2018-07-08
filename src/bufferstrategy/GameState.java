/*** In The Name of Allah ***/
package bufferstrategy;

import battleObject.Bullet;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

	public int locX, locY, diam;
	public boolean gameOver;
    public int mouseX, mouseY;
    public double angle;
    public double tankAngle;
	public int tankCenterX;
	public int tankCenterY;

	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
	private boolean mousePress;
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;

	public boolean rightKeyPressed;



	private ArrayList<Bullet> heavybullet‌‌s;
	private ArrayList<Bullet> lightBullet;

	public GameState() {
		locX = 100;
		locY = 500;
		diam = 40;
		gameOver = false;
		//
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		//
		mousePress = false;
		mouseX = 0;
		mouseY = 0;
		//
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
		//
		tankAngle = 0;
		angle = 1;
		//
		heavybullet‌‌s = new ArrayList<Bullet>();
		lightBullet = new ArrayList<Bullet>();
	}

	private boolean almostEqual(double num1 ,double num2) {
		if(Math.abs(num1 - num2) <= 0.05)
			return true;
		if(Math.abs(num2 - num1) <= 0.05)
			return true;
		return false;
	}

	/**
	 * The method which updates the game state.
	 */
	public void update() {

		if(keyUP && keyRIGHT && tankAngle > -Math.PI/4) {
			tankAngle -= 0.05;
			if(almostEqual(tankAngle, -Math.PI / 4))
				tankAngle = -Math.PI/4;
			locX += 1;
			locY -= 1;
		} else if(keyUP && keyRIGHT && tankAngle < -Math.PI/4) {
			tankAngle += 0.05;
			if(almostEqual(tankAngle, -Math.PI / 4))
				tankAngle = -Math.PI/4;
			locX += 1;
			locY -= 1;
		}

		else if(keyDOWN && keyLEFT && tankAngle < 3 * Math.PI / 4 && tankAngle >= 0) {
			tankAngle += 0.05;
			if (almostEqual(tankAngle, 3 * Math.PI / 4))
				tankAngle = 3 * Math.PI / 4;
			locX -= 1;
			locY += 1;
		} else if(keyDOWN && keyLEFT && tankAngle > -5 * Math.PI / 4 && tankAngle < 0) {
			tankAngle -= 0.05;
			if (almostEqual(tankAngle, -5 * Math.PI / 4))
				tankAngle = -5 * Math.PI / 4;
			locX -= 1;
			locY += 1;
		}

		else if (keyDOWN && keyRIGHT && tankAngle <  Math.PI / 4) {
			tankAngle += 0.05;
			if(almostEqual(tankAngle, Math.PI/4))
				tankAngle = Math.PI/4;
			locX += 1;
			locY += 1;
		} else if (keyDOWN && keyRIGHT && tankAngle >  Math.PI / 4 ) {
			tankAngle -= 0.05;
			if(almostEqual(tankAngle, Math.PI/4))
				tankAngle = Math.PI/4;
			locX += 1;
			locY += 1;
		}


		else if (keyUP && keyLEFT && tankAngle > -3 * Math.PI / 4 && tankAngle <= 0) {
			tankAngle -= 0.05;
			if(almostEqual(-3 * Math.PI/4,tankAngle))
				tankAngle = -3 * Math.PI / 4;
			locX -= 1;
			locY -= 1;
		} else if (keyUP && keyLEFT && tankAngle < 5 * Math.PI / 4 && tankAngle > 0) {
			tankAngle += 0.05;
			if(almostEqual( 5 * Math.PI/4,tankAngle))
				tankAngle = 5 * Math.PI / 4;
			locX -= 1;
			locY -= 1;
		}

		else if (keyRIGHT && tankAngle < 0 && !keyUP && !keyDOWN) {
			tankAngle += 0.05;
			if(almostEqual(0,tankAngle))
				tankAngle = 0;
			locX += 1;
		} else if (keyRIGHT && tankAngle > 0 && !keyUP && !keyDOWN) {
			tankAngle -= 0.05;
			if(almostEqual(0,tankAngle))
				tankAngle = 0;
			locX += 1;
		}

		else if (keyLEFT && tankAngle < Math.PI && !keyUP && !keyDOWN) {
			tankAngle += 0.05;
			if(almostEqual(Math.PI,tankAngle))
				tankAngle = Math.PI;
			locX -= 1;
		} else if (keyLEFT && tankAngle > Math.PI && !keyUP && !keyDOWN) {
			tankAngle -= 0.05;
			if(almostEqual(Math.PI,tankAngle))
				tankAngle = Math.PI;
			locX -= 1;
		}

		else if (keyUP && (tankAngle > -Math.PI/2) && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if(almostEqual(-Math.PI/2,tankAngle))
				tankAngle = -Math.PI/2;
			locY -= 1;
		}else if (keyUP && (tankAngle < -Math.PI/2) && !keyLEFT && !keyRIGHT) {
			tankAngle += 0.05;
			if(almostEqual(-Math.PI/2,tankAngle))
				tankAngle = -Math.PI/2;
			locY -= 1;
		} else if (keyUP && (tankAngle != 3 * Math.PI/2 && tankAngle != -Math.PI/2) && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if(almostEqual(-Math.PI/2,tankAngle) || almostEqual(tankAngle , 3 * Math.PI/2))
				tankAngle = -Math.PI/2;
			locY -= 1;
		}

		else if (keyDOWN && tankAngle < Math.PI/2 && !keyLEFT && !keyRIGHT) {
			tankAngle += 0.05;
			if(almostEqual(Math.PI/2,tankAngle))
				tankAngle = Math.PI/2;
			locY += 1;
		} else if (keyDOWN && tankAngle != Math.PI/2 && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if(almostEqual(Math.PI/2,tankAngle))
				tankAngle = Math.PI/2;
			locY += 1;
		}


		else {
			if (keyUP) {
				locY -= 8;
			}
			if (keyDOWN) {
				locY += 8;
			}
			if (keyLEFT) {
				locX -= 8;
			}
			if (keyRIGHT) {
				locX += 8;
			}
		}


		if(tankAngle >= 2 * Math.PI)
			tankAngle -= 2 * Math.PI;
		else if (tankAngle <= -2 *  Math.PI)
			tankAngle += 2 * Math.PI;

		locX = Math.max(locX, 0);
		locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
		locY = Math.max(locY, 0);
		locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);

		tankCenterX = locX + 50;
		tankCenterY = locY + 50;

		angle = Math.atan2( mouseY - (locY + 50) , mouseX - (locX + 50) );

	}


	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}



	/**
	 * The keyboard handler.
	 */
	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
			switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					keyUP = true;
					break;
				case KeyEvent.VK_S:
					keyDOWN = true;
					break;
				case KeyEvent.VK_A:
					keyLEFT = true;
					break;
				case KeyEvent.VK_D:
					keyRIGHT = true;
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
			}
			switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					keyUP = false;
					break;
				case KeyEvent.VK_S:
					keyDOWN = false;
					break;
				case KeyEvent.VK_A:
					keyLEFT = false;
					break;
				case KeyEvent.VK_D:
					keyRIGHT = false;
					break;
			}
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent mouseEvent){
            mouseX = mouseEvent.getX() + diam / 2;
            mouseY = mouseEvent.getY() + diam / 2 ;
        }

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
			if(mouseEvent.getButton() == MouseEvent.BUTTON1){
				try {
					lightBullet.add(new Bullet(mouseX,mouseY,locX,locY, ImageIO.read(new File("Resources/Images/LightBullet.png"))));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			if(mouseEvent.getButton() == MouseEvent.BUTTON1){
				rightKeyPressed = true;
				try {
					heavybullet‌‌s.add(new Bullet(mouseX,mouseY,locX,locY, ImageIO.read(new File("Resources/Images/HeavyBullet.png"))));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			if(mouseEvent.getButton() == MouseEvent.BUTTON1){
				rightKeyPressed = false;
			}
		}
	}

	public ArrayList<Bullet> getHeavybullet‌‌s() {
		return heavybullet‌‌s;
	}

	public ArrayList<Bullet> getLightBullet() {
		return lightBullet;
	}
}
