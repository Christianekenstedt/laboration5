/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.scene.media.AudioClip;
import javafx.scene.media.*;
import static javafx.scene.media.MediaPlayer.Status.PAUSED;


/**
 *
 * @author Chrille
 */
public class Audio {
    
    private static AudioClip bulletSound;
    private static AudioClip alienKilled;
    private Media music;
    private static MediaPlayer bgMusic;
    private static double bgVolume;
    private static double soundEffectsVolume;
    
    public Audio(){
        loadSounds();
    }

    private void loadSounds() {
        music = new Media(getClass().getResource("/resources/audio/music.mpeg").toExternalForm());
        bgMusic = new MediaPlayer(music);
        setBgVolume(0.2);
        //bgMusic.setAutoPlay(true);
        bulletSound = new AudioClip(getClass().getResource("/resources/audio/LaserGun.mp3").toExternalForm());
        alienKilled = new AudioClip(getClass().getResource("/resources/audio/invaderkilled.wav").toExternalForm());
        setSoundEffectsVolume(0.5);
        
        
        
    }
    
    public void playBG(){
        bgMusic.setVolume(getBgVolume());
    }
    public static void playBullet(){
        bulletSound.setVolume(Audio.getSoundEffectsVolume());
        bulletSound.play();
        
    }
    public void alienKilled(){
        alienKilled.setVolume(Audio.getSoundEffectsVolume());
        alienKilled.play();
    }
    
    public  void setMediaPlayerVolume(){
        bgMusic.setVolume(getBgVolume());
    }
    
    public  double getBgVolume() {
        return bgVolume;
    }
    
    public  void setBgVolume(double bgVolume) {
        
        Audio.bgVolume = bgVolume;
        System.out.println(Audio.bgVolume);
    }

    public static double getSoundEffectsVolume() {
        return soundEffectsVolume;
    }
    
    public static void toggleBGMusic(){
        if(bgMusic.getStatus() == PAUSED) bgMusic.play();
        else bgMusic.pause();
        
    }
    
    public static void setSoundEffectsVolume(double soundEffectsVolume) {
        Audio.soundEffectsVolume = soundEffectsVolume;
    }
}
