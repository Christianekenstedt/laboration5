package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Gustaf
 */
public class Player extends GameObject{
    
    private int hp;
    private Image image;
    private final String path;
    
    private double velX;
    private double velY;
    
    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param hp
     * @param path 
     */
    public Player(double x, double y, double width, double height, int hp, String path){
        super(x, y, width, height);
        this.hp = hp;
        this.path = path;
        velX = 0;
        velY = 0;
        
    } 
    
    public void tick() {
        super.setX(getX() + velX);
        super.setY(getY() + velY);
        //x+=velX;
        //y+=velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }
/*
    public void setVelY(double velY) {
        this.velY = velY;
    } 
  */  
    /*public int getScore(){
        return score;
    }*/
    @Override
    public void drawObject(GraphicsContext gc) {
        image = new Image(path);
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("LLPixel",20));
        gc.strokeText("Health: " + hp, 10, 20);
        gc.drawImage(image, getX(), getY(), getHeight(),getWidth());
    }

    @Override
    public void constrain() {
        if(getX() < 0){
            setX(0);
            
        }else if(getX()> 640 - getWidth()){
            setX(640-getWidth());
        }
    }
}
