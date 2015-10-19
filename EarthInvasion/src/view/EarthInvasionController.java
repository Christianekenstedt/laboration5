package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import model.EarthInvasionModel;
import model.GameObject;
import view.Audio;
/**
 *
 * @author Chrille
 */
public class EarthInvasionController {

    private final EarthInvasionModel model;
    private final EarthInvasionView view;
    //private final Audio audio;

    public EarthInvasionController(EarthInvasionModel model, EarthInvasionView view){
        this.model = model;
        this.view = view;
        //this.audio = audio;
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                model.playerMovement(1, 1);
                break;
            case RIGHT:
                model.playerMovement(2, 1);
                break;
            case COMMA:
                model.PlayerShot(1);
                Audio.playBullet();
                break;
            case A:
                if(model.getNoOfPlayers()==2){
                    model.playerMovement(1, 2);
                }
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    model.playerMovement(2, 2);
                }
                
                break;
            case SPACE:
                if(model.getNoOfPlayers()>1){
                    model.PlayerShot(2);
                    Audio.playBullet();
                }
                
                break;
            case V:
                model.alienShot(1);
                //audio.toggleBGMusic();
                //audio.alienKilled();
                break;
            default:
            
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                model.playerMovement(3, 1);
                break;
            case RIGHT:
                model.playerMovement(4, 1);
                break;
            case COMMA:
                //System.out.println("PANG!");
                break;
            case A:
                if(model.getNoOfPlayers()>1){
                    model.playerMovement(3, 2);
                }
                
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    model.playerMovement(4, 2);
                }
                
                break;
            case SPACE:

                break;
            default:
            
        }
    }

    public void handleNewGame(ActionEvent event) {
        System.out.println("START NEW GAME!!");
    }

    public void handleHighscore(ActionEvent event) {
        System.out.println("HIGHSCORE!");
    }

    public void handleRules(ActionEvent event) {
        System.out.println("RULES!!");
        RulesWindow rules = new RulesWindow();
        view.setTimerStop();
        view.setFrostEffect(10, 3);
        rules.showWindow();
        view.setTimerStart();
        view.setFrostEffect(0, 0);
    }

    public void handleQuit(ActionEvent event) {
        AlertWindow alert = new AlertWindow();
        view.setTimerStop();
        view.setFrostEffect(10, 3);
        alert.showWindow();
        view.setTimerStart();
        view.setFrostEffect(0, 0);

    }
    
    public void handlePause(ActionEvent event) {
        view.setTimerStop();
        view.setFrostEffect(10, 3);
        
    }

    public void handleResume(ActionEvent event) {
        view.setTimerStart();
        view.setFrostEffect(0, 0);
    }
    
    void handleSaveItem(ActionEvent event) {
        System.out.println("SAVE");
    }

    public ArrayList<GameObject> getPlayer() {
        return (ArrayList) model.getPlayer();
    }

    public ArrayList<GameObject> getAlien() {
        return (ArrayList) model.getAlien();
    }

    public ArrayList<GameObject> getShot() {
        return (ArrayList) model.getShot();
    }

    public ArrayList<GameObject> getBlock() {
        return (ArrayList) model.getBlock();
    }


}
