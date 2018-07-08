/*** In The Name of Allah ***/
package bufferstrategy;

import battleObject.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

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

	public static final int GAME_HEIGHT = 720;                  // 720p game resolution
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio

	//uncomment all /*...*/ in the class for using UserTank icon instead of a simple circle
	/*private BufferedImage image;*/

	private long lastRender;
	private ArrayList<Float> fpsHistory;

	private BufferStrategy bufferStrategy;

	private UserTank tank = new UserTank();
	private boolean mouseHandlerAdded;

	private EnemyTank enemyTank;

	private ArrayList<Drawable> drawables;

	public GameFrame(String title) throws IOException {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		lastRender = -1;
		fpsHistory = new ArrayList<>(100);
		enemyTank = new EnemyTank(300,300);
		drawables = new ArrayList<>();
		drawables.add(tank);
		drawables.add(enemyTank);
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
		try {
			Map map = new Map( state , g2d ) ;
			map.paintMap();

		} catch (IOException e) {
			e.printStackTrace();
		}

		tank.setState(state);
		tank.setG2d(g2d);
		enemyTank.setTarget(state.locX,state.locY);
		enemyTank.setG2d(g2d);
		if(!mouseHandlerAdded) {
			addMouseListener(tank.getTankMouseHandler());
			mouseHandlerAdded = true;
		}
		for(Drawable drawable : drawables)
			drawable.render();

		for(Bullet bullet : tank.getMainGun().getBullets())
			bullet.paint(g2d);
		for(Bullet bullet : tank.getSecondGun().getBullets())
			bullet.paint(g2d);
		tank.fireSecondGun();
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
		}
	}

}
