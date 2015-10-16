package view;

import static com.sun.javafx.runtime.async.BackgroundExecutor.getTimer;
import java.util.ArrayList;
import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.EarthInvasionModel;
import model.GameObject;

/**
 *
 * @author Chrille
 */
public class EarthInvasionController {

    private final EarthInvasionModel model;
    private final EarthInvasionView view;

    EarthInvasionController(EarthInvasionModel model, EarthInvasionView view) {
        this.model = model;
        this.view = view;
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                model.setVelX(-4, 1);
                break;
            case RIGHT:
                model.setVelX(4, 1);
                break;
            case COMMA:
                model.PlayerShot(1);
                break;
            case A:
                if(model.getNoOfPlayers()==2){
                    model.setVelX(-4, 2);
                }
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    model.setVelX(4, 2);
                }
                
                break;
            case SPACE:
                if(model.getNoOfPlayers()>1){
                    model.PlayerShot(2);
                }
                
                break;
            case V:
                model.alienShot(1);
                break;
            default:
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {

            case LEFT:
                model.setVelX(0, 1);
                break;
            case RIGHT:
                model.setVelX(0, 1);
                break;
            case COMMA:
                //System.out.println("PANG!");
                break;
            case A:
                if(model.getNoOfPlayers()>1){
                    model.setVelX(0, 2);
                }
                
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    model.setVelX(0, 2);
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
