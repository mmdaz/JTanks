/*** In The Name of Allah ***/
package bufferstrategy;
import Map.Map;
import battleObject.UserTank;
import Map.MapLevel2;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;


/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState implements Serializable {

	public int locX, locY, diam , preLocX , preLocY;
	public boolean gameOver;
	public boolean level1Won;
	public int mouseX, mouseY;
	public double angle;
	public double tankAngle;
	public int tankCenterX;
	public int tankCenterY;

	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;

	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;


	public GameState() {
		locX = 00;
		locY = 110;
		diam = 40;
		gameOver = false;
		//
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		//
		mouseX = 0;
		mouseY = 0;
		//
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
		//
		tankAngle = 0;
		angle = 1;
	}

	private boolean almostEqual(double num1, double num2) {
		if (Math.abs(num1 - num2) <= 0.05)
			return true;
		if (Math.abs(num2 - num1) <= 0.05)
			return true;
		return false;
	}

	/**
	 * The method which updates the game state.
	 */
	public void update() {

		if (keyUP && keyRIGHT && tankAngle > -Math.PI / 4) {
			tankAngle -= 0.05;
			if (almostEqual(tankAngle, -Math.PI / 4))
				tankAngle = -Math.PI / 4;
//			locX += 1;
//			locY -= 1;
		} else if (keyUP && keyRIGHT && tankAngle < -Math.PI / 4) {
			tankAngle += 0.05;
			if (almostEqual(tankAngle, -Math.PI / 4))
				tankAngle = -Math.PI / 4;
//			locX += 1;
//			locY -= 1;
		} else if (keyDOWN && keyLEFT && tankAngle != 3 * Math.PI / 4 && tankAngle >= 0) {
			tankAngle += 0.05;
			if (almostEqual(tankAngle, 3 * Math.PI / 4))
				tankAngle = 3 * Math.PI / 4;
//			locX -= 1;
//			locY += 1;
		} else if (keyDOWN && keyLEFT && tankAngle > -5 * Math.PI / 4 && tankAngle < 0) {
			tankAngle -= 0.05;
			if (almostEqual(tankAngle, -5 * Math.PI / 4))
				tankAngle = -5 * Math.PI / 4;
//			locX -= 1;
//			locY += 1;
		} else if (keyDOWN && keyRIGHT && tankAngle < Math.PI / 4) {
			tankAngle += 0.05;
			if (almostEqual(tankAngle, Math.PI / 4))
				tankAngle = Math.PI / 4;
//			locX += 1;
//			locY += 1;
		} else if (keyDOWN && keyRIGHT && tankAngle > Math.PI / 4) {
			tankAngle -= 0.05;
			if (almostEqual(tankAngle, Math.PI / 4))
				tankAngle = Math.PI / 4;
//			locX += 1;
//			locY += 1;
		} else if (keyUP && keyLEFT && tankAngle > -3 * Math.PI / 4 && tankAngle <= 0) {
			tankAngle -= 0.05;
			if (almostEqual(-3 * Math.PI / 4, tankAngle))
				tankAngle = -3 * Math.PI / 4;
//			locX -= 1;
//			locY -= 1;
		} else if (keyUP && keyLEFT && tankAngle < 5 * Math.PI / 4 && tankAngle > 0) {
			tankAngle += 0.05;
			if (almostEqual(5 * Math.PI / 4, tankAngle))
				tankAngle = 5 * Math.PI / 4;
//			locX -= 1;
//			locY -= 1;
		} else if (keyRIGHT && tankAngle < 0 && !keyUP && !keyDOWN) {
			tankAngle += 0.05;
			if (almostEqual(0, tankAngle))
				tankAngle = 0;
//			locX += 1;
		} else if (keyRIGHT && tankAngle > 0 && !keyUP && !keyDOWN) {
			tankAngle -= 0.05;
			if (almostEqual(0, tankAngle))
				tankAngle = 0;
//			locX += 1;
		} else if (keyLEFT && tankAngle < Math.PI && !keyUP && !keyDOWN) {
			tankAngle += 0.05;
			if (almostEqual(Math.PI, tankAngle))
				tankAngle = Math.PI;
//			locX -= 1;
		} else if (keyLEFT && tankAngle > Math.PI && !keyUP && !keyDOWN) {
			tankAngle -= 0.05;
			if (almostEqual(Math.PI, tankAngle))
				tankAngle = Math.PI;
//			locX -= 1;
		} else if (keyUP && (tankAngle > -Math.PI / 2) && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if (almostEqual(-Math.PI / 2, tankAngle))
				tankAngle = -Math.PI / 2;
//			locY -= 1;
		} else if (keyUP && (tankAngle < -Math.PI / 2) && !keyLEFT && !keyRIGHT) {
			tankAngle += 0.05;
			if (almostEqual(-Math.PI / 2, tankAngle))
				tankAngle = -Math.PI / 2;
//			locY -= 1;
		} else if (keyUP && (tankAngle != 3 * Math.PI / 2 && tankAngle != -Math.PI / 2) && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if (almostEqual(-Math.PI / 2, tankAngle) || almostEqual(tankAngle, 3 * Math.PI / 2))
				tankAngle = -Math.PI / 2;
//			locY -= 1;
		} else if (keyDOWN && tankAngle < Math.PI / 2 && !keyLEFT && !keyRIGHT) {
			tankAngle += 0.05;
			if (almostEqual(Math.PI / 2, tankAngle))
				tankAngle = Math.PI / 2;
//			locY += 1;
		} else if (keyDOWN && tankAngle != Math.PI / 2 && !keyLEFT && !keyRIGHT) {
			tankAngle -= 0.05;
			if (almostEqual(Math.PI / 2, tankAngle))
				tankAngle = Math.PI / 2;
//			locY += 1;
		} else {

			preLocX = locX ;
			preLocY = locY ;

			if (keyUP) {
                    locY -= 6;
                    if(!level1Won) {
						Map.yOffset += 6;
						if (!Map.checkHitWithObjects() || !Map.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							Map.yOffset -= 6;
						}
					}else {
						MapLevel2.yOffset += 6;
						if (!MapLevel2.checkHitWithObjects() || !MapLevel2.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							MapLevel2.yOffset -= 6;
						}
					}

			}
			if (keyDOWN) {
                    locY += 6;
                    if(!level1Won) {
						Map.yOffset -= 6;
						if (!Map.checkHitWithObjects() || !Map.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							Map.yOffset += 6;
						}
					} else {
						MapLevel2.yOffset -= 6;
						if (!MapLevel2.checkHitWithObjects() || !MapLevel2.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							MapLevel2.yOffset += 6;
						}
					}
			}
			if (keyLEFT) {
                    locX -= 6;
                    if(!level1Won) {
						Map.xOffset += 6;
						if (!Map.checkHitWithObjects() || !Map.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							Map.xOffset -= 6;
						}
					} else {
						MapLevel2.xOffset += 6;
						if (	!MapLevel2.checkHitWithObjects() || !MapLevel2.collisionUserTankAndEnemy() ) {
							locX = preLocX;
							locY = preLocY;
							MapLevel2.xOffset -= 6;
						}
					}
			}
			if (keyRIGHT) {
                    locX += 6;
                    if(!level1Won) {
						Map.xOffset -= 6;
						if (!Map.checkHitWithObjects() || !Map.collisionUserTankAndEnemy()) {
							locX = preLocX;
							locY = preLocY;
							Map.xOffset += 6;
						}
					}
					else {
					MapLevel2.xOffset -= 6;
					if (	!MapLevel2.checkHitWithObjects() || !MapLevel2.collisionUserTankAndEnemy() ) {
						locX = preLocX;
						locY = preLocY;
						MapLevel2.xOffset += 6 ;}

				}
			}
		}


		if (tankAngle >= 2 * Math.PI)
			tankAngle -= 2 * Math.PI;
		else if (tankAngle <= -2 * Math.PI)
			tankAngle += 2 * Math.PI;

		locX = Math.max(locX, 0);
		locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
		locY = Math.max(locY, 0);
		locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);

		tankCenterX = locX + 50;
		tankCenterY = locY + 50;

		angle = Math.atan2(mouseY - (locY + 50), mouseX - (locX + 50));

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
			switch (e.getKeyCode()) {
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

			if(e.getKeyCode() == KeyEvent.VK_ALT) {
				try {

					String cheat = JOptionPane.showInputDialog("Input cheat here ");
					if (cheat.equals("infa")) {
						UserTank.numberOfLightBullet = 9999;
						UserTank.numberOfHeavyBullet = 9999;
					} else if (cheat.equals("infh")) {
						UserTank.health = 9999;
					}
				} catch (Exception ex){
					}

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
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
		public void mouseMoved(MouseEvent mouseEvent) {
			mouseX = mouseEvent.getX() + diam / 2;
			mouseY = mouseEvent.getY() + diam / 2;
		}

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
		}

		@Override
		public void mousePressed(MouseEvent mouseEvent){
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent){
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent){
			mouseX = mouseEvent.getX() + diam / 2;
			mouseY = mouseEvent.getY() + diam / 2;
		}
	}
}