package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Chrille
 */
public class Alien extends GameObject{
    private int hp;
    private Image image;
    
    public Alien(double x, double y, double width, double height){
        super(x, y, width, height);
        loadImage();
        hp = 1; // hur mkt hp?
        
    }
   
    public void moveAlien(){
       setX(getX()+0.2);
    }
    
    private void loadImage(){
        image = new Image("resources/alien.png");
    }
    
    @Override
    public void drawObject(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void constrain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
