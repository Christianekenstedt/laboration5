package view;

import com.sun.glass.events.WindowEvent;
import java.util.ArrayList;
import javafx.event.ActionEvent;
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
    
    public void handleShip(KeyEvent event){
        
            switch (event.getCode()) {
                case LEFT:
                   model.setPlayerX(model.getPlayerX() - 3.0);
                    break;
                case RIGHT:
                    model.setPlayerX(model.getPlayerX() + 3.0);
                    break;
                case SPACE:
                    System.out.println("PANG!");
                    break;
                default:
            }
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
}

