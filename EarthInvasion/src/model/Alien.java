/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
