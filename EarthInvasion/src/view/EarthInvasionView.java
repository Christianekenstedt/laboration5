package view;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.EarthInvasionModel;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {
    
    private Label l;
    private final EarthInvasionModel model;
    public EarthInvasionView(EarthInvasionModel model){
        this.model = model;
        EarthInvasionController controller = new EarthInvasionController(model, this);
        // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        initView();

    }
    
    /*private Menu createMenu(){
        
        return ;
    }*/
    
    private void initView(){
        this.setPadding(new Insets(0, 0, 0, 0));
        MenuBar menuBar = createMenu();
        this.getChildren().addAll(menuBar); // Creates the menu at the top.
        
        
    }
    
    private MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem quitItem = new MenuItem("Quit");
        MenuItem highscoreItem = new MenuItem("Highscore");
        fileMenu.getItems().addAll(newGameItem,highscoreItem,quitItem);
        
        MenuItem rulesItem = new MenuItem("Rules");
        helpMenu.getItems().addAll(rulesItem);
        
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        
        return menuBar;
    }
    
}
