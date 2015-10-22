package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Chrille
 */
public class Alien extends GameObject {

    private int hp, animationTimer;
    private Image image, image2;
    private static double velocity;
    private static boolean movingRight, atBottom, constrained;
    private int reloadCounter;
    private boolean canFire;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
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
     *
     * @return
     */
    public boolean isAtBottom() {
        return atBottom;
    }

    /**
     *
     * @param atBottom
     */
    public void setAtBottom(boolean atBottom) {
        Alien.atBottom = atBottom;
    }

    /**
     *
     * @return
     */
    public static double getVelocity() {
        return velocity;
    }

    /**
     *
     * @param velocity
     */
    public static void setVelocity(double velocity) {
        Alien.velocity = velocity;
    }

    /**
     *
     * @return
     */
    public static boolean isMovingRight() {
        return movingRight;
    }

    /**
     *
     * @param movingRight
     */
    public void setMovingRight(boolean movingRight) {
        Alien.movingRight = movingRight;
    }

    /**
     *
     */
    public void moveAlien() {
        if (movingRight) {
            setX(getX() + getVelocity());
            //velocity = 0.4;
        } else {
            setX(getX() + (getVelocity()*-1));
            //velocity = -0.4;
        }

        
    }

    /**
     *
     */
    public void moveAlienDown() {
        setY(getY() + 3);
    }

    private void loadImage() {
        image = new Image("resources/alien.png");
        image2 = new Image("resources/alien2.png");
    }

    /**
     *
     * @param gc
     */
    @Override
    public void drawObject(GraphicsContext gc) {
        animationTimer++;
        if(animationTimer<=50){
            gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
        }else if(animationTimer>50 && animationTimer <= 100){
            gc.drawImage(image2, getX(), getY(), getWidth(), getHeight());
        }else if(animationTimer>100) {
            gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
            animationTimer = 0;
        }
        
    }

    /**
     *
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
     *
     * @return
     */
    public boolean hasConstrained() {
        return constrained;
    }

    /**
     *
     */
    public void setConstrained() {
        constrained = false;
    }
}
