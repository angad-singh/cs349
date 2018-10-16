
import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.math.*;
import java.awt.geom.*;
import javax.swing.JPanel;

public class Model extends Observable {
    private int type;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int currTool;
    // private int

    /** The observers that are watching this model for changes. */
    private ArrayList<Observer> observers;
    private ArrayList<Drawable> ShapeList = new ArrayList<Drawable>();

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    public void setType(int i) {
        this.type = i;
    }

    public void addShape(Drawable shape) {
        this.ShapeList.add(shape);
        // notifyObservers();
    }

    public void setStartPos(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void setCurrTool(int i) {
        this.currTool = i;
    }

    public int getCurrTool() {
        return this.currTool;
    }

    public void setEndPos(int x, int y) {
        this.endX = x;
        this.endY = y;
    }

    public boolean rectHitTest(Point current, Drawable shapeItem) {
        return ((current.x <= shapeItem.x + shapeItem.width) && (current.x >= shapeItem.x)
                && (current.y <= shapeItem.y + shapeItem.height) && (current.y >= shapeItem.y));S
    }

    public boolean circleHitTest(Point current, Drawable shapeItem) {
        Point center = new Point(shapeItem.x, shapeItem.y);
        double radius = (int) Math.hypot(Math.abs(shapeItem.x - shapeItem.x1), Math.abs(shapeItem.y - shapeItem.y1));
        double dist = Math.hypot(center.x - current.x, center.y - current.y);
        return dist <= radius;
    }

    public boolean lineHitTest(Point current, Drawable shapeItem) {
        double mouseDist = Line2D.ptSegDist(shapeItem.x, shapeItem.y, shapeItem.x1, shapeItem.y1, current.x, current.y);
        return mouseDist <= 5.00;
    }

    public void selectShapes(Point current) {
        for (Drawable shapeItem : this.getShapeList()) {
            // SELECT rectangles
            if (shapeItem.type == 1) {
                if(this.rectHitTest(current, shapeItem)){
                    System.out.println("SELECT RECTANGLE");
                }
                // // change the border
                // // change the global thickness
                // // change the global color
                // // change the tool selecton setCurrTool
                // // notify the views
                // // MAybe return after this to only select one item??
                // }
            }
            // SELECT circles
            else if (shapeItem.type == 2) {
                // find the center of the circle
                // distance b/w (current and center)^2 <= (radius)^2
                // System.out.println("center ;"+center);
                if (this.circleHitTest(current, shapeItem)) {
                    System.out.println("SELECT CIRCLE");
                    // change the border
                    // change the global thickness
                    // change the global color
                    // change the tool selecton setCurrTool
                    // notify the views
                }
            }
            // SELECT line
            else if (shapeItem.type == 3) {
                if (this.lineHitTest(current, shapeItem)) {
                    System.out.println("SELECT LINE");
                    // change the border
                    // change the global thickness
                    // change the global color
                    // change the tool selecton setCurrTool
                    // notify the views
                }
            }
        }
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeDrawable(Drawable shape) {
        this.ShapeList.remove(shape);
        // notifyObservers();
    }

    public ArrayList<Drawable> getShapeList() {
        return this.ShapeList;
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
}
