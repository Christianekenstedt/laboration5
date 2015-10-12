/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Gustaf
 */
public class Player {
    
    private int hp;
    private double x;
    private double y;
    private Image image;
    
    public Player(int hp, int x, int y){
        this.hp = hp;
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX(double x) {
        //x += 1.0;
        this.x = x;
    }
    
    public void setY(int y) {
        y+=1.0;
    }
    
    public void playerDraw(GraphicsContext gc) {
        image = new Image("resources/ship.png");
        gc.drawImage(image, x, y);
    }
}
