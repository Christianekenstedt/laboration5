/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 *
 * @author Chrille
 */
public class Block {
    private int hp;
    private double x;
    private double y;
    private int width;
    private int height;
    private ArrayList<Image> images;
    
    public Block(double x,double y){
        images = new ArrayList<Image>();
        hp = 100;
        loadImages();
        this.x = x;
        this.y = y;
        width = 100;
        height = 40;
        
    }
    
    private void loadImages(){
        Image temp = new Image("resources/block.png");
        PixelReader reader = temp.getPixelReader();
        images.add(new WritableImage(reader, 0, 0, 100, 40));
        images.add(new WritableImage(reader, 100, 0, 100, 40));
        images.add(new WritableImage(reader, 200, 0, 100, 40));
    }
    
    public void drawBlock(GraphicsContext gc){
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFont(new Font("Calibri",20));
        gc.strokeText("Health: " + hp, 10, 20);
        
        if(hp > 75){
            gc.drawImage(images.get(0), x, y);
        }else if (hp > 76 && hp > 25){
            gc.drawImage(images.get(1), x, y);
        }else if(hp < 26 && hp > 0){
            gc.drawImage(images.get(2), x, y);
        }else System.out.println("dead");
    }
}
