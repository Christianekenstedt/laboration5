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
    private int x;
    private int y;
    private Image image;
    
    public Player(int hp, int x, int y){
        this.hp = hp;
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX() {
        x+=1;
    }
    
    public void setY(int y) {
        y+=1;
    }
    
    public void playerDraw(GraphicsContext gc) {
        image = new Image("resources/ship.png");
        gc.drawImage(image, x, y);
    }
}
