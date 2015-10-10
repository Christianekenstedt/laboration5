package view;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
    private Image image;
    private Label l;
    private final EarthInvasionModel model;
    private GraphicsContext gc;
    private Canvas canvas;
    
    public EarthInvasionView(EarthInvasionModel model){
        this.model = model;
        EarthInvasionController controller = new EarthInvasionController(model, this); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
        initView();
        //image = new Image("resources/bg.jpg");
        //ImageView img = new ImageView(image);
        //img.setVisible(true);
        //this.getChildren().add(img);
        
        canvas = new Canvas(model.getScreenWidth(), model.getScreenHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        
        drawImage(gc);
        draw(gc);
        
        
    }
    /**
     * 
     */
    private void drawImage(GraphicsContext gc) {
        image = new Image("resources/bg.jpg");
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
  
    
    
    
}
