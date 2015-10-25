package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class represent a object that can be renderd in the game, a Player,
 * Alien, Block or a shot.
 *
 * @author Gustaf Holmström and Christian Ekenstedt
 */
abstract public class GameObject {

    private double x, y, width, height;

    /**
     * The constructor creates the and intis the data with the given paramets.
     * 
     * @param x, the y-coordinate to be given to the GameObject.
     * @param y, the x-coordinate to be given to the GameObject.
     * @param width, the width to be set for the GameObject.
     * @param height, the height to be set for the GameObject.
     */
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the x-coordinate of the GameObject.
     *
     * @return the x-coordinate of the GameObject.
     */
    public double getX() {
        return x;
    }

    /**
     * Return the x-coordinate of the GameObject.
     * @param x sets the x-coordinate of the GameObject.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Return the y-coordinate of the GameObject.
     * @return the y-coordinate of the GameObject.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets value of y.
     * @param y sets the y-coordinate of the GameObject.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Return width of the GameObject.
     * @return the width of the GameObject.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Return height of the GameObject.
     * @return the height of the GameObject.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Draw the object.
     * @param gc, the GraphicsContext
     */
    abstract public void drawObject(GraphicsContext gc);

    /**
     * Checks if the object is within a specific area.
     */
    abstract public void constrain();

    /**
     * Checks if the object is intersecting with another object given by the parameters.
     * @param rectX, the x-coordinate for the rect to be checked for intersect.
     * @param rectY, the y-coordinate for the rect to be checked for intersect.
     * @param rectWidth, the width for the rect to be checked for intersect.
     * @param rectHeight, the height for the rect to be checked for intersect.
     * @return true/false if intersects.
     */
    public boolean intersectsArea(double rectX, double rectY, double rectWidth, double rectHeight) {

        Rectangle2D object = new Rectangle2D(getX(), getY(), getWidth(), getHeight());
        Rectangle2D otherObject = new Rectangle2D(rectX, rectY, rectWidth, rectHeight);

        return object.intersects(otherObject);
    }

    /**
     * Returns a string of the 
     * @return a string.
     */
    @Override
    public String toString() {
        String info;
        info = this.getClass().toString();
        return info;
    }
}
