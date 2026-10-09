package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SoundEffect extends SoundClip {
    private static float currentVolume = 75f;

    private static List<SoundEffect> allEffects = new ArrayList<>();

    public SoundEffect(String fileName) throws Exception {
        super(fileName);
        allEffects.add(this);
        this.setVolume(currentVolume / 100f);
    }

    public static void setCurrentVolume(int volume) {
        currentVolume = Math.min(Math.max(volume, 0), 100);
        float scaled = currentVolume / 100f;
        for (SoundEffect effect : allEffects) {
            effect.setVolume(scaled);
        }
    }

    public static float getCurrentVolume() {
        return currentVolume;
    }

}
