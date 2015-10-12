package view;

import java.awt.event.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.EarthInvasionModel;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {
    private AnimationTimer timer;
    private Image image;
    private final EarthInvasionModel model;
    private GraphicsContext gc;
    private Canvas canvas;
    
    public EarthInvasionView(EarthInvasionModel model){

        this.model = model;
        EarthInvasionController controller = new EarthInvasionController(model, this); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        initView();
        canvas = new Canvas(model.getScreenWidth(), model.getScreenHeight());
        //GraphicsContext gc = canvas.getGraphicsContext2D();
       
        this.getChildren().add(canvas);
        
        
        
        graphicsStart();
    }

    protected class run extends AnimationTimer {
        
        private long previousNs = 0;
        
        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            GraphicsContext gc = canvas.getGraphicsContext2D();
            // paint the background
            drawBackground(gc);

            // paint the player
            drawPlayer(gc);

            // paint the balls
            
            model.setPlayerX();
        }
        
    }
    
    
    public void graphicsStart() {
        timer = new run();
        timer.start();
    }
    
    
    
    
    
    /**
     * 
     */  
    public void drawPlayer(GraphicsContext gc) {
        image = new Image("resources/ship.png");
        System.out.println("Image X: "+model.getPlayerX()+" Y: "+model.getPlayerY());
        gc.drawImage(image, model.getPlayerX(), model.getPlayerY());
    }
    
    
    public void drawBackground(GraphicsContext gc) {
        image = new Image("resources/bg1.jpg");
        gc.drawImage(image, 0, 0);
    }
    
    
    private void draw(GraphicsContext gc) {
        gc.setLineWidth(10);
        gc.setFill(Color.RED);
        gc.fillOval(50, 50, 10, 10);
    }
    
    private void initView(){
        this.setPadding(new Insets(0, 0, 0, 0));
        MenuBar menuBar = createMenu();
        this.getChildren().addAll(menuBar); // Creates the menu at the top. 
    }
    /**
     * 
     * @return 
     */
    private MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        MenuItem chrille = new MenuItem("Christian");
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem quitItem = new MenuItem("Quit");
        MenuItem highscoreItem = new MenuItem("Highscore");
        fileMenu.getItems().addAll(newGameItem,highscoreItem,new SeparatorMenuItem(),quitItem);
        
        MenuItem rulesItem = new MenuItem("Rules");
        helpMenu.getItems().addAll(rulesItem);
        
        menuBar.getMenus().addAll(fileMenu,helpMenu); //Adds all menus to the menu bar.
        return menuBar;
    }
  
    
    
    
}
