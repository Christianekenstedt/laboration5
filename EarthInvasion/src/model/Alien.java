package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represent an alien and extends GameObject.
 *
 * @author Christian Ekenstedt and Gustaf Holmström
 */
public class Alien extends GameObject {

    private int hp, animationTimer;
    private Image image, image2;
    private static double velocity;
    private static boolean movingRight, atBottom, constrained;
    private int reloadCounter;
    private boolean canFire;

    /**
     * The constructor takes x,y,widht and height and inits the alien.
     *
     * @param x, the x-coordinate for the alien.
     * @param y, the y-coordinate for the alien.
     * @param width, the width to be set for the alien.
     * @param height, the height to be set for the alien.
     */
    public Alien(double x, double y, double width, double height) {
        super(x, y, width, height);
        loadImage();
        hp = 1; // hur mkt hp?
        movingRight = true;
        atBottom = false;
        constrained = false;
        setVelocity(0.2);
        animationTimer = 0;
        
        canFire=true;
        reloadCounter = 0;

    }
    
    public int getReloadCounter() {
        return reloadCounter;
    }

    public void reloadCounter(int reloadCounter) {
        this.reloadCounter = reloadCounter;
    }
    
    public boolean canFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    /**
     * Returns an boolean if this alien is at bottom.
     *
     * @return true/false if at bottom.
     */
    public boolean isAtBottom() {
        return atBottom;
    }

    /**
     * Sets true/false to atBottom.
     *
     * @param atBottom
     */
    public void setAtBottom(boolean atBottom) {
        Alien.atBottom = atBottom;
    }

    /**
     * Returns the velocity of the alien.
     *
     * @return velocity
     */
    public static double getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the alien.
     *
     * @param velocity, the velocity to be set.
     */
    public static void setVelocity(double velocity) {
        Alien.velocity = velocity;
    }

    /**
     * Returns a boolean if the alien is moving right.
     *
     * @return true/false
     */
    public static boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Set true/false if the alien is moving right.
     *
     * @param movingRight
     */
    public void setMovingRight(boolean movingRight) {
        Alien.movingRight = movingRight;
    }

    /**
     * This method moves the alien.
     */
    public void moveAlien() {
        if (movingRight) {
            setX(getX() + getVelocity());
            //velocity = 0.4;
        } else {
            setX(getX() + (getVelocity() * -1));
            //velocity = -0.4;
        }

    }

    /**
     * This method moves the alien down.
     */
    public void moveAlienDown() {
        setY(getY() + 3);
    }

    /**
     * This methid loads the images for the alien.
     */
    private void loadImage() {
        image = new Image("resources/alien.png");
        image2 = new Image("resources/alien2.png");
    }

    /**
     * This method overrides from GameObjects and draws the alien at screen.
     *
     * @param gc, the GraphicsContext to drawn at.
     */
    @Override
    public void drawObject(GraphicsContext gc) {
        animationTimer++;
        if (animationTimer <= 50) {
            gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
        } else if (animationTimer > 50 && animationTimer <= 100) {
            gc.drawImage(image2, getX(), getY(), getWidth(), getHeight());
        } else if (animationTimer > 100) {
            gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
            animationTimer = 0;
        }

    }

    /**
     * This method keeps the alien inside the canvas.
     */
    @Override
    public void constrain() {

        if (getX() < 0) {
            constrained = true;
            setMovingRight(true);
        } else if (getX() > 640 - getWidth()) {
            constrained = true;
            setMovingRight(false);
        } else if (getY() > 540 - getHeight()) {
            setAtBottom(true);
        }
    }

    /**
     * Returns a boolean if the alien has constrained.
     *
     * @return if the alien constrianed.
     */
    public boolean hasConstrained() {
        return constrained;
    }

    /**
     * This method set false to constrianed.
     */
    public void setConstrained() {
        constrained = false;
    }
}
