package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represent a player. It extends from GameObject with two abstract
 * methods.
 *
 * @author Gustaf Holmström and Christian Ekenstedt
 */
public class Player extends GameObject {

    private int hp;
    private int reloadCounter;
    private static int playerNo = 0;
    private int uniqeNo;
    
    private Image image;
    private final String path;
    
    private double velX;
    private double velY;
    
    private boolean left;
    private boolean right;
    private boolean canFire;
    private boolean dead;

    /**
     * The constructor takes x, y, width, height, hp and the path to the image.
     * It inits all the data for the player with the given parameters.
     *
     * @param x, the x-coordinate to be set.
     * @param y, the y-cooridnate to be set.
     * @param width, the width of the player.
     * @param height, the height of the player.
     * @param hp, the hp to be given to the player.
     * @param path, the path of the image.
     */
    public Player(double x, double y, double width, double height, int hp, String path) {
        super(x, y, width, height);
        this.hp = hp;
        this.path = path;
        this.left = false;
        this.right = false;
        dead = false;
        canFire = true;
        reloadCounter = 0;
        uniqeNo = playerNo;
        velX = 4;
        velY = 0;
        
        loadImage();
    }

    /**
     *
     * @return the value of reloadCounter.
     */
    public int getReloadCounter() {
        return reloadCounter;
    }

    /**
     *
     * @param reloadCounter, sets the reloadCounter.
     */
    public void reloadCounter(int reloadCounter) {
        this.reloadCounter = reloadCounter;
    }

    /**
     *
     * @return true/false, if the player can shot.
     */
    public boolean canFire() {
        return canFire;
    }

    /**
     *
     * @param canFire, if the player can shot.
     */
    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    /**
     *
     * @return true/false if the player is dead.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @param dead, sets the player if dead or not.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Loads the image with the given path.
     */
    private void loadImage() {
        image = new Image(path);
    }

    /**
     *
     * @return the player number.
     */
    public static int getPlayerNo() {
        return playerNo;
    }

    /**
     * Sets the player number.
     *
     * @param playerNo, the player number to be set.
     */
    public static void setPlayerNo(int playerNo) {
        Player.playerNo = playerNo;
    }

    /**
     * return a boolean value showing if player object is moving left or not
     * @return true/false if left.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * sets variable left to true or false
     * @param left, true/false if left.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * return a boolean value showing if player object is moving right or not
     * @return true/fals if right.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * sets variable left to true or false
     * @param right, fyll i.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Depending on the variables left and right the
     * X value in GameObject will change accordingly
     */
    public void tick() {

        if (left && !right) {
            super.setX(getX() - velX);
        } else if (right && !left) {
            super.setX(getX() + velX);
        } else if (right && left) {
            super.setX(getX());
        }
    }

    /**
     * Sets the velocity for x.
     *
     * @param velX, the velocity to be set for x.
     */
    public void setVelX(double velX) {
        this.velX = velX;
    }

    /**
     * Returns the current health points.
     *
     * @return hp.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the amount of health points.
     *
     * @param hp, the amount to be set.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the uniqe player number.
     *
     * @return the uniqe player number.
     */
    public int getUniqeNo() {
        return uniqeNo;
    }

    /**
     * Draw the player and its current hp.
     *
     * @param gc, the GraphicsContext.
     */
    @Override
    public void drawObject(GraphicsContext gc) {

        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel", 20));
        if (getUniqeNo() == 0) {
            gc.strokeText("P" + (getUniqeNo() + 1) + " Health: " + getHp(), 10, 20);
        } else {
            gc.strokeText("P" + (getUniqeNo() + 1) + " Health: " + getHp(), 10, 45);
        }
        if (!dead) {
            gc.drawImage(image, getX(), getY(), getHeight(), getWidth());
        }

    }

    /**
     * This method keeps the player within the canvas.
     */
    @Override
    public void constrain() {
        if (getX() < 0) {
            setX(0);
        } else if (getX() > 640 - getWidth()) {
            setX(640 - getWidth());
        }
    }
}
