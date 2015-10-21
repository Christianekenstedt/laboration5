package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Chrille
 */
public class HighscoreView {
    private Stage highStage;
    private BorderPane highPane;
    private Scene scene;
    
    public HighscoreView(){
        highStage = new Stage();
        highPane = new BorderPane();
        scene = new Scene(highPane, 400, 400);
        highPane.setPadding(new Insets(10, 10, 10, 10));
        initHighscoreWindow();
    }
    
    private void initHighscoreWindow() {
        Label title = new Label("Highscores");
        Label info = new Label("highscores will be here");
        
        Button okButton = new Button("Thanks, now i will try to beat that!");
        okButton.autosize();
        HBox buttons = new HBox(5);
        title.setCenterShape(true);
        
        buttons.getChildren().add(okButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        highPane.setBottom(buttons);
        
        highPane.setTop(title);
        highPane.setCenter(info);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ropa på spara funktioner osv..
                highStage.close();
            }

        });

    }

    public void showWindow() {
        highStage.setTitle("Rules!");
        highStage.setScene(scene);
        highStage.setResizable(false);
        highStage.setAlwaysOnTop(false);
        highStage.toFront();
        highStage.initStyle(StageStyle.UNDECORATED);

        highStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
}
