package view;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Alien;
import model.EarthInvasionModel;
import model.GameObject;
import model.Player;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {

    private AnimationTimer timer;
    private Image image;
    private final EarthInvasionModel model;
    private final EarthInvasionController controller;
    private GraphicsContext gc;
    private Canvas canvas;
    private EventHandler shipHandler;
    private Alert alert;
    private Audio a;

    public EarthInvasionView(EarthInvasionModel model) {
        a = new Audio();
        this.model = model;
        controller = new EarthInvasionController(model, this, a); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController
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
            
            
            //model.tick(1);
            //model.tick(2);
            model.movePlayers();
            model.moveAlien();
            model.moveShot();
            // constrain the objects
            model.constrain();
            // collision
            collision();
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
            
            model.checkIfShootPlayer();
                    
            if (isGameOver()) {
                showGameOver("Game Over"); // ENDAST TILLFÄLLIG!!
                timer.stop();
            }
        }
    }

    public void collision() {
        model.collisionWithObjects();
        model.moveAlienDown();
    }

    public void graphicsStart() {
        gc = canvas.getGraphicsContext2D();
        
        //Thread gu = new Thread(new GraphicsUpdater(gc,controller,model, this));
        //gu.start();
        timer = new run();
        timer.start();
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

    private void initView() {
        this.setPadding(new Insets(0, 0, 0, 0));

        MenuBar menuBar = createMenu();
        this.getChildren().addAll(menuBar); // Creates the menu at the top.
        canvas = new Canvas(model.getScreenWidth() + 12, model.getScreenHeight());
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        this.getChildren().addAll(canvas);
    }

    /**
     *
     * @return
     */
    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("File");
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");
        
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem quitItem = new MenuItem("Quit");
        MenuItem saveItem = new MenuItem("Save", new ImageView(new Image("resources/save.png",15,15,true,true)));
        
        MenuItem highscoreItem = new MenuItem("Highscore");
        
        fileMenu.getItems().addAll(newGameItem, saveItem, highscoreItem, new SeparatorMenuItem(), quitItem);
        
        MenuItem bgMusic = new MenuItem("Music");
        bgMusic.setDisable(true);
        
        Slider bgS = new Slider(0,1,a.getBgVolume());
        bgS.setShowTickLabels(true);
        bgS.setShowTickMarks(true);
        
        bgS.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            oldValue = a.getBgVolume();
            a.setBgVolume(newValue.doubleValue());
            
        });
        
        MenuItem sfxMusic = new MenuItem("Sound effects");
        sfxMusic.setDisable(true);
        
        Slider fxS = new Slider(0,1,a.getSoundEffectsVolume());
        fxS.setShowTickLabels(true);
        fxS.setShowTickMarks(true);
        
        fxS.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            oldValue = a.getSoundEffectsVolume();
            a.setSoundEffectsVolume(newValue.doubleValue());
        });
        
        CustomMenuItem sliderBg = new CustomMenuItem(bgS);
        CustomMenuItem sliderSfx = new CustomMenuItem(fxS);
        sliderBg.setHideOnClick(false);
        sliderSfx.setHideOnClick(false);
        
        Menu music = new Menu("Music");
        music.getItems().addAll(bgMusic,sliderBg,new SeparatorMenuItem(),sfxMusic,sliderSfx);
        
        MenuItem pauseItem = new MenuItem("Pause");
        MenuItem resumeItem = new MenuItem("Resume");
        gameMenu.getItems().addAll(pauseItem, resumeItem, new SeparatorMenuItem(), music);
        
        MenuItem rulesItem = new MenuItem("Rules");
        helpMenu.getItems().add(rulesItem);
        
        quitItem.setAccelerator(KeyCombination.keyCombination("Esc"));
        quitItem.setOnAction((ActionEvent event) -> {
            controller.handleQuit(event);
        });
        newGameItem.setOnAction((ActionEvent event) -> {
            controller.handleNewGame(event);
        });
        saveItem.setAccelerator(KeyCombination.keyCombination("ctrl+S"));
        saveItem.setOnAction((ActionEvent event) -> {
            controller.handleSaveItem(event);
        });
        rulesItem.setOnAction((ActionEvent event) -> {
            controller.handleRules(event);
        });
        highscoreItem.setOnAction((ActionEvent event) -> {
            controller.handleHighscore(event);
        });
        pauseItem.setAccelerator(KeyCombination.keyCombination("P"));
        pauseItem.setOnAction((ActionEvent event) ->{
            controller.handlePause(event);
        });
        resumeItem.setAccelerator(KeyCombination.keyCombination("R"));
        resumeItem.setOnAction((ActionEvent event) ->{
            controller.handleResume(event);
        });
        //menuBar.useSystemMenuBarProperty().set(true);
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu); //Adds all menus to the menu bar.
        return menuBar;
    }

    private void addEventHandlers() {

        shipHandler = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                //controller.handleShip(event);
                controller.keyPressed(event);
            } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                controller.keyReleased(event);
            }
        };
        canvas.setOnKeyPressed(shipHandler);
        canvas.setOnKeyReleased(shipHandler);
    }

    public void setFrostEffect(int BLUR_VALUE, int AMOUNT) {
        canvas.setEffect(new BoxBlur(BLUR_VALUE, BLUR_VALUE, AMOUNT));
    }

    private boolean isGameOver() {
        Boolean gameOver = false;

        for (GameObject go : controller.getAlien()) {
            if (((Alien) go).isAtBottom()) {
                gameOver = true;
            }

        }
        return gameOver;
    }

    private void showGameOver(String message) {
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel", 100));
        gc.setLineWidth(5);
        gc.strokeText(message, 25, 360);
    }

    public void setTimerStop() {
        timer.stop();
    }

    public void setTimerStart() {
        timer.start();
    }

}
