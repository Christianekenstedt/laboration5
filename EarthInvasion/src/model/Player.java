package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Gustaf
 */
public class Player extends GameObject {

    private int hp;
    private Image image;
    private final String path;
    private static int playerNo = 0;
    private int uniqeNo;
    private double velX;
    private double velY;
    private boolean left;
    private boolean right;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param hp
     * @param path
     */
    public Player(double x, double y, double width, double height, int hp, String path) {
        super(x, y, width, height);
        this.hp = hp;
        this.path = path;
        velX = 4;
        velY = 0;

        this.left = false;
        this.right = false;

        uniqeNo = playerNo;
        System.out.println("created player: " + playerNo++);

    }

    /**
     *
     * @return
     */
    public boolean isLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     *
     * @return
     */
    public boolean isRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     *
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
     *
     * @param velX
     */
    public void setVelX(double velX) {
        this.velX = velX;
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     *
     * @return
     */
    public int getUniqeNo() {
        return uniqeNo;
    }

    /**
     *
     * @param gc
     */
    @Override
    public void drawObject(GraphicsContext gc) {

        image = new Image(path);
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel", 20));
        if (getUniqeNo() == 0) {
            gc.strokeText("P" + (getUniqeNo() + 1) + " Health: " + getHp(), 10, 20);
        } else {
            gc.strokeText("P" + (getUniqeNo() + 1) + " Health: " + getHp(), 10, 45);
        }

        gc.drawImage(image, getX(), getY(), getHeight(), getWidth());
    }

    /**
     *
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
