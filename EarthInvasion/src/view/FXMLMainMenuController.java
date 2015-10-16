/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import earthinvasion.EarthInvasion;

/**
 * FXML Controller class
 *
 * @author Chrille
 */
public class FXMLMainMenuController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane aPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button onePlayerButton;
    @FXML
    private Button twoPlayerButton;
    private static int noOfPlayers;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    private void onePlayerButtonHandler(ActionEvent event) {
        noOfPlayers = 1;
        System.out.println(noOfPlayers);
        
        
    }

    @FXML
    private void twoPlayerButtonHandler(ActionEvent event) {
        noOfPlayers = 2;
        System.out.println(noOfPlayers);
        
    }

    public static int getNoOfPlayers() {
        return noOfPlayers;
    }

    public static void setNoOfPlayers(int noOfPlayers) {
        FXMLMainMenuController.noOfPlayers = noOfPlayers;
    }
    
}
