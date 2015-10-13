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
    private static double velocity;
    private static boolean movingRight;
    
    public Alien(double x, double y, double width, double height){
        super(x, y, width, height);
        loadImage();
        hp = 1; // hur mkt hp?
        velocity = 0.2;
        movingRight = true;
        
    }
   
    public void moveAlien(){
        if(movingRight){
            velocity = 0.2;
        }else velocity = -0.2;
        
       setX(getX()+velocity);
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
        if(getX() < 0){
            setX(0);
            
            movingRight = true;
        }else if(getX()> 640 - getWidth()){
            setX(640-getWidth());
            
            movingRight = false;
        }
    }
}
