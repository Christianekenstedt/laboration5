package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * This class represent a block that exists in the world to cover from shots.
 * This class extends from GameObject with two abstract methods.
 *
 * @author Christian Ekenstedt and Gustaf Holmström.
 */
public class Block extends GameObject {

    private int hp;
    private final ArrayList<Image> images;

    /**
     * The constructor creates the block with the given parameters.
     *
     * @param x, the x-coordinate for the block.
     * @param y, the y-coordinate for the block.
     * @param width, the width to be set for the block.
     * @param height, the height to be set for the block.
     */
    public Block(double x, double y, double width, double height) {
        super(x, y, width, height);
        images = new ArrayList<>();
        hp = 250;
        loadImages();

    }

    /**
     * Sets the health points for the block with the amount given by the
     * parameter.
     *
     * @param hp, the hp to be set.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the current health points.
     *
     * @return the current health points.
     */
    public int getHp() {
        return hp;
    }

    /**
     * This method load all the image of the block (Sprite). 
     */
    private void loadImages() {
        Image temp = new Image("resources/newBlock.png");
        PixelReader reader = temp.getPixelReader();
        images.add(new WritableImage(reader, 0, 0, 100, 40));
        images.add(new WritableImage(reader, 100, 0, 100, 40));
        images.add(new WritableImage(reader, 200, 0, 100, 40));
    }

    /**
     * This method draws the object on the GraphicsContext.
     * @param gc, the GraphicsContext.
     */
    @Override
    public void drawObject(GraphicsContext gc) {
        if (hp > 166) {
            gc.drawImage(images.get(0), getX(), getY());
        } else if (hp < 167 && hp > 83) {
            gc.drawImage(images.get(1), getX(), getY());
        } else if (hp < 84 && hp > 0) {
            gc.drawImage(images.get(2), getX(), getY());
        }
    }

    /**
     * This method constrains the block, within the canvas.
     */
    @Override
    public void constrain() {
        throw new UnsupportedOperationException("Block aint got no constrain!"); //To change body of generated methods, choose Tools | Templates.
    }
}
