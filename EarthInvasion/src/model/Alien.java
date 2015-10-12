package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Chrille
 */
public class Alien {
    private int hp;
    private double x;
    private double y;
    private int width;
    private int height;
    private Image image;
    
    public Alien(double x, double y){
        loadImage();
        hp = 1; // hur mkt hp?
        this.x = x;
        this.y = y;
        width = 50;
        height = 50;
    }
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX() {
        
        this.x += 0.2;
    }
    
    public void setY(int y) {
        y+=1.0;
    }
    public double getWidth(){
        return (int)width;
    }
    public double getHeight(){
        return (int)height;
    }
    
    private void loadImage(){
        image = new Image("resources/alien.png");
    }
    
    public void drawAlien(GraphicsContext gc){
        gc.drawImage(image, x, y, width,height);
    }
}
