/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.media.AudioClip;
import javafx.scene.media.*;
import static javafx.scene.media.MediaPlayer.Status.STOPPED;
import javafx.util.Duration;


/**
 *
 * @author Chrille
 */
public class Audio {
    
    private AudioClip bulletSound;
    private AudioClip alienKilled;
    private Media music;
    private MediaPlayer bgMusic;
    private double bgVolume;
    private double soundEffectsVolume;
    
    public Audio(){
        loadSounds();
    }

    private void loadSounds() {
        music = new Media(getClass().getResource("/resources/audio/music.mpeg").toExternalForm());
        bulletSound = new AudioClip(getClass().getResource("/resources/audio/LaserGun.mp3").toExternalForm());
        alienKilled = new AudioClip(getClass().getResource("/resources/audio/invaderkilled.wav").toExternalForm());
        setSoundEffectsVolume(0.5);
        setBgVolume(0.2);
        
    }
    
    public void playBG(){
        
        bgMusic = new MediaPlayer(music);
        bgMusic.setVolume(getBgVolume());
        bgMusic.play();
    }
    public void playBullet(){
        bulletSound.setVolume(getSoundEffectsVolume());
        bulletSound.play();
        
    }
    public void alienKilled(){
        alienKilled.setVolume(getSoundEffectsVolume());
        alienKilled.play();
    }
    
    public void setMediaPlayerVolume(){
             bgMusic.setVolume(getBgVolume());
    }
    
    public double getBgVolume() {
        return bgVolume;
    }
    
    public void setBgVolume(double bgVolume) {
        this.bgVolume = bgVolume;
    }

    public double getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public void setSoundEffectsVolume(double soundEffectsVolume) {
        this.soundEffectsVolume = soundEffectsVolume;
    }

    /*@Override
    public void update(Observable o, Object o1) {
        //setBgVolume((Double) o.);
    }*/

 
    
}
