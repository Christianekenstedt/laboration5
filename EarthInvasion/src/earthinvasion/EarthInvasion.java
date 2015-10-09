/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthinvasion;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.EarthInvasionModel;

import view.EarthInvasionView;

/**
 *
 * @author Chrille
 */
public class EarthInvasion extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // skapa EarthInvasionModel och skicka som argument till EarthInvasionView
        EarthInvasionModel model = new EarthInvasionModel();
        EarthInvasionView root = new EarthInvasionView(model);
        
        Scene scene = new Scene(root, 640,480);
        
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
