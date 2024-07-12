package fr.app.ressources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

public class Sound {

    private Clip clip;

    /**
     * Constructor
     * @param sound the sound path
     */
    public Sound(String sound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource(sound)));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Clip getClip() {
        return clip;
    }

    /**
     * start the sound
     */
    public void play() {
        clip.start();
    }

    /**
     * stop the sound
     */
    public void stop() {
        clip.stop();
    }

    /**
     * used to play short sounds
     * @param sound the sound path
     */
    public static void playSound(String sound) {
        Sound sound1 = new Sound(sound);
        sound1.play();
    }
}
