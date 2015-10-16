package model;

import java.util.ArrayList;

/**
 *
 * @author Chrille
 */
public class EarthInvasionModel {

    private final int screenWidth = 640;
    private final int screenHeight = 720 + 15;

    private final ArrayList<GameObject> player;
    private final ArrayList<GameObject> alien;
    private final ArrayList<GameObject> block;
    private final ArrayList<GameObject> shot;

    private int score;

    public EarthInvasionModel() {

        player = new ArrayList<GameObject>();
        alien = new ArrayList<GameObject>();
        block = new ArrayList<GameObject>();
        shot = new ArrayList<GameObject>();

        score = 0;
        addPlayers(2);
        addAliens();
        addBlocks();
    }

    public void PlayerShot(int index) {

        shot.add(new Shot(player.get(index - 1).getX() + 29, player.get(index - 1).getY(), 7, 25, true));
    }

    public double getPlayerX(int index) {

        return player.get(index - 1).getX();
    }

    public double getPlayerY(int index) {

        return player.get(index - 1).getY();
    }

    private void addPlayers(int noOfPlayers) {
        if (noOfPlayers == 2) {

            player.add(new Player(250, 620, 66.3, 66.3, 100, "resources/ship.png"));
            player.add(new Player(250, 620, 66.3, 66.3, 100, "resources/ship2.png"));
        } else {
            player.add(new Player(250, 620, 75, 150, 100, "resources/ship.png"));
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

    public void setVelX(double velX, int index) {
        ((Player) player.get(index - 1)).setVelX(velX);
    }

    public void tick(int index) {
        ((Player) player.get(index - 1)).tick();
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScore() {
        return score;
    }
    
    public void alienShot(int index){
        shot.add(new Shot(alien.get(index - 1).getX()-10, alien.get(index - 1).getY()-10, 7, 25, false));
    }
    
    public void moveAlien() {

        for (GameObject object : alien) {
            ((Alien) object).moveAlien();
        }
    }

    public void moveAlienDown() {

        for (GameObject object : alien) {
            if (((Alien) object).hasConstrained()) {
                ((Alien) object).moveAlienDown();
            }
        }
        for (GameObject object : alien) { // FUL LÖSNING SOM FAN MEN VET EJ HUR ANNARS?!
            ((Alien) object).setConstrained();
        }
    }

    public void moveShot() {

        for (GameObject object : shot) {
            ((Shot) object).moveShot();
        }
    }

    public void constrain() {

        for (GameObject p : player) {
            p.constrain();
        }
        for (GameObject a : alien) {
            a.constrain();
        }
        for (int i = 0; i < shot.size(); i++) {

            shot.get(i).constrain();
            if (((Shot) shot.get(i)).isInFrame() == false) {
                shot.remove(shot.get(i));
                i -= 1;

            }
        }

    }

    public void collisionWithObjects() {
        shotCheckCollisionWithAlien();
        shotCheckCollisionWithBlock();
    }

    public void shotCheckCollisionWithAlien() {
        for (int i = 0; i < shot.size(); i++) {
            for (int j = 0; j < alien.size(); j++) {
                if (shot.get(i).intersectsArea(alien.get(j).getX(), alien.get(j).getY(), alien.get(j).getWidth(), alien.get(j).getHeight())) {
                    shot.remove(shot.get(i));
                    i--;
                    score += 20;
                    alien.remove(alien.get(j));
                    j--;
                    break;
                }
            }
        }

    }

    public void shotCheckCollisionWithBlock() {

        for (int i = 0; i < shot.size(); i++) {

            for (int j = 0; j < block.size(); j++) {

                if (shot.get(i).intersectsArea(block.get(j).getX(), block.get(j).getY(), block.get(j).getWidth(), block.get(j).getHeight())) {
                    ((Block) block.get(j)).setHp(((Block) block.get(j)).getHp() - ((Shot) shot.get(i)).getDamage());

                    if (((Block) block.get(j)).getHp() <= 0) {
                        block.remove(block.get(j));
                        shot.remove(i);
                        j--;
                    } else {
                        shot.remove(shot.get(i));
                    }
                    i--;
                    break;
                }

            }

        }

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
