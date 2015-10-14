/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Gustaf
 */
public class Shot extends GameObject {
    
    private Image image;
    private boolean firedFromPlayer;
    private int damage;
    
    public Shot(double x, double y, double width, double height, boolean firedFromPlayer){
        super(x, y, width, height);
        this.damage = 30;
        loadImage();
        this.firedFromPlayer = firedFromPlayer;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    
    
    public void moveShot(){
        if(firedFromPlayer == true) {
            setY(getY()-2);
        }
    }
    
    private void loadImage(){
        image = new Image("resources/shot.png");
    }
    @Override
    public void drawObject(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY(), getWidth(), getHeight());
    }

    
}
