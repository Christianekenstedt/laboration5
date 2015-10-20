package model;
import javafx.geometry.Rectangle2D;
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

    public double getHeight() {
        return height;
    }

    abstract public void drawObject(GraphicsContext gc);
    abstract public void constrain();
    
    public boolean intersectsArea(double rectX, double rectY, double rectWidth, double rectHeight){
        
        Rectangle2D object = new Rectangle2D(getX(),getY(),getWidth(),getHeight());
        Rectangle2D otherObject = new Rectangle2D(rectX,rectY,rectWidth,rectHeight);
        
        return object.intersects(otherObject);
    }
     
    @Override
    public String toString(){
        String info;
        info = this.getClass().toString();
        return info;
    }
}
