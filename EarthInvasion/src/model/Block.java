package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Chrille
 */
public class Block extends GameObject{
    private int hp;
    private ArrayList<Image> images;
    
    public Block(double x,double y, double width, double height){
        super(x, y, width, height);
        images = new ArrayList<Image>();
        hp = 250;
        loadImages();
 
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
    
    
    
    private void loadImages(){
        Image temp = new Image("resources/newBlock.png");
        PixelReader reader = temp.getPixelReader();
        images.add(new WritableImage(reader, 0, 0, 100, 40));
        images.add(new WritableImage(reader, 100, 0, 100, 40));
        images.add(new WritableImage(reader, 200, 0, 100, 40));
    }
    
    @Override
    public void drawObject(GraphicsContext gc) {
        if(hp > 166){
            gc.drawImage(images.get(0), getX(), getY());
        }else if (hp < 167 && hp > 83){
            gc.drawImage(images.get(1), getX(), getY());
        }else if(hp < 84 && hp > 0){
            gc.drawImage(images.get(2), getX(), getY());
        }else System.out.println("dead");
    }

    

}
