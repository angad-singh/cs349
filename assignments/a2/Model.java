
import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import javax.swing.JPanel;

public class Model extends Observable {
    int x;
    int y;

    public class Drawable extends JPanel{
        //fields
        public int x;
        public int y;
        public int width;
        public int height;
        public int lineThickness;
        public boolean isFilled;
        public Color drawColor;
        // public Color fillColor;
        public String type;

        // Constructor
        public Drawable() {
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }

        public Drawable(int x, int y, int width, int height,
                        int thickness, String type) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.lineThickness = thickness;
            this.type = type;
        }

        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(this.drawColor);
            g2.setStroke(new BasicStroke(this.lineThickness));

            if (Objects.equals(this.type, new String("rectangle"))) {
                if (isFilled) {
                    g2.fillRect(10,10,100,100);
                } else {
                    g2.drawRect(this.x, this.y, this.width, this.height);
                }
            }
        }

        // public void setThickness(int thickness) {
        //     this.lineThickness = thickness;
        // }

        // public void isFilled() {
        //     this.lineThickness = thickness;
        // }
    }
    /** The observers that are watching this model for changes. */
    private ArrayList<Observer> observers;
    private ArrayList<Drawable> ShapeList = new ArrayList<Drawable>();

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    public void addShape(Drawable shape) {
        this.ShapeList.add(shape);
        notifyObservers();
    }

    public void set(int x, int y) {
        System.out.println("Model X = " + x);
        System.out.println("Model Y = " + y);
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
        notifyObservers();
    }

    public void paintShapes() {
        // Graphics g1 = 
        
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
