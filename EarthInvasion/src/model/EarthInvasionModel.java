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
    private ArrayList<Player> players;
    private ArrayList<Block> blocks;
    
    private Canvas canvas;

    
    public EarthInvasionModel() {
        players = new ArrayList<Player>();
        blocks = new ArrayList<Block>();
        players.add(new Player(100, 250, 620, "resources/ship.png"));
        blocks.add(new Block(100.0,100.0));
    }

    public double getPlayerX() {
        return players.get(0).getX();
    }
    
    public double getPlayerY() {
        return players.get(0).getY();
    }
    
    public void setPlayerX(double x) {
        players.get(0).setX(x);
    }
    
    
    
    public int getScreenHeight(){
        return screenHeight;
    }
    
    public int getScreenWidth(){
        return screenWidth;
    }   
    
    public ArrayList<Player> getPlayer(){
        return (ArrayList)players.clone();
    }
    public ArrayList<Player> getBlocks(){
        return (ArrayList)blocks.clone();
    }

}
