
import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
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
