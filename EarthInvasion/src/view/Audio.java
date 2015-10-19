/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
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
    
    public Audio(){
        loadSounds();
    }

    private void loadSounds() {
        music = new Media(getClass().getResource("/resources/audio/music.mpeg").toExternalForm());
        bulletSound = new AudioClip(getClass().getResource("/resources/audio/LaserGun.mp3").toExternalForm());
        alienKilled = new AudioClip(getClass().getResource("/resources/audio/invaderkilled.wav").toExternalForm());
        
    }
    
    public void playBG(){
        bgMusic = new MediaPlayer(music);
        bgMusic.setVolume(0.2);
        bgMusic.play();
    }
    public void playBullet(){
        bulletSound.setVolume(0.5);

        bulletSound.play();
        
    }
    public void alienKilled(){
        alienKilled.setVolume(0.5);
        alienKilled.play();
    }
}
