package battleObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 * Each class Implementing this interface must implement render method
 *
 * @author Mohamad Chaman-Motlagh
 */
public interface Drawable {
    public void render() throws IOException;
    public void setG2d(Graphics2D g2d);
    public boolean isAlive();
    public void damage(int damge);
    public Rectangle2D getRect () ;
}
