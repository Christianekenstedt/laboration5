package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.EarthInvasionModel;
import model.Player;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {
    private AnimationTimer timer;
    private Image image, shipImage;
    private final EarthInvasionModel model;
    private GraphicsContext gc;
    private Canvas canvas;
    private Player player;
    private double x,y;
    public EarthInvasionView(EarthInvasionModel model){

        this.model = model;
        EarthInvasionController controller = new EarthInvasionController(model, this); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        initView();
        canvas = new Canvas(model.getScreenWidth(), model.getScreenHeight());
        canvas.setOnKeyPressed(new ShipKeyHandler()); // WHY U NO WORK?!?!
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        
        this.getChildren().add(canvas);

        graphicsStart();
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        System.out.println("You pressed me!");
                        model.setPlayerX(me.getX());
                    }
                });
    }

    protected class run extends AnimationTimer {
        
        private long previousNs = 0;
        
        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            gc = canvas.getGraphicsContext2D();
            
            // paint the background
            drawBackground(gc);

            // paint the player
            drawPlayer(gc);

            // paint the balls
            //model.setPlayerX();
        }
        
    }
    
    
    public void graphicsStart() {
        timer = new run();
        timer.start();
    }
    
    
    
    
    
    /**
     * 
     * @param gc
     */  
    public void drawPlayer(GraphicsContext gc) {
        shipImage = new Image("resources/ship.png");
        System.out.println("Image X: "+model.getPlayerX()+" Y: "+model.getPlayerY());
        gc.drawImage(shipImage, model.getPlayerX(), model.getPlayerY());
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
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem quitItem = new MenuItem("Quit");
        MenuItem highscoreItem = new MenuItem("Highscore");
        fileMenu.getItems().addAll(newGameItem,highscoreItem,new SeparatorMenuItem(),quitItem);
        
        MenuItem rulesItem = new MenuItem("Rules");
        helpMenu.getItems().addAll(rulesItem);
        
        menuBar.getMenus().addAll(fileMenu,helpMenu); //Adds all menus to the menu bar.
        return menuBar;
    }
    
   
    private class ShipKeyHandler implements EventHandler<KeyEvent> {
        public ShipKeyHandler(){
            System.out.println("created keyHandler");
        }
        @Override
        public void handle(KeyEvent event) {
            System.out.println("u clicked!");
            switch (event.getCode()) {
                case UP:
                    System.out.println("upp!");
                    y = y - 1.0;
                    break;
                case DOWN:
                    y = y + 1.0;
                    break;
                case LEFT:
                    System.out.println("left");
                   //model.setPlayerX(model.getPlayerX() -1.0);
                    //x = x - 1.0;
                    break;
                case RIGHT:
                    System.out.println("right");
                    //model.setPlayerX(model.getPlayerX() +1.0);
                    //x = x + 1.0;
                    break;
                default:
            }
            //gc.drawImage(shipImage, x, y);
            //drawPlayer(gc);
        }
    }  
}
