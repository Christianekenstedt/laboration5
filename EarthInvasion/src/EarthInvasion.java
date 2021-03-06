import controller.FileHandler;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.GameModel;
import view.Audio;

import view.EarthInvasionView;
import view.MainMenu;

/**
 *
 * @author Chrille
 */
public class EarthInvasion extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {
        FileHandler file = new FileHandler();
        Audio audio = new Audio();
        MainMenu m = new MainMenu(audio, file);
        GameModel model = new GameModel(MainMenu.getNoOfPlayers());
        EarthInvasionView root = new EarthInvasionView(model,audio, file);

        
        Scene scene = new Scene(root, EarthInvasionView.getScreenWidth(), EarthInvasionView.getScreenHeight()+15);

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