package view;

import controller.EarthInvasionController;
import controller.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.*;

/**
 *
 * @author Chrille
 */
public class EarthInvasionView extends VBox {

    private final static int screenWidth = 640;
    private final static int screenHeight = 720 + 15;

    private Image image;
    private final GameModel model;
    private final EarthInvasionController controller;
    private GraphicsContext gc;
    private Canvas canvas;
    private EventHandler shipHandler;
    private Audio audio;

    public EarthInvasionView(GameModel model, Audio audio, FileHandler file) throws Exception {
        this.model = model;
        this.audio = audio;
        controller = new EarthInvasionController(model, this, audio, file); // skapa EarthInvasionController och model och view skicka som argument till EarthInvasionController

        //Creates the window, menu bar and so on
        initView();
        // Add all the event handlers
        addEventHandlers();
        // Start the graphics
        graphicsStart();

    }

    public void drawGameObjects(){

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

    public void graphicsStart() {
        gc = canvas.getGraphicsContext2D();
    }

    public void drawBackground() {
        gc.drawImage(image, 0, 0, getScreenWidth(), getScreenHeight());
    }

    public void drawInfo() {
        
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel", 20));
        gc.strokeText("Level: " + model.getLevelCounter(), 420, 20);
        gc.strokeText("Score: " + model.getScore(), 500, 20);
    }
    public void drawMessage(String message){
        gc.setStroke(Color.RED);
        
        gc.setFont(Font.font("LLPixel", FontWeight.BOLD, 20));
        gc.strokeText(message, 200, 400);
        
    }

    private void initView() {
        this.setPadding(new Insets(0, 0, 0, 0));
        image = new Image("resources/bg1.jpg");
        MenuBar menuBar = createMenu();
        this.getChildren().addAll(menuBar); // Creates the menu at the top.
        canvas = new Canvas(getScreenWidth(), getScreenHeight());
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        this.getChildren().addAll(canvas);
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("File");
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");
        
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem quitItem = new MenuItem("Quit");
        
        
        MenuItem highscoreItem = new MenuItem("Highscore");
        
        fileMenu.getItems().addAll(newGameItem, highscoreItem, new SeparatorMenuItem(), quitItem);
        
        CheckMenuItem bgMusic = new CheckMenuItem("Music");
        bgMusic.setSelected(true);
        bgMusic.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Audio.toggleBGMusic();
        });
        
        MenuItem sfxMusic = new MenuItem("Sound effects");
        sfxMusic.setDisable(true);
        
        Slider fxS = new Slider(0,1,Audio.getSoundEffectsVolume());
        fxS.setShowTickLabels(true);
        fxS.setShowTickMarks(true);
        
        fxS.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            oldValue = audio.getSoundEffectsVolume();
            audio.setSoundEffectsVolume(newValue.doubleValue());
        });
        
        
        CustomMenuItem sliderSfx = new CustomMenuItem(fxS);
        
        sliderSfx.setHideOnClick(false);
        
        Menu music = new Menu("Music");
        music.getItems().addAll(bgMusic,new SeparatorMenuItem(),sfxMusic,sliderSfx);
        
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
        
        rulesItem.setOnAction((ActionEvent event) -> {
            controller.handleRules(event);
        });
        highscoreItem.setOnAction((ActionEvent event) -> {
            try {
                controller.handleHighscore(event);
            } catch (Exception ex) {
                Logger.getLogger(EarthInvasionView.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }




}
