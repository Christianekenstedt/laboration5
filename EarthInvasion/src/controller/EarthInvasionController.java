package controller;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import model.*;
import view.AlertWindow;
import view.Audio;
import view.EarthInvasionView;
import view.RulesWindow;

/**
 *
 * @author Chrille
 */
public class EarthInvasionController {
    private AnimationTimer timer;
    private final GameModel model;
    private final EarthInvasionView view;
    private final Audio audio;

    public EarthInvasionController(GameModel model, EarthInvasionView view, Audio audio){
        this.model = model;
        this.view = view;
        this.audio = audio;

        gameLoop();

    }

    public void gameLoop(){
        timer = new GameLoop();
        timer.start();
    }
    public void setTimerStop() {
        timer.stop();
    }
    public void setTimerStart() {
        timer.start();
    }

    public void playerMovement(int rightOrLeft, int index){
        //1 = left, 2 = right, 3 = false left, 4 = false right
        if(rightOrLeft == 1){
            ((Player) getPlayer().get(index - 1)).setLeft(true);
        }else if(rightOrLeft == 2){
            ((Player) getPlayer().get(index - 1)).setRight(true);
        }else if(rightOrLeft == 3){
            ((Player) getPlayer().get(index - 1)).setLeft(false);
        }else if(rightOrLeft == 4){
            ((Player) getPlayer().get(index - 1)).setRight(false);
        }

    }

    public void checkForCollision() {
        collisionWithObjects();
        moveAlienDown();
    }

    public void movePlayers() {
        if(model.getNoOfPlayers() == 1){
            ((Player) getPlayer().get(0)).tick();
        }else if (model.getNoOfPlayers() == 2){
            ((Player) getPlayer().get(0)).tick();
            ((Player) getPlayer().get(1)).tick();
        }

    }
    
    public void checkIfShootPlayer(){
        int counter = 0;

        for(int i = 0; i < getAlien().size(); i++){
            for(int j = 0; j < getAlien().size(); j++){
                if(getAlien().get(i).getX() == getAlien().get(j).getX()){ // Kollar om getAlien() i har samma x-varde som getAlien() j
                    // for i so fall so ligger bada pa samma kolummn.
                    // Da maste vi kolla om getAlien() j har starre y-varde, om den har de sa far inte getAlien() i skjuta.
                    //Ifall den inte har de maste vi kolla om tills arrayen ar slut av aliens, och den absolut sista getAlien() for skjuta. for varje rad.
                    if (getAlien().get(i).getY() < getAlien().get(j).getY()){ // Om getAlien() i:s, y-varde ar mindre an getAlien() j:s, da betyder det att getAlien() i, ej far skjuta.
                        counter = j ;                               // Da sparar vi undan tillfalligt den getAlien() som finns langst ner far varje kolummn.
                    }
                }
            }
            alienShot(counter);

        }

    }

    public void alienShot(int index){

        //ticker ++;
        //if(ticker == 100){
            //getShot().add(new Shot(getAlien().get(index).getX() + (getAlien().get(index).getWidth() / 2), getAlien().get(index).getY() + getAlien().get(index).getHeight(), 7, 25, false));
        //}
       // if (ticker > 1000) ticker = 0;
    }

    public void moveAlien() {

        for (GameObject object : getAlien()) {
            ((Alien) object).moveAlien();
        }
    }

    public void moveAlienDown() {

        for (GameObject object : getAlien()) {
            if (((Alien) object).hasConstrained()) {
                ((Alien) object).moveAlienDown();
            }
        }
        for (GameObject object : getAlien()) { // FUL LoSNING SOM FAN MEN VET EJ HUR ANNARS?!
            ((Alien) object).setConstrained();
        }
    }

    public void moveShot() {

        for (GameObject object : getShot()) {
            ((Shot) object).moveShot();
        }
    }

    public void checkForConstrain() {

        for (GameObject p : getPlayer()) {
            p.constrain();
        }
        for (GameObject a : getAlien()) {
            a.constrain();
        }
        for (int i = 0; i < getShot().size(); i++) {

            getShot().get(i).constrain();
            if (!((Shot) getShot().get(i)).isInFrame()) {
                model.removeObject(getShot().get(i));
                i -= 1;

            }
        }

    }

    public void collisionWithObjects() {
        shotCheckCollisionWithAlien();
        shotCheckCollisionWithBlock();
        shotCheckCollisionWithPlayer();
    }

    private void shotCheckCollisionWithPlayer() {
        for (int i = 0; i < getShot().size(); i++) {
            for (int j = 0; j < getPlayer().size(); j++) {
                if(!((Shot)getShot().get(i)).isFiredFromPlayer()){
                    if (getShot().get(i).intersectsArea(getPlayer().get(j).getX(), getPlayer().get(j).getY(), getPlayer().get(j).getWidth(), getPlayer().get(j).getHeight())) {
                        ((Player) getPlayer().get(j)).setHp(((Player) getPlayer().get(j)).getHp() - ((Shot) getShot().get(i)).getDamage());

                        if (((Player) getPlayer().get(j)).getHp() <= 0) {
                            //model.removeObject(getPlayer().get(j)); //TAGIT BORT ATT PLAYERN FoRSVINNER ATM
                            model.removeObject(getShot().get(i));

                            //j--;
                        } else {
                            model.removeObject(getShot().get(i));
                        }
                        i--;
                        break;
                    }
                }
            }
        }
    }
    
