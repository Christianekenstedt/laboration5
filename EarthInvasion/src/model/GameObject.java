/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import view.EarthInvasionView;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Gustaf
 */
abstract public class GameObject {
    
    private double x, y, width, height;
    
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    /*public boolean intersectArea(){
        
        if(x < width && x)
        
        
    }*/
    
    abstract public void drawObject(GraphicsContext gc);
    abstract public void constrain();
    
    
    
}