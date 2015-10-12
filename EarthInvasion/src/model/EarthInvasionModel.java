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
    private ArrayList<Alien> aliens;
    
    private Canvas canvas;

    
    public EarthInvasionModel() {
        players = new ArrayList<Player>();
        blocks = new ArrayList<Block>();
        aliens = new ArrayList<Alien>();
        addPlayers(1);
        addAliens();
        addBlocks();
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
    private void addPlayers(int noOfPlayers){
        if(noOfPlayers == 2){
            players.add(new Player(100, 250, 620, "resources/ship.png"));
            players.add(new Player(100, 250, 620, "resources/ship2.png"));
        }else players.add(new Player(100, 250, 620, "resources/ship.png"));
    }
    
    private void addAliens(){
        aliens.add(new Alien(56.0,40));
    }
    
    private void addBlocks(){
        blocks.add(new Block(56.65,500.0));
        blocks.add(new Block(269.95,500.0));
        blocks.add(new Block(483.25,500.0));
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
    public ArrayList<Alien> getAliens(){
        return (ArrayList)aliens.clone();
    }

}
