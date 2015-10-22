/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Christian
 */
public class RulesWindow {

    private Stage rulesStage;
    private BorderPane rulesPane;
    private Scene scene;

    public RulesWindow() {
        rulesStage = new Stage();
        rulesPane = new BorderPane();
        scene = new Scene(rulesPane, 400, 400);
        rulesPane.setPadding(new Insets(10, 10, 10, 10));
        initRulesWindow();
    }

    private void initRulesWindow() {
        Label title = new Label("Rules for Earth Invasion!");
        title.setFont(Font.font("Helvetica",FontWeight.BOLD, 20));
        Label info = new Label("Singel player:\n'←' to go left\n '→' go right\n',' (COMMA) to shoot\n\nMultiplayer:\n\nPlayer 2:\n'A' to go left\n'D' to go right\n'SPACE' to shoot\n\n Game rules:\n\n Shot down the aliens before they kill you or they reach your shields.");
        
        Button okButton = new Button("Yes, i understand!");
        okButton.autosize();
        HBox buttons = new HBox(5);
        title.setCenterShape(true);
        
        buttons.getChildren().add(okButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        rulesPane.setBottom(buttons);
        
        rulesPane.setTop(title);
        rulesPane.setCenter(info);
        rulesPane.setAlignment(title,Pos.TOP_CENTER);
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ropa på spara funktioner osv..
                rulesStage.close();
            }

        });

    }

    public void showWindow() {
        rulesStage.setTitle("Rules!");
        rulesStage.setScene(scene);
        rulesStage.setResizable(false);
        rulesStage.setAlwaysOnTop(false);
        rulesStage.toFront();
        rulesStage.initStyle(StageStyle.UNDECORATED);

        rulesStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
}
