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
public class Block {
    private int hp;
    private double x;
    private double y;
    private double width;
    private double height;
    private ArrayList<Image> images;
    
    public Block(double x,double y){
        images = new ArrayList<Image>();
        hp = 100;
        loadImages();
        this.x = x;
        this.y = y;
        
    }
    
    private void loadImages(){
        Image temp = new Image("resources/block.png");
        images.add(temp);
    }
    
    public void drawBlock(GraphicsContext gc){
        
        gc.drawImage(images.get(0), x, y);
    }
}
