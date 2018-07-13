/*** In The Name of Allah ***/
package bufferstrategy;

import battleObject.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.* ;
import Map.Map ;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {
	//TODO: Fix
	public static final int GAME_HEIGHT = 800;                  // 600p game resolution
	public static final int GAME_WIDTH = 1600;  // 2:1 aspect ratio

	//uncomment all /*...*/ in the class for using UserTank icon instead of a simple circle
	/*private BufferedImage image;*/

	private long lastRender;
	private ArrayList<Float> fpsHistory;

	private BufferStrategy bufferStrategy;

	private UserTank tank = new UserTank(1000);
	private boolean mouseHandlerAdded;


	public static ArrayList<Drawable> drawables;


	private Map map;

	public GameFrame(String title) throws IOException {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		lastRender = -1;
		fpsHistory = new ArrayList<>(100);
		drawables = new ArrayList<>();
		drawables.add(tank);
		if(Start.startState.equals("easy")) {
			drawables.add(new Mine(400, 100));
			drawables.add(new Mine(400, 200));
			drawables.add(new Mine(500, 200));
			drawables.add(new Turret(500, 1300, 400));
			drawables.add(new KhengEnemy(300, 2100, 200));
			drawables.add(new EnemyTank(500, 2700, 100));
			drawables.add(new Turret(500, 1700, 900));
			drawables.add(new Mine(900, 1600));
			drawables.add(new EnemyTank(600, 1000, 800));
			drawables.add(new EnemyTank(600, 900, 950));
			drawables.add(new Mine(600, 1300));
			drawables.add(new Mine(700, 1300));
			drawables.add(new Mine(600, 1400));
			drawables.add(new Mine(700, 1400));
		} else if(Start.startState.equals("medium")){
			drawables.add(new Mine(400, 100));
			drawables.add(new Mine(400, 200));
			drawables.add(new Mine(500, 200));
			drawables.add(new Turret(500, 1400, 200));
			drawables.add(new Turret(500, 1300, 400));
			drawables.add(new KhengEnemy(300, 2100, 200));
			drawables.add(new EnemyTank(500, 2700, 100));
			drawables.add(new Turret(500, 1700, 900));
			drawables.add(new Turret(500, 1400, 900));
			drawables.add(new Mine(900, 1600));
			drawables.add(new EnemyTank(600, 1000, 800));
			drawables.add(new EnemyTank(600, 900, 950));
			drawables.add(new Mine(600, 1300));
			drawables.add(new Mine(700, 1300));
			drawables.add(new Mine(600, 1400));
			drawables.add(new Mine(700, 1400));
		} else if(Start.startState.equals("hard")){
			drawables.add(new Mine(400, 100));
			drawables.add(new Mine(400, 200));
			drawables.add(new Mine(500, 200));
			drawables.add(new Turret(500, 1400, 200));
			drawables.add(new Turret(500, 1300, 400));
			drawables.add(new KhengEnemy(300, 2100, 200));
			drawables.add(new EnemyTank(500, 2700, 100));
			drawables.add(new EnemyTank(500, 2700, 300));
			drawables.add(new Turret(500, 1700, 900));
			drawables.add(new Turret(500, 1400, 900));
			drawables.add(new Mine(900, 1600));
			drawables.add(new EnemyTank(600, 1000, 800));
			drawables.add(new EnemyTank(600, 900, 1000));
			drawables.add(new EnemyTank(600, 800, 900));
			drawables.add(new Mine(600, 1300));
			drawables.add(new Mine(700, 1300));
			drawables.add(new Mine(600, 1400));
			drawables.add(new Mine(700, 1400));
		}

		map = new Map();
	/*	try{
			image = ImageIO.read(new File("Icon.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}*/


        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("Resources/Images/pointer.png").getImage(),
                new Point(0,0),"custom cursor"));


	}

	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}


	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		// Render single frame
		do {
			// The following loop ensures that the contents of the drawing buffer
			// are consistent in case the underlying surface was recreated
			do {
				// Get a new graphics context every time through the loop
				// to make sure the strategy is validated
				Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
				try {
					doRendering(graphics, state);
				} catch (IOException e) {
                    e.printStackTrace();
                } finally {
					// Dispose the graphics
					graphics.dispose();
				}
				// Repeat the rendering if the drawing buffer contents were restored
			} while (bufferStrategy.contentsRestored());

			// Display the buffer
			bufferStrategy.show();
			// Tell the system to do the drawing NOW;
			// otherwise it can take a few extra ms and will feel jerky!
			Toolkit.getDefaultToolkit().sync();

			// Repeat the rendering if the drawing buffer was lost
		} while (bufferStrategy.contentsLost());
	}

	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state) throws IOException {
		// Draw background
		map.setG2d(g2d);
		Map.setState(state);
		map.paintMap();

		// draw additional objects :
		map.intersectWithRepairObject();
		map.intersectWithCannonShell();
		map.intersectWithMachineGunShell();
		map.intersectWithUpgrader();

		if(!mouseHandlerAdded) {
			addMouseListener(tank.getTankMouseHandler());
			mouseHandlerAdded = true;
		}
		for(Drawable drawable : drawables) {
			if(!(drawable instanceof UserTank))
				drawable.checkIntersect(tank);
			if(drawable instanceof UserTank){
				((UserTank) drawable).setState(state);
				((UserTank) drawable).fireSecondGun();
			}
			drawable.setG2d(g2d);
			drawable.render();
			if(drawable instanceof KhengEnemy){
				((KhengEnemy) drawable).setTarget(state.locX, state.locY);
			}else if(drawable instanceof EnemyTank){
				((EnemyTank) drawable).setTarget(state.locX,state.locY);
				((EnemyTank) drawable).fire(drawables);
				for(Bullet bullet : ((EnemyTank) drawable).getGun().getBullets())
					bullet.paint(g2d);
			} else if(drawable instanceof Turret){
				((Turret) drawable).setTarget(state.locX,state.locY);
				((Turret) drawable).fire();
				for(Bullet bullet : ((Turret) drawable).getGun().getBullets())
					bullet.paint(g2d);
			}
		}

		Iterator<Drawable> drawableIterator = drawables.iterator();
		while (drawableIterator.hasNext())
			if(!drawableIterator.next().isAlive()) {
				drawableIterator.remove();
			}

		for(Drawable drawable: drawables)
			if(drawable instanceof  UserTank) {
				for (Bullet bullet : tank.getMainGun().getBullets())
					bullet.paint(g2d);
				for (Bullet bullet : tank.getSecondGun().getBullets())
					bullet.paint(g2d);
			}

		for (Drawable drawable : drawables)
			if(!(drawable instanceof UserTank))
				tank.checkIntersect(drawable);


		if(!(drawables.get(0) instanceof UserTank))
			state.gameOver = true;


		if(state.locX - Map.xOffset >= 800 && state.locY - Map.yOffset >= 1200){
			state.gameWon = true;
			String str = "You Won!";
			g2d.setColor(Color.RED);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
			new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}).start();
		}

		// Print FPS info
//		long currentRender = System.currentTimeMillis();
//		if (lastRender > 0) {
//			fpsHistory.add(1000.0f / (currentRender - lastRender));
//			if (fpsHistory.size() > 100) {
//				fpsHistory.remove(0); // remove oldest
//			}
//			float avg = 0.0f;
//			for (float fps : fpsHistory) {
//				avg += fps;
//			}
//			avg /= fpsHistory.size();
//			String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
//					avg, (currentRender - lastRender));
//			g2d.setColor(Color.CYAN);
//			g2d.setFont(g2d.getFont().deriveFont(18.0f));
//			int strWidth = g2d.getFontMetrics().stringWidth(str);
//			int strHeight = g2d.getFontMetrics().getHeight();
//			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
//		}
//		lastRender = currentRender;
		// Print user guide
		String userGuide
				= "Use ARROW KEYS to move the Tank. "
				+ "Press ESCAPE to end the game.";
		g2d.setFont(g2d.getFont().deriveFont(18.0f));
//		g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
		// Draw GAME OVER
		if (state.gameOver) {
			String str = "GAME OVER";
			g2d.setColor(Color.RED);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
			new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}).start();

		}
	}

}
