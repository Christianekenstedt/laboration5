package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.EarthInvasionModel;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {
    private Image image, ship;
    private final EarthInvasionModel model;
    private GraphicsContext gc;
    private Canvas canvas;
    private double x,y;
    
    public EarthInvasionView(EarthInvasionModel model){
        this.model = model;
        x = 215;
        y = 600;
        System.out.println("x="+x + "y=" + y);
        EarthInvasionController controller = new EarthInvasionController(model, this); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        initView();
        canvas = new Canvas(model.getScreenWidth(), model.getScreenHeight());
        
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        drawImage(gc);

    }
    /**
     * 
     */
    private void drawImage(GraphicsContext gc) {
        image = new Image("resources/bg1.jpg");
        ship = new Image("resources/ship.png");

        gc.drawImage(image,0,0);
        drawShip(gc);
        
    }
    
    private void drawShip(GraphicsContext gc) {
        System.out.println("rita skepp");
        gc.drawImage(ship,x,y);
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
                    x = x - 1.0;
                    break;
                case RIGHT:
                    x = x + 1.0;
                    break;
                default:
            }
            drawShip(gc);
        }
    }  
}
