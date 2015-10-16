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
        scene = new Scene(rulesPane, 300, 100);
        rulesPane.setPadding(new Insets(10, 10, 10, 10));
        initRulesWindow();
    }

    private void initRulesWindow() {
        Label label = new Label("Rules and rules and rules....");
        Button okButton = new Button("Yes, i understand!");
        okButton.autosize();

        HBox buttons = new HBox(5);

        buttons.getChildren().add(okButton);
        buttons.setAlignment(Pos.CENTER);
        rulesPane.setBottom(buttons);
        rulesPane.setCenter(label);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ropa på spara funktioner osv..
                rulesStage.close();
            }

        });

    }

    public void showWindow() {
        rulesStage.setTitle("Warning!");
        rulesStage.setScene(scene);
        rulesStage.setResizable(false);
        rulesStage.setAlwaysOnTop(false);
        rulesStage.toFront();
        rulesStage.initStyle(StageStyle.UNDECORATED);

        rulesStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
}
