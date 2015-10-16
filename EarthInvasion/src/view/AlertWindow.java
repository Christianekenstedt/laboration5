/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.animation.AnimationTimer;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import view.EarthInvasionView;

/**
 *
 * @author Christian
 */
public class AlertWindow {

    private Stage alertStage;
    private BorderPane alertPane;
    private Scene scene;

    public AlertWindow() {
        alertStage = new Stage();
        alertPane = new BorderPane();
        scene = new Scene(alertPane, 300, 100);
        alertPane.setPadding(new Insets(10, 10, 10, 10));
        initAlertWindow();
    }

    private void initAlertWindow() {
        Label label = new Label("Are you sure you want to exit?");
        Button okButton = new Button("Yes");
        okButton.autosize();
        Button cancelButton = new Button("No");
        cancelButton.autosize();
        HBox buttons = new HBox(5);

        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);
        alertPane.setBottom(buttons);
        alertPane.setCenter(label);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Ropa på spara funktioner osv..
                System.exit(0);
            }

        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alertStage.close();
            }

        });

    }

    public void showWindow() {
        alertStage.setTitle("Warning!");
        alertStage.setScene(scene);
        alertStage.setResizable(false);
        alertStage.setAlwaysOnTop(false);
        alertStage.toFront();
        alertStage.initStyle(StageStyle.UNDECORATED);

        alertStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
}
