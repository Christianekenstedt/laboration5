/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Gustaf
 */
public class Player {
    
    private int hp,score;
    private double x;
    private double y;
    private double width, height;
    private Image image;
    private String path;
    
    private double velX=0;
    private double velY=0;
    
    public Player(int hp, int x, int y, String path){
        this.hp = hp;
        score = 0;
        this.x = x;
        this.y = y;
        width = 75;
        height = 150;
        this.path = path;
        
    } 
    
    public void tick() {
        x+=velX;
        y+=velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }
/*
    public void setVelY(double velY) {
        this.velY = velY;
    } 
  */  
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
    public int getScore(){
        return score;
    }
    public void drawPlayer(GraphicsContext gc) {
        image = new Image(path);
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("Calibri",20));
        gc.strokeText("Health: " + hp, 10, 20);
        gc.drawImage(image, x, y, height,width);
    }
}