    public void shotCheckCollisionWithAlien() {
        for (int i = 0; i < getShot().size(); i++) {
            for (int j = 0; j < getAlien().size(); j++) {
                if ( ((Shot)getShot().get(i)).isFiredFromPlayer() && getShot().get(i).intersectsArea(getAlien().get(j).getX(), getAlien().get(j).getY(), getAlien().get(j).getWidth(), getAlien().get(j).getHeight())) {
                    //getShot().remove(getShot().get(i));
                    model.removeObject(getShot().get(i));
                    i--;
                    model.setScore(model.getScore()+20);
                    audio.alienKilled();
                    //getAlien().remove(getAlien().get(j));
                    model.removeObject(getAlien().get(j));
                    j--;
                    break;
                }
            }
        }

    }

    public void shotCheckCollisionWithBlock() {

        for (int i = 0; i < getShot().size(); i++) {

            for (int j = 0; j < getBlock().size(); j++) {

                if (getShot().get(i).intersectsArea(getBlock().get(j).getX(), getBlock().get(j).getY(), getBlock().get(j).getWidth(), getBlock().get(j).getHeight())) {

                    ((Block) getBlock().get(j)).setHp(((Block) getBlock().get(j)).getHp() - ((Shot) getShot().get(i)).getDamage());

                    if (((Block) getBlock().get(j)).getHp() <= 0) {
                        model.removeObject(getBlock().get(j));
                        //getBlock().remove(getBlock().get(j));
                        model.removeObject(getShot().get(i));
                        j--;
                    } else {
                        model.removeObject(getShot().get(i));
                        //getShot().remove(getShot().get(i));
                    }
                    i--;
                    break;
                }

            }

        }

    }
    

    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                playerMovement(1, 1);
                break;
            case RIGHT:
                playerMovement(2, 1);
                break;
            case COMMA:
                model.PlayerShot(1);
                Audio.playBullet();
                break;
            case A:
                if(model.getNoOfPlayers()==2){
                    playerMovement(1, 2);
                }
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    playerMovement(2, 2);
                }
                
                break;
            case SPACE:
                if(model.getNoOfPlayers()>1){
                    model.PlayerShot(2);
                    Audio.playBullet();
                }
                
                break;
            case V:
                alienShot(1);
                break;
            default:
            
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                playerMovement(3, 1);
                break;
            case RIGHT:
                playerMovement(4, 1);
                break;
            case COMMA:
                break;
            case A:
                if(model.getNoOfPlayers()>1){
                    playerMovement(3, 2);
                }
                
                break;
            case D:
                if(model.getNoOfPlayers()>1){
                    playerMovement(4, 2);
                }
                
                break;
            case SPACE:

                break;
            default:
            
        }
    }

    public void handleNewGame(ActionEvent event) {
        System.out.println("START NEW GAME!!");
    }

    public void handleHighscore(ActionEvent event) {
        System.out.println("HIGHSCORE!");
    }

    public void handleRules(ActionEvent event) {
        System.out.println("RULES!!");
        RulesWindow rules = new RulesWindow();
        setTimerStop();
        view.setFrostEffect(10, 3);
        rules.showWindow();
        setTimerStart();
        view.setFrostEffect(0, 0);
    }

    public void handleQuit(ActionEvent event) {
        AlertWindow alert = new AlertWindow();
        setTimerStop();
        view.setFrostEffect(10, 3);
        alert.showWindow();
        setTimerStart();
        view.setFrostEffect(0, 0);

    }
    
    public void handlePause(ActionEvent event) {
        setTimerStop();
        view.setFrostEffect(10, 3);
        
    }

    public void handleResume(ActionEvent event) {
        setTimerStart();
        view.setFrostEffect(0, 0);
    }
    
    public void handleSaveItem(ActionEvent event) {
        System.out.println("SAVE");
    }

    public ArrayList<GameObject> getPlayer() {
        return (ArrayList) model.getPlayer();
    }

    public ArrayList<GameObject> getAlien() {
        return (ArrayList) model.getAlien();
    }

    public ArrayList<GameObject> getShot() {
        return (ArrayList) model.getShot();
    }

    public ArrayList<GameObject> getBlock() {
        return (ArrayList) model.getBlock();
    }

    protected class GameLoop extends AnimationTimer {

        private long previousNs = 0;

        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            // paint the background
            view.drawBackground();
            // paint info and objects
            view.drawInfo();
            view.drawGameObjects();
            
            movePlayers();
            moveAlien();
            moveShot();
            checkForConstrain();
            checkForCollision();
            checkIfShootPlayer();


        }
    }
}