package earthinvasion;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.EarthInvasionModel;
import view.AlertWindow;
import view.Audio;

import view.EarthInvasionView;
import view.MainMenu;

/**
 *
 * @author Chrille
 */
public class EarthInvasion extends Application {
    //private static Stage menuStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Audio a = new Audio();
        MainMenu m = new MainMenu(a);
        m.showWindow();
        EarthInvasionModel model = new EarthInvasionModel(MainMenu.getNoOfPlayers(), a);
        EarthInvasionView root = new EarthInvasionView(model);
        
        Scene scene = new Scene(root, model.getScreenWidth(), model.getScreenHeight());
        
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            AlertWindow alert = new AlertWindow();
            root.setTimerStop();
            root.setFrostEffect(10, 3);
            alert.showWindow();
            root.setTimerStart();
            root.setFrostEffect(0, 0);
            event.consume();
        });
        
        primaryStage.setTitle("Earth Invasion!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("resources/alien_icon.png"));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}