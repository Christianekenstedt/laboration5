package model;

import java.util.ArrayList;

/**
 * Created by Chrille on 2015-10-20.
 */
public class GameModel {

    private ArrayList<GameObject> player;
    private ArrayList<GameObject> alien;
    private ArrayList<GameObject> block;
    private ArrayList<GameObject> shot;

    private int score;
    private int ticker;
    private int noOfPlayers;
    private int levelCounter;
    private int playerReloadCounter;

    /**
     *
     * @param noOfPlayers
     */
    public GameModel(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        init(this.noOfPlayers);
    }
    
    public GameObject getPlayerSpecific(int index){
        return player.get(index-1);
    }

    private void init(int noOfPlayers) {
        player = new ArrayList<>();
        alien = new ArrayList<>();
        block = new ArrayList<>();
        shot = new ArrayList<>();
        ticker = 0;
        score = 0;
        playerReloadCounter = 0;
        levelCounter = 1;
        // CHANGE THIS TO ADD NR OF PLAYERS! MAXIMUM 2
        addPlayers(noOfPlayers);
        addAliens();
        addBlocks();
    }
    public void restartGame(){
        Player.setPlayerNo(0);
        player.clear();
        init(getNoOfPlayers());
    }
    public void newLevel(){
        Player.setPlayerNo(0);
        levelCounter++;
        shot.clear();
        alien.clear();
        block.clear();
        player.clear();
        addPlayers(noOfPlayers);
        addAliens();
        addBlocks();
        //addPlayers(2);
        Alien.setVelocity(Alien.getVelocity()*levelCounter);
    }
    
    /**
     *
     * @param index
     */
    public void PlayerShot(int index) {
        if(((Player) player.get(index-1)).canFire()){
            shot.add(new Shot(player.get(index - 1).getX() + 29, player.get(index - 1).getY(), 7, 25, true));
            ((Player) player.get(index-1)).setCanFire(false);
            ((Player) player.get(index-1)).reloadCounter(0);
        }
        
    }
    
    
    public boolean alienShot(int index) {
        
        ticker ++;    
        if(ticker == 10){
            shot.add(new Shot(getAlien().get(index).getX() + (getAlien().get(index).getWidth() / 2), getAlien().get(index).getY() + getAlien().get(index).getHeight(), 7, 25, false));
            return true;
        }
         if (ticker > 300/(1+(levelCounter/10)) ) ticker = 0;
        return false;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public int getLevelCounter() {
        return levelCounter;
    }

    public void setLevelCounter(int levelCounter) {
        this.levelCounter = levelCounter;
    }
    
    /**
     * Returns the number of players.
     *
     * @return noOfPlayers
     */
    public int getNoOfPlayers() {
        return noOfPlayers;
    }
    
    private void addPlayers(int noOfPlayers) {
        if (noOfPlayers == 2) {
            player.add(new Player(260, 650, 66.3, 66.3, 100, "resources/ship.png"));
            player.add(new Player(360, 650, 66.3, 66.3, 100, "resources/ship2.png"));
        } else {
            player.add(new Player(250, 650, 66.3, 66.3, 100, "resources/ship.png"));
        }
    }

    private void addAliens() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                alien.add(new Alien((j * 75), 60 * (i + 1), 50.0, 50.0));

            }
        }
    }

    private void addBlocks() {
        block.add(new Block(56.65, 530.0, 100, 40));
        block.add(new Block(269.95, 530.0, 100, 40));
        block.add(new Block(483.25, 530.0, 100, 40));
    }

    /**
     *
     * @param o
     */
    public void removeObject(GameObject o) {
        if (o instanceof Shot) {
            this.shot.remove(o);
        } else if (o instanceof Alien) {
            this.alien.remove(o);
            if(alien.isEmpty()){
                newLevel();
            }
        } else if (o instanceof Block) {
            this.block.remove(o);
        }else if (o instanceof Player) {
            //this.player.remove(o);
            for(int i=0; i<player.size(); i++){
                if(player.get(i) == o){
                    System.out.println("Player: "+i+" is dead!");
                    ((Player) player.get(i)).setDead(true);
                    ((Player) player.get(i)).setY(1000);
                }
            }
        }
    }
    
    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }
    
    public int getUniqePlayerNo(int playerNo){
        return ((Player)player.get(getNoOfPlayers()-playerNo)).getUniqeNo();
    }
    /**
     *
     * @return
     */
    public ArrayList<GameObject> getPlayer() {
        return (ArrayList) player.clone();
    }

    /**
     *
     * @return
     */
    public ArrayList<GameObject> getAlien() {
        return (ArrayList) alien.clone();
    }

    /**
     *
     * @return
     */
    public ArrayList<GameObject> getShot() {
        return (ArrayList) shot.clone();
    }

    /**
     *
     * @return
     */
    public ArrayList<GameObject> getBlock() {
        return (ArrayList) block.clone();
    }
}
