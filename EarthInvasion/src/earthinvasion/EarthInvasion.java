package earthinvasion;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import model.EarthInvasionModel;

import view.EarthInvasionView;

/**
 *
 * @author Chrille
 */
public class EarthInvasion extends Application {
    
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        // skapa EarthInvasionModel och skicka som argument till EarthInvasionView
        
        
        
        EarthInvasionModel model = new EarthInvasionModel();
        EarthInvasionView root = new EarthInvasionView(model);
        
        Scene scene = new Scene(root, model.getScreenWidth(), model.getScreenHeight());

        primaryStage.setTitle("Earth Invasion!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
