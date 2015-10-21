package view;

import model.FileHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private FileHandler highscoreList;
    
    public HighscoreView() throws Exception{
        highscoreList = new FileHandler();
        highStage = new Stage();
        highPane = new BorderPane();
        scene = new Scene(highPane, 300, 300);
        highPane.setPadding(new Insets(10, 10, 10, 10));
        
        initHighscoreWindow(highscoreList.read());
    }
    
    private void initHighscoreWindow(String highscoreString) {
        Label title = new Label("Highscores");
        title.setFont(Font.font("Helvetica",FontWeight.BOLD, 20));
        title.setUnderline(true);
        Label border = new Label("\nName\t\tLevel\tScore\n");
        border.setFont(Font.font("Helvetica",FontWeight.BOLD,15));
        Label info = new Label(highscoreString);
        info.setFont(Font.font("Helvetica", 15));
        
        Button okButton = new Button("Thanks, now i will try to beat that!");
        okButton.autosize();
        
        HBox buttons = new HBox(5);
        buttons.getChildren().add(okButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        
        VBox texts = new VBox(5);
        texts.getChildren().addAll(border,info);
        
        
        
        
        
        highPane.setBottom(buttons);
        highPane.setTop(title);
        highPane.setCenter(texts);
        highPane.setAlignment(title,Pos.TOP_CENTER);

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
        scene.setFill(Color.WHITESMOKE);
        highStage.setScene(scene);
        highStage.setResizable(false);
        highStage.setAlwaysOnTop(false);
        highStage.toFront();
        highStage.initStyle(StageStyle.UNDECORATED);
        

        highStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
}
