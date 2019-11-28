package Game;

/*
*
*@author Pierce Miller 
*@studentid 14872510
*
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class musicPlayer {

    public static void playMusic(String filepath) {

        InputStream music;

        try {

            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Music Error" + e);
        }
    }

}
