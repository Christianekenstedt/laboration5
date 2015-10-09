package view;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.EarthInvasionModel;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView {
    VBox root;
    private final EarthInvasionModel model;
    public EarthInvasionView(EarthInvasionModel model){
        this.model = model;
        EarthInvasionController controller = new EarthInvasionController(model, this);
        // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        root = new VBox();
        root.setPadding(new Insets(0,0,0,0));
    }
    
    private Menu createMenu(){
        
        return ;
    }
    
}
