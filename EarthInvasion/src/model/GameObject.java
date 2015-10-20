package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Gustaf
 */
abstract public class GameObject {

    private double x, y, width, height;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @param gc
     */
    abstract public void drawObject(GraphicsContext gc);

    /**
     *
     */
    abstract public void constrain();

    /**
     *
     * @param rectX
     * @param rectY
     * @param rectWidth
     * @param rectHeight
     * @return
     */
    public boolean intersectsArea(double rectX, double rectY, double rectWidth, double rectHeight) {

        Rectangle2D object = new Rectangle2D(getX(), getY(), getWidth(), getHeight());
        Rectangle2D otherObject = new Rectangle2D(rectX, rectY, rectWidth, rectHeight);

        return object.intersects(otherObject);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String info;
        info = this.getClass().toString();
        return info;
    }
}
