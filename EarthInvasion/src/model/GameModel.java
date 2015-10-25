package model;

import java.util.ArrayList;

/**
 * This class ....
 * @author Christian Ekenstedt and Gustaf Holmström.
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
     * @param noOfPlayers, the number of player.
     */
    public GameModel(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        init(this.noOfPlayers);
    }
    /**
     * 
     * @param index, the player to return.
     * @return the specific player.
     */
    public GameObject getPlayerSpecific(int index) {
        return player.get(index - 1);
    }
    /**
     * Inits the game.
     * Clearing all arrays then,
     * creating new players, aliens, blocks.
     * @param noOfPlayers, the number of players to be created.
     */
    private void init(int noOfPlayers) {
        player = new ArrayList<>();
        alien = new ArrayList<>();
        block = new ArrayList<>();
        shot = new ArrayList<>();
        score = 0;
        playerReloadCounter = 0;
        levelCounter = 1;

        addPlayers(noOfPlayers);
        addAliens();
        addBlocks();
    }
    
    
    /**
     * Restarts the game.
     */
    public void restartGame() {
        Player.setPlayerNo(0);
        player.clear();
        init(getNoOfPlayers());
    }
    /**
     * Starts a new level.
     */
    public void newLevel() {
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
        Alien.setVelocity(Alien.getVelocity() * levelCounter);
    }

    /**
     * Makes the player to shoot, given by the parameter.
     * @param index, the player to shoot.
     */
    public void PlayerShot(int index) {
        if (((Player) player.get(index - 1)).canFire()) {
            shot.add(new Shot(player.get(index - 1).getX() + 29, player.get(index - 1).getY(), 7, 25, true));
            ((Player) player.get(index - 1)).setCanFire(false);
            ((Player) player.get(index - 1)).reloadCounter(0);
        }

    }
    /**
     * Makes the alien given by the parameter to shoot.
     * @param index, the alien to shot.
     * @return true/false if the alien shots.
     */
    public boolean alienShot(int index) {
       
        if(((Alien) alien.get(index)).canFire()){
            shot.add(new Shot(getAlien().get(index).getX() + (getAlien().get(index).getWidth() / 2), getAlien().get(index).getY() + getAlien().get(index).getHeight(), 7, 25, false));
            ((Alien) alien.get(index)).setCanFire(false);
            ((Alien) alien.get(index)).reloadCounter(0);
            return true;
        }
        return false;
    }

    /**
     * Sets the score of the current session.
     * @param score, sets the score.
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * Return the current level.
     * @return levelCounter, the current level.
     */
    public int getLevelCounter() {
        return levelCounter;
    }

    /**
     * Set the current level.
     * @param levelCounter, sets the current level.
     */
    public void setLevelCounter(int levelCounter) {
        this.levelCounter = levelCounter;
    }

    /**
     * Returns the number of players.
     *
     * @return noOfPlayers, the current numbers of players.
     */
    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    /**
     * Adds players to the game
     *
     * @param noOfPlayers, the number of players to be added.
     */
    private void addPlayers(int noOfPlayers) {
        if (noOfPlayers == 2) {
            player.add(new Player(260, 650, 66.3, 66.3, 100, "resources/ship.png"));
            player.add(new Player(360, 650, 66.3, 66.3, 100, "resources/ship2.png"));
        } else {
            player.add(new Player(250, 650, 66.3, 66.3, 100, "resources/ship.png"));
        }
    }

    /**
     * Adds aliens the the game.
     */
    private void addAliens() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                alien.add(new Alien((j * 75), 60 * (i + 1), 50.0, 50.0));

            }
        }
    }

    /**
     * Adds block to the game.
     */
    private void addBlocks() {
        block.add(new Block(56.65, 530.0, 100, 40));
        block.add(new Block(269.95, 530.0, 100, 40));
        block.add(new Block(483.25, 530.0, 100, 40));
    }

    /**
     *
     * @param o, remves the given object.
     */
    public void removeObject(GameObject o) {
        if (o instanceof Shot) {
            this.shot.remove(o);
        } else if (o instanceof Alien) {
            this.alien.remove(o);
            if (alien.isEmpty()) {
                newLevel();
            }
        } else if (o instanceof Block) {
            this.block.remove(o);
        } else if (o instanceof Player) {
            //this.player.remove(o);
            for (int i = 0; i < player.size(); i++) {
                if (player.get(i) == o) {
                    System.out.println("Player: " + i + " is dead!");
                    ((Player) player.get(i)).setDead(true);
                    ((Player) player.get(i)).setY(1000);
                }
            }
        }
    }

    /**
     * Returns the score for the current session.
     *
     * @return the current score for the session.
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param playerNo, the player to get the return from.
     * @return returns the uniqe number of the given player.
     */
    public int getUniqePlayerNo(int playerNo) {
        return ((Player) player.get(getNoOfPlayers() - playerNo)).getUniqeNo();
    }

    /**
     *
     * @return a reference to all the players.
     */
    public ArrayList<GameObject> getPlayer() {
        return (ArrayList) player.clone();
    }

    /**
     *
     * @return a reference to all the aliens.
     */
    public ArrayList<GameObject> getAlien() {
        return (ArrayList) alien.clone();
    }

    /**
     *
     * @return a reference to all the shots.
     */
    public ArrayList<GameObject> getShot() {
        return (ArrayList) shot.clone();
    }

    /**
     *
     * @return an reference to all the blocks.
     */
    public ArrayList<GameObject> getBlock() {
        return (ArrayList) block.clone();
    }
}
