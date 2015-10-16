package view;

import static com.sun.javafx.runtime.async.BackgroundExecutor.getTimer;
import java.util.ArrayList;
import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
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
                   model.setVelX(-4,1);
                    break;
                case RIGHT:
                    model.setVelX(4,1);
                    break;
                case COMMA:
                    model.PlayerShot(1);
                    break;
                case A:
                    model.setVelX(-4,2);
                    break;
                case D:
                    model.setVelX(4,2);
                    break;
                case SPACE:
                    model.PlayerShot(2);
                    break;
                default:
            }
    }
    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
                
                case LEFT:
                   model.setVelX(0,1);
                    break;
                case RIGHT:
                    model.setVelX(0,1);
                    break;
                case COMMA:
                    //System.out.println("PANG!");
                    break;
                case A:
                    model.setVelX(0,2);
                    break;
                case D:
                    model.setVelX(0,2);
                    break;
                case SPACE:
                    
                    break;
                default:
            }
        }
    
    public void handleNewGame(ActionEvent event){
        System.out.println("START NEW GAME!!");
    }
    
    public void handleHighscore(ActionEvent event){
        System.out.println("HIGHSCORE!");
    }
    
    public void handleRules(ActionEvent event){
        System.out.println("RULES!!");
    }
    
    public void handleQuit(ActionEvent event){
        AlertWindow alert = new AlertWindow();
        
        view.setTimerStop();
        alert.showWindow();
        view.setTimerStart();
        
    
    }
    void handleSaveItem(ActionEvent event) {
        System.out.println("SAVE");
    }
    public ArrayList<GameObject> getObjects() {
        return (ArrayList)model.getObjects().clone();
    }

    
}
