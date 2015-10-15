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
    private int score;
    
    public EarthInvasionModel() {
        
        objects = new ArrayList<GameObject>();
        score = 0;
        addPlayers(2);
        addAliens();
        addBlocks();
    }
    
    public void PlayerShot(int player){
        if(objects.get(player-1) instanceof Player){
            objects.add(new Shot(objects.get(player-1).getX()+29, objects.get(player-1).getY(), 7, 25, true));
        }
    }
    
    public void collision(){
        
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
    
    public int getScore(){
        return score;
    }
    
    public void moveAlien(){
        for (GameObject object : objects) {
            if(object instanceof Alien){
                ((Alien)object).moveAlien();
            }
        }
    }
    public void moveAlienDown(){
        for (GameObject object : objects) {
            if(object instanceof Alien){
                ((Alien)object).moveAlienDown();
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
    
    public void constrain(int index) {

        if(objects.get(index) instanceof Player){
            if(objects.get(index).getX() < 0){
                objects.get(index).setX(0);
            }else if(objects.get(index).getX() > 640 - objects.get(index).getWidth()+12){
                objects.get(index).setX(640-objects.get(index).getWidth()+12);
            }
        }
        
        else if(objects.get(index) instanceof Alien){
            if(objects.get(index).getX() < 0){
                objects.get(index).setX(0);
                moveAlienDown();
                ((Alien)objects.get(index)).setMovingRight(true);
            }else if(objects.get(index).getX() > 640 - objects.get(index).getWidth()){
                objects.get(index).setX(640 - objects.get(index).getWidth());
                moveAlienDown();
                ((Alien)objects.get(index)).setMovingRight(false);
            }else if (objects.get(index).getY() > 500 - objects.get(index).getHeight()){
                ((Alien)objects.get(index)).setAtBottom(true);
            }
                
        }
        
        else if(objects.get(index) instanceof Shot){
            if(objects.get(index).getY() <= 0){
                objects.remove(index);
                System.out.println("Removing shot");
            }else if(objects.get(index).getY() >= screenHeight - objects.get(index).getHeight()){
                objects.remove(index);
                System.out.println("Removing shot");
            }
        }
    }
    
    public void checkForCollisionWithObject(int index) {
        Boolean shotsExist = false;
        for(GameObject go: getObjects()){
            if(go instanceof Shot) shotsExist = true;
        }
        if(shotsExist && objects.get(index) instanceof Shot){
            for(int i=0; i<objects.size(); i++){
                if(objects.get(i) instanceof Block){
                    
                    if(objects.get(index).intersectsArea(objects.get(i).getX(), objects.get(i).getY(), objects.get(i).getWidth(), objects.get(i).getHeight())){
                        
                        System.out.println("Collision with brick");
                        ((Block)objects.get(i)).setHp(((Block)objects.get(i)).getHp() - ((Shot)objects.get(index)).getDamage());
                        
                        if(((Block)objects.get(i)).getHp() <= 0){  
                            System.out.println(objects.get(i).toString() + " removed");
                            objects.remove(i);
                            objects.remove(index-1);
                            i -= i;
                        }else {System.out.println(objects.get(index).toString() + " removed"); objects.remove(index); index-=1;}
                        break; 
                    }
                }else if(objects.get(i) instanceof Alien){
                    if(objects.get(index).intersectsArea(objects.get(i).getX(),objects.get(i).getY(),objects.get(i).getWidth(),objects.get(i).getHeight())){
                        
                        System.out.println("Collision with alien!");
                        score += 20;
                        objects.remove(i); //Remove the alien
                        i -=1; 
                        objects.remove(index-1); // remove the shot
                        index -=1;
                        break;
                    }
                }
            }
            
        }
        
    }
    
    
}
