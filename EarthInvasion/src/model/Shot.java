package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represent a shot which can be fired from a player or an alien.
 *
 * @author Gustaf Holmström and Christian Ekenstedt
 */
public class Shot extends GameObject {

    private Image image;
    private boolean firedFromPlayer;
    private int damage;
    private boolean inFrame;

    /**
     * The constructor creates the shot when called and inits the data with data
     * from all parameters.
     *
     * @param x, the x coordiante
     * @param y, the y cooirdinate
     * @param width, the width of the shot
     * @param height, the height of the shot
     * @param firedFromPlayer, whether the shot is fired from player or not.
     */
    public Shot(double x, double y, double width, double height, boolean firedFromPlayer) {
        super(x, y, width, height);
        this.damage = 30;
        this.firedFromPlayer = firedFromPlayer;
        this.inFrame = true;
        loadImage();
        
        
    }

    /**
     * Returns if the shot is in frame or not.
     *
     * @return true/false.
     */
    public boolean isInFrame() {
        return inFrame;
    }

    /**
     * Sets if shot is in frame (true) or not (false).
     *
     * @param inFrame, if in frame.
     */
    public void setInFrame(boolean inFrame) {
        this.inFrame = inFrame;
    }

    /**
     * Returns the damage for the shot.
     *
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage for the shot.
     *
     * @param damage, the damage to be set.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * This method moves the shot with a set speed.
     */
    public void moveShot() {
        if (firedFromPlayer) {
            setY(getY() - 2);
        } else {
            setY(getY() + 2);
        }
    }

    /**
     * Returns whether the shot is fired from the player or not.
     *
     * @return true if the shot is fired from player, else false.
     */
    public boolean isFiredFromPlayer() {
        return firedFromPlayer;
    }

    /**
     * Load image of the shot.
     */
    private void loadImage() {
        if (firedFromPlayer) {
            image = new Image("resources/shot.png");
        } else {
            image = new Image("resources/shot2.png");
        }
    }

    /**
     * Draw the object with the GraphicsContext gc.
     *
     * @param gc, the GraphicsContext.
     */
    @Override
    public void drawObject(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY(), getWidth(), getHeight());

    }

    /**
     * Checks if inside the specific area.
     */
    @Override
    public void constrain() {
        if (getY() <= 0) {
            setInFrame(false);
        } else if (getY() >= 720 + 15 - getHeight()) {
            setInFrame(false);
        }
    }

}
