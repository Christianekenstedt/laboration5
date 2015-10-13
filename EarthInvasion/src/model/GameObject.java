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
    //abstract public void constrain();
    
    public boolean intersectsArea(double rectX, double rectY, double rectWidth, double rectHeight) {

        // Find the closest point to the circle's center within 
        // the rectangle
        double closestX = clamp(x, rectX, rectX + rectWidth);
        double closestY = clamp(y, rectY, rectY + rectHeight);

        // Calculate the distance between the circle's center and the
        // rectangles closest point
        double distanceX = x - closestX;
        double distanceY = y - closestY;

        // If the distance is less than the circle's radius, an 
        // intersection occurs (Pythagoras theorem)
        return (distanceX * distanceX) + (distanceY * distanceY)
                < (width * width);
    }
    
    private double clamp(double value, double lower, double upper) {
        if (value < lower) {
            return lower;
        }
        if (value > upper) {
            return upper;
        }
        return value;
    }
    
}
