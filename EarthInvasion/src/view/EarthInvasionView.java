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
import model.Block;
import model.EarthInvasionModel;
import model.Player;
import view.EarthInvasionController;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {
    private AnimationTimer timer;
    private Image image, shipImage;
    private final EarthInvasionModel model;
    private final EarthInvasionController controller;
    private GraphicsContext gc;
    private Canvas canvas;
    private EventHandler shipHandler,menuQuitItem, menuRulesItem, menuNewGameItem, menuHighscoreItem;
    public EarthInvasionView(EarthInvasionModel model){

        this.model = model;
        controller = new EarthInvasionController(model, this); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        //Creates the window, menu bar and so on
        initView();
        // Add all the event handlers
        addEventHandlers();
        // Start the graphics
        graphicsStart();
        
        
    }

    protected class run extends AnimationTimer {

        private long previousNs = 0;
        
        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            gc = canvas.getGraphicsContext2D();
            model.tick();
            // paint the background
            drawBackground(gc);
            // paint the blocks
            for(Block b: controller.getBlocks()){
                b.drawBlock(gc);
            }
            // paint the player
            for(Player p: controller.getPlayers()){
                p.playerDraw(gc);
            }
            // paint the aliens

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
        //System.out.println("Image X: "+model.getPlayerX()+" Y: "+model.getPlayerY());
        gc.drawImage(shipImage, model.getPlayerX(), model.getPlayerY());
    }
    
    
    public void drawBackground(GraphicsContext gc) {
        image = new Image("resources/bg1.jpg");
        gc.drawImage(image, 0, 0);
    }
    
    private void initView(){
        this.setPadding(new Insets(0, 0, 0, 0));

        MenuBar menuBar = createMenu();
        this.getChildren().addAll(menuBar); // Creates the menu at the top.
        canvas = new Canvas(model.getScreenWidth(), model.getScreenHeight());
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        this.getChildren().add(canvas);
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
        
        quitItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit!");
                controller.handleQuit(event);
            }
            
        });
        newGameItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NEW GAME!");
            }
            
        });
        rulesItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RULES!");
            }
            
        });
        highscoreItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("HIGHSCORE!!");
                
            }
            
        });
        menuBar.getMenus().addAll(fileMenu,helpMenu); //Adds all menus to the menu bar.
        return menuBar;
    }
    private void addEventHandlers(){
        
        shipHandler = new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                
                if(event.getEventType() == KeyEvent.KEY_PRESSED){
                //controller.handleShip(event); 
                    controller.keyPressed(event);
                }
   
                else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
                    controller.keyReleased(event);
                }
                
                
                
                
            }
        };
        canvas.setOnKeyPressed(shipHandler);
        canvas.setOnKeyReleased(shipHandler);
    }
}
