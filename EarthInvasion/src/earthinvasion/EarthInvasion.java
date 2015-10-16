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
        // skapa EarthInvasionModel och skicka som argument till EarthInvasionView
        /*menuStage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/view/FXMLMainMenu.fxml"));
        Scene scene2 = new Scene(root2, 640, 720);
        
        System.out.println(FXMLMainMenuController.getNoOfPlayers());
        
        if(FXMLMainMenuController.getNoOfPlayers() == 0){
            menuStage.setTitle("Earth Invasion!");
            menuStage.setScene(scene2);
            menuStage.setResizable(false);
            menuStage.getIcons().add(new Image("resources/alien_icon.png"));
            menuStage.showAndWait();
            
        }*/
        MainMenu m = new MainMenu();
        m.showWindow();
        System.out.println(MainMenu.getNoOfPlayers());
        EarthInvasionModel model = new EarthInvasionModel(MainMenu.getNoOfPlayers());
        EarthInvasionView root = new EarthInvasionView(model);
        
        Scene scene = new Scene(root, model.getScreenWidth(), model.getScreenHeight());
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                AlertWindow alert = new AlertWindow();
                root.setTimerStop();
                root.setFrostEffect(10, 3);
                alert.showWindow();
                root.setTimerStart();
                root.setFrostEffect(0, 0);
                event.consume();
            }
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