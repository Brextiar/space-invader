package fr.app.ressources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

public class Sound {
    private Clip clip;

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

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public static void playSound(String sound) {
        Sound sound1 = new Sound(sound);
        sound1.play();
    }
}
