package view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.*;
import static javafx.scene.media.MediaPlayer.Status.PAUSED;
import javafx.util.Duration;

/**
 *
 * @author Chrille
 */
public class Audio {

    private static AudioClip bulletSound;
    private static AudioClip alienKilled;
    private AudioClip playerKilled;
    private AudioClip alienShoot;
    
    private Media music;
    private static MediaPlayer bgMusic;
    private static double bgVolume;
    private static double soundEffectsVolume;
    

    public Audio() {
        loadSounds();
    }

    private void loadSounds() {
        music = new Media(getClass().getResource("/resources/audio/music.mpeg").toExternalForm());
        bgMusic = new MediaPlayer(music);
        bgMusic.setVolume(0.1);
        bgMusic.setAutoPlay(true);
        bgMusic.setOnEndOfMedia(() -> {
            bgMusic.seek(Duration.ZERO);
            bgMusic.play();
        });
        bulletSound = new AudioClip(getClass().getResource("/resources/audio/LaserGun.mp3").toExternalForm());
        alienKilled = new AudioClip(getClass().getResource("/resources/audio/invaderkilled.wav").toExternalForm());
        playerKilled = new AudioClip(getClass().getResource("/resources/audio/playerDie.wav").toExternalForm());
        alienShoot = new AudioClip(getClass().getResource("/resources/audio/alienShoot.wav").toExternalForm());
        setSoundEffectsVolume(0.3);

    }

    public void playBG() {
        bgMusic.setVolume(getBgVolume());
    }

    public static void playBullet() {
        bulletSound.setVolume(Audio.getSoundEffectsVolume());
        bulletSound.play();

    }

    public void alienKilled() {
        alienKilled.setVolume(Audio.getSoundEffectsVolume() - 0.2);
        alienKilled.play();
    }
    
    public void playerKilled(){
        playerKilled.setVolume(Audio.getSoundEffectsVolume());
        playerKilled.play();
    }
    public void alienShoot(){
        alienShoot.setVolume(Audio.getSoundEffectsVolume()-0.2);
        alienShoot.play();
    }

    public void setMediaPlayerVolume() {
        bgMusic.setVolume(getBgVolume());
    }

    public double getBgVolume() {
        return bgVolume;
    }

    public void setBgVolume(double bgVolume) {

        Audio.bgVolume = bgVolume;
        System.out.println(Audio.bgVolume);
    }

    public static double getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public static void toggleBGMusic() {
        if (bgMusic.getStatus() == PAUSED) {
            bgMusic.play();
        } else {
            bgMusic.pause();
        }

    }

    public static void setSoundEffectsVolume(double soundEffectsVolume) {
        Audio.soundEffectsVolume = soundEffectsVolume;
    }
}
