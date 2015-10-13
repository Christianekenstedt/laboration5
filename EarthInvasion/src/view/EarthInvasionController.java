package view;

import java.util.ArrayList;
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
                case SPACE:
                    System.out.println("PANG!");
                    model.PlayerShot(1);
                    break;
                case A:
                    model.setVelX(-4,2);
                    break;
                case D:
                    model.setVelX(4,2);
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
                case SPACE:
                    //System.out.println("PANG!");
                    break;
                case A:
                    model.setVelX(0,2);
                    break;
                case D:
                    model.setVelX(0,2);
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
        System.exit(0);
        
    
    }
    public ArrayList<GameObject> getObjects() {
        return (ArrayList)model.getObjects().clone();
    }
    
    /*
    public ArrayList<Player> getPlayers(){
        return (ArrayList)model.getPlayer().clone();
    }
    public ArrayList<Block> getBlocks(){
        return (ArrayList)model.getBlocks().clone();
    }
    public ArrayList<Alien> getAliens(){
        return (ArrayList)model.getAliens().clone();
    }
    */
}
