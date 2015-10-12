package view;

import com.sun.glass.events.WindowEvent;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import model.Alien;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.EarthInvasionModel;
import model.Player;
import model.Block;

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
                case SPACE:
                    //System.out.println("PANG!");
                    break;
                default:
            }
    
        //model.setVelX(0);
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
    public ArrayList<Player> getPlayers(){
        return (ArrayList)model.getPlayer().clone();
    }
    public ArrayList<Block> getBlocks(){
        return (ArrayList)model.getBlocks().clone();
    }
    public ArrayList<Alien> getAliens(){
        return (ArrayList)model.getAliens().clone();
    }
}
