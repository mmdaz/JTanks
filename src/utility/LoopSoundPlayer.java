package utility;
/**
 * This class is used to play a sound in a loop
 *
 * @author Mohamad Chaman-Motlagh
 */

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LoopSoundPlayer {

    public LoopSoundPlayer(String path){

        AudioPlayer audioPlayer = AudioPlayer.player;
        AudioStream audioStream;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream(path);
            audioStream = new AudioStream(test);
            AudioPlayer.player.start(audioStream);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        audioPlayer.start(loop);

    }
}
