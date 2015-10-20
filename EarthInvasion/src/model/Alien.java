package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Chrille
 */
public class Alien extends GameObject {

    private int hp;
    private Image image;
    private static double velocity;
    private static boolean movingRight, atBottom, constrained;

    public Alien(double x, double y, double width, double height) {
        super(x, y, width, height);
        loadImage();
        hp = 1; // hur mkt hp?
        movingRight = true;
        atBottom = false;
        constrained = false;

    }

    public boolean isAtBottom() {
        return atBottom;
    }

    public void setAtBottom(boolean atBottom) {
        Alien.atBottom = atBottom;
    }

    public static double getVelocity() {
        return velocity;
    }

    public static void setVelocity(double velocity) {
        Alien.velocity = velocity;
    }

    public static boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        Alien.movingRight = movingRight;
    }

    public void moveAlien() {
        if (movingRight) {
            velocity = 0.4;
        } else {
            velocity = -0.4;
        }

        setX(getX() + getVelocity());
    }

    public void moveAlienDown() {
        setY(getY() + 2);
    }

    private void loadImage() {
        image = new Image("resources/alien.png");
    }

    @Override
    public void drawObject(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void constrain() {

        if (getX() < 0) {
            setX(0);
            constrained = true;
            //moveAlienDown();
            setMovingRight(true);
        } else if (getX() > 640 - getWidth()) {
            setX(640 - getWidth());
            constrained = true;
            //moveAlienDown();
            setMovingRight(false);
        } else if (getY() > 500 - getHeight()) {
            setAtBottom(true);
        }
    }

    public boolean hasConstrained() {
        return constrained;
    }

    public void setConstrained() {
        constrained = false;
    }
}
