package model;

import view.Audio;
import java.util.ArrayList;

/**
 * Created by Chrille on 2015-10-20.
 */
public class GameModel {



    private  ArrayList<GameObject> player;
    private  ArrayList<GameObject> alien;
    private  ArrayList<GameObject> block;
    private  ArrayList<GameObject> shot;

    private int score;
    int ticker;
    private int noOfPlayers;


    public GameModel(int noOfPlayers){
        this.noOfPlayers = noOfPlayers;
        init();
    }


    private void init(){
        player = new ArrayList<>();
        alien = new ArrayList<>();
        block = new ArrayList<>();
        shot = new ArrayList<>();
        ticker = 0;
        score = 0;
        // CHANGE THIS TO ADD NR OF PLAYERS! MAXIMUM 2
        addPlayers(noOfPlayers);
        addAliens();
        addBlocks();
    }
    public void PlayerShot(int index) {

        shot.add(new Shot(player.get(index - 1).getX() + 29, player.get(index - 1).getY(), 7, 25, true));
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    private void addPlayers(int noOfPlayers) {
        if (noOfPlayers == 2) {

            player.add(new Player(260, 620, 66.3, 66.3, 100, "resources/ship.png"));
            player.add(new Player(360, 620, 66.3, 66.3, 100, "resources/ship2.png"));
        } else {
            player.add(new Player(250, 620, 66.3, 66.3, 100, "resources/ship.png"));
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
        block.add(new Block(56.65, 500.0, 100, 40));
        block.add(new Block(269.95, 500.0, 100, 40));
        block.add(new Block(483.25, 500.0, 100, 40));
    }

    public void removeObject(GameObject o){
        if(o instanceof Shot){
            this.shot.remove(o);
        }else if (o instanceof Alien){
            this.alien.remove(o);
        }else if (o instanceof Block){
            this.block.remove(o);
        }else if (o instanceof Player){
            this.player.remove(o);
        }
    }

    public int getScore() {
        return score;
    }

    public ArrayList<GameObject> getPlayer() {
        return (ArrayList) player.clone();
    }

    public ArrayList<GameObject> getAlien() {
        return (ArrayList) alien.clone();
    }

    public ArrayList<GameObject> getShot() {
        return (ArrayList) shot.clone();
    }

    public ArrayList<GameObject> getBlock() {
        return (ArrayList) block.clone();
    }
}
