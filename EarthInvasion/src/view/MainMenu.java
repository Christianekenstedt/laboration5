/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Christian
 */
public class MainMenu {

    private ImageView imageView;
    private Scene scene;
    private AnchorPane aPane;
    private ImageView logoImage;
    private Button onePlayerButton;
    private Button twoPlayerButton;
    private static int noOfPlayers;
    private final Stage menuStage;
    private final Audio a;
    
    public MainMenu(Audio a) {
        this.a = a;
        menuStage = new Stage();
        aPane = new AnchorPane();
        scene = new Scene(aPane, 640, 720);
        aPane.setPadding(new Insets(10, 10, 10, 10));
        initMainMenu();
        showWindow();
    }


    public void initMainMenu() {
        //a.toggleBGMusic();
        Image bg = new Image("resources/EarthInvasion.gif",650,750,false,false);
        Image logo = new Image("resources/earthInvasionLogo.png",400,100,true,true);
        imageView = new ImageView(bg);
        logoImage = new ImageView(logo);
        onePlayerButton = new Button("1 Player ");
        onePlayerButton.setOnAction((ActionEvent event) -> {
            onePlayerButtonHandler(event);
        });
        twoPlayerButton = new Button("2 Players");
        twoPlayerButton.setOnAction((ActionEvent event) -> {
            twoPlayerButtonHandler(event);
        });
        onePlayerButton.setLayoutX(294);
        onePlayerButton.setLayoutY(335);
        twoPlayerButton.setLayoutX(294);
        twoPlayerButton.setLayoutY(370);
        logoImage.setX(120);
        logoImage.setY(210);
        aPane.getChildren().addAll(imageView,logoImage,onePlayerButton,twoPlayerButton);
       
    }    

    private void onePlayerButtonHandler(ActionEvent event) {
        noOfPlayers = 1;
        menuStage.close();
    }

    private void twoPlayerButtonHandler(ActionEvent event) {
        noOfPlayers = 2;
        menuStage.close();
    }
    
    public void showWindow() {
        menuStage.setTitle("Warning!");
        menuStage.setScene(scene);
        menuStage.setResizable(false);
        menuStage.setAlwaysOnTop(false);
        menuStage.toFront();
        menuStage.initStyle(StageStyle.UNDECORATED);

        menuStage.showAndWait();
        //Här borde vi lägga någon typ av return
    }
    
    public static int getNoOfPlayers(){
        return noOfPlayers;
    }
}
