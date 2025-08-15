package Src;
 

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
    private String filePath;
    private int duration; 

    public Music(String filePath, int duration) {
        this.filePath = filePath;
        this.duration = duration;
    }

    public void play() {
        try {
            File file = new File(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            Thread.sleep(duration * 1000);
            clip.stop();
        } catch (Exception e) {
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    public int getDuration() {
        return duration;
    }
} 