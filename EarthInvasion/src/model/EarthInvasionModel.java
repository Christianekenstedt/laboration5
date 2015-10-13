package model;

import java.util.ArrayList;


/**
 *
 * @author Chrille
 */
public class EarthInvasionModel {
    
    private int screenWidth = 640;
    private int screenHeight = 720+15;
    private ArrayList<GameObject> objects;
    
    public EarthInvasionModel() {
        
        objects = new ArrayList<GameObject>();
        
        addPlayers(2);
        addAliens();
        addBlocks();
    }
    
    public void PlayerShot(int player){
        if(objects.get(player-1) instanceof Player){
            objects.add(new Shot(objects.get(player-1).getX()+29, objects.get(player-1).getY(), 7, 25, true));
        }
    }

    public double getPlayerX(int player) {
        if(objects.get(player-1) instanceof Player){
            return objects.get(player-1).getX();
        }
        else return -1;
    }
    
    public double getPlayerY(int player) {
        if(objects.get(player-1) instanceof Player){
            return objects.get(player-1).getY();
        }
        else return -1;
    }
    
    private void addPlayers(int noOfPlayers){
        if(noOfPlayers == 2){
            objects.add(new Player(250, 620, 66.3, 66.3, 100, "resources/ship.png"));
            objects.add(new Player(250, 620, 66.3, 66.3, 100, "resources/ship2.png"));
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
    
    public void moveShot(){
        for(GameObject object: objects) {
            if(object instanceof Shot){
                ((Shot)object).moveShot(); 
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
