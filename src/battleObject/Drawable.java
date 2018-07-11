package battleObject;

import java.awt.*;
import java.io.IOException;

/**
 * Each class Implementing this interface must implement render method
 *
 * @author Mohamad Chaman-Motlagh
 */
public interface Drawable {
    public void render() throws IOException;
    public void setG2d(Graphics2D g2d);
}
