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
    //private ArrayList<Player> players;
    //private ArrayList<Block> blocks;
    //private ArrayList<Alien> aliens;
    
    private ArrayList<GameObject> objects;
    
    private Canvas canvas;

    
    public EarthInvasionModel() {
        //players = new ArrayList<Player>();
        //blocks = new ArrayList<Block>();
        //aliens = new ArrayList<Alien>();
        
        objects = new ArrayList<GameObject>();
        
        addPlayers(2);
        addAliens();
        addBlocks();
    }

    public double getPlayerX(int player) {
        //return players.get(player-1).getX();
        
        if(objects.get(player-1) instanceof Player){
            return objects.get(player-1).getX();
        }
        else return -1;
        
    }
    
    public double getPlayerY(int player) {
        //return players.get(player-1).getY();
        
        if(objects.get(player-1) instanceof Player){
            return objects.get(player-1).getY();
        }
        else return -1;
        
    }
    
    private void addPlayers(int noOfPlayers){
        if(noOfPlayers == 2){
            //players.add(new Player(100, 250, 620, "resources/ship.png"));
            //players.add(new Player(100, 250, 620, "resources/ship2.png"));
            
            objects.add(new Player(250, 620, 75, 150, 100, "resources/ship.png"));
            objects.add(new Player(250, 620, 75, 150, 100, "resources/ship2.png"));
            
        }else objects.add(new Player(250, 620, 75, 150, 100, "resources/ship.png"));
    }
    
    private void addAliens(){
        for(int i=0; i<5; i++){
            for(int j=0; j<8; j++){
                objects.add(new Alien((j*75), 60*(i+1), 50.0, 50.0));
                
            }
        }
        
    }
    
    private void addBlocks(){
        
        objects.add(new Block(56.65, 500.0, 100, 40));
        objects.add(new Block(269.95, 500.0, 100, 40));
        objects.add(new Block(483.25, 500.0, 100, 40));
        
    }
    
    public void setVelX(double velX, int player) {
        //players.get(player-1).setVelX(velX);
        
        if(objects.get(player-1) instanceof Player){
            ((Player)objects.get(player-1)).setVelX(velX);
        }
    }
    
    public void tick(int player) {
        
        if(objects.get(player-1) instanceof Player){
            ((Player)objects.get(player-1)).tick();
        }
    }
    
    public int getScreenHeight(){
        return screenHeight;
    }
    
    public int getScreenWidth(){
        return screenWidth;
    }
    
    
    public void moveAlien(){
        for (GameObject object : objects) {
            if(object instanceof Alien){
                ((Alien)object).moveAlien();
            }
        }
    }
    
    
    public ArrayList<GameObject> getObjects() {
        return (ArrayList)objects.clone();
    }
    
    /*
    public ArrayList<Player> getPlayer(){
        return (ArrayList)players.clone();
    }
    public ArrayList<Player> getBlocks(){
        return (ArrayList)blocks.clone();
    }
    public ArrayList<Alien> getAliens(){
        return (ArrayList)aliens.clone();
    }
    */
}
