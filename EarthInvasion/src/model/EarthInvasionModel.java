package model;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.EarthInvasionView;


/**
 *
 * @author Chrille
 */
public class EarthInvasionModel {
    
    private int screenWidth = 640;
    private int screenHeight = 720;

    private AnimationTimer timer;
    private Player player;
    
    private Canvas canvas;

    
    public EarthInvasionModel() {
        player = new Player(100, 210, 600);
    }

    public double getPlayerX() {
        return player.getX();
    }
    
    public double getPlayerY() {
        return player.getY();
    }
    
    public void setPlayerX(double x) {
        player.setX(x);
    }
    
    
    
    public int getScreenHeight(){
        return screenHeight;
    }
    
    public int getScreenWidth(){
        return screenWidth;
    }   


}
