package utility;
/**
 * This class is used to play sounds in a new Thread
 *
 * @author Mohamad Chaman-Motlagh
 */

import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer extends Thread{
    private String path;

    public SoundPlayer(String path) {

        this.path = path;

    }

    @Override
    public void run() {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            AudioStream audioStream = new AudioStream(in);

            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
