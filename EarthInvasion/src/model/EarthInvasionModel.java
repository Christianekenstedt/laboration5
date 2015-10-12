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
    private int screenHeight = 720+15;

    private AnimationTimer timer;
    private ArrayList<Player> players;
    private ArrayList<Block> blocks;
    private ArrayList<Alien> aliens;
    
    private Canvas canvas;

    
    public EarthInvasionModel() {
        players = new ArrayList<Player>();
        blocks = new ArrayList<Block>();
        aliens = new ArrayList<Alien>();
        addPlayers(2);
        addAliens();
        addBlocks();
    }

    public double getPlayerX(int player) {
        return players.get(player-1).getX();
    }
    
    public double getPlayerY(int player) {
        return players.get(player-1).getY();
    }
    
    public void setPlayerX(double x,int player) {
        players.get(player-1).setX(x);
    }
    private void addPlayers(int noOfPlayers){
        if(noOfPlayers == 2){
            players.add(new Player(100, 250, 620, "resources/ship.png"));
            players.add(new Player(100, 250, 620, "resources/ship2.png"));
        }else players.add(new Player(100, 250, 620, "resources/ship.png"));
    }
    
    private void addAliens(){
        for(int i=0; i<5; i++){
            for(int j=0; j<8; j++){
                aliens.add(new Alien((j*75), 60*(i+1)));
            }
        }
        
    }
    
    private void addBlocks(){
        blocks.add(new Block(56.65,500.0));
        blocks.add(new Block(269.95,500.0));
        blocks.add(new Block(483.25,500.0));
    }
    
    public void setVelX(double velX, int player) {
        players.get(player-1).setVelX(velX);
    }
    
    public void tick(int player) {
        players.get(player-1).tick();
    }
    
    public int getScreenHeight(){
        return screenHeight;
    }
    
    public int getScreenWidth(){
        return screenWidth;
    }
    
    public void move(){
        for(Alien a: getAliens()){
            a.setX();
        }
    }
    
    public int getScore(){
        return players.get(0).getScore();
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
