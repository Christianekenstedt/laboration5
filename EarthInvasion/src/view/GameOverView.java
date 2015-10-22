package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controller.FileHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.GameModel;

/**
 *
 * @author Christian
 */
public class GameOverView {

    private Stage stage;
    private VBox pane;
    private Scene scene;
    private FileHandler file;
    private GameModel model;
    private int score;
    private final int level;

    public GameOverView(GameModel model, FileHandler file) {
        this.model = model;
        this.file = file;
        stage = new Stage();
        pane = new VBox(10);
        scene = new Scene(pane, 230, 150);
        pane.setPadding(new Insets(10, 10, 10, 10));
        score = model.getScore();
        level = model.getLevelCounter();
        initRulesWindow();
    }

    private void initRulesWindow() {
        HBox box = new HBox(5);
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        gameOverText.setFill(Color.RED);
        
        Label label = new Label("Name: ");
        TextField text = new TextField();
        text.setPromptText("minimum 4 characters");
        text.deselect();
        Button saveButton = new Button("Save");
        saveButton.requestFocus();
        saveButton.setAlignment(Pos.BOTTOM_CENTER);
        
        box.getChildren().addAll(label, text);
        pane.getChildren().addAll(gameOverText,box, saveButton);
        pane.setAlignment(Pos.CENTER);
        
        saveButton.setOnAction(event->{
            if(text.getLength()>4 && !text.getText().contains(" ")){
              try {
                file.write(text.getText()+" "+ level +" " + score);
                } catch (Exception ex) {
                    Logger.getLogger(GameOverView.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("File save don't work");
                }
                stage.close();  
            }
            
        });
        
        
    }

    public void showWindow() {
        
        
        stage.setTitle("Game Over...");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(false);
        stage.toFront();
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
        
    }
}
