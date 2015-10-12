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
        for(int i = 0; i < 8; i++){
            if(i == 0){
                aliens.add(new Alien(56.0*(i+1),40));
                aliens.add(new Alien(56.0*(i+1),40*2+20));
                aliens.add(new Alien(56.0*(i+1),40*3+20*2));
                aliens.add(new Alien(56.0*(i+1),40*4+20*3));
                aliens.add(new Alien(56.0*(i+1),40*5+20*4));
            }else if (i == 1){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if(i == 2){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if(i == 3){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if(i == 4){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if (i == 5){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if (i == 6){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }else if (i == 7){
                aliens.add(new Alien(56.0*(i+1)+8*i,40));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*2+20));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*3+20*2));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*4+20*3));
                aliens.add(new Alien(56.0*(i+1)+8*i,40*5+20*4));
            }
            
        }
        
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
