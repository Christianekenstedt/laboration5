/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameObject;
import model.EarthInvasionModel;
import view.EarthInvasionController;
import view.EarthInvasionView;

/**
 *
 * @author Chrille
 */
public class GraphicsUpdater implements Runnable {
    private GraphicsContext gc;
    private Image image;
    private EarthInvasionController controller;
    private EarthInvasionModel model;
    private final EarthInvasionView view;
    
    public GraphicsUpdater(GraphicsContext gc, EarthInvasionController controller, EarthInvasionModel model, EarthInvasionView view){
        this.gc = gc;
        this.controller = controller;
        this.model = model;
        this.view = view;
    }
    @Override
    public void run() {
        synchronized(view){
            while(true){
                    // paint the background
                drawBackground(gc);
                // paint info
                drawInfo(gc);


                // paint the objects
                for (GameObject go : controller.getPlayer()) {
                    go.drawObject(gc);
                }
                for (GameObject go : controller.getAlien()) {
                    go.drawObject(gc);
                }
                for (GameObject go : controller.getShot()) {
                    go.drawObject(gc);
                }
                for (GameObject go : controller.getBlock()) {
                    go.drawObject(gc);
                }
                
            }
        }
        }
    public void drawBackground(GraphicsContext gc) {
        image = new Image("resources/bg1.jpg");
        gc.drawImage(image, 0, 0, EarthInvasionModel.getScreenWidth() + 12, EarthInvasionModel.getScreenHeight());
    }

    private void drawInfo(GraphicsContext gc) {
        
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel", 20));

        gc.strokeText("Score: " + model.getScore(), 500, 20);
    }
    
}
