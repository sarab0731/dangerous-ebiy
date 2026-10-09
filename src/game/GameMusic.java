package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameMusic extends SoundClip {
    private static double currentVolume = 75f;
    private static List<GameMusic> allClips = new ArrayList<>();


    public GameMusic(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(fileName);
        allClips.add(this);
        this.setVolume(currentVolume / 100f);
    }

    public GameMusic(URL url) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(url);
        allClips.add(this);
        this.setVolume(currentVolume / 100f);
    }

    public static void setCurrentVolume(int volume) {
        currentVolume = Math.min(Math.max(volume, 0.01), 100);
        double scaled = currentVolume / 100;
        for (GameMusic clip : allClips) {
            clip.setVolume(scaled);
        }
    }

    public static double getCurrentVolume() {
        return currentVolume;
    }

}
