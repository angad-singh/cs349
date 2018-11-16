

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.*;


public class Torso extends Sprite {

    private Rectangle2D rect = null;

    public Torso(int width, int height) {
        super();
        this.bodyPart = Part.TORSO;
        this.initialize(width, height);
    }

    public Torso(int width, int height, Sprite parentSprite) {
        super(parentSprite);
        this.bodyPart = Part.TORSO;
        this.initialize(width, height);
    }
    
    private void initialize(int width, int height) {
        rect = new Rectangle2D.Double(15, 70, width, height);
    }
    
    public boolean pointInside(Point2D p) {
        AffineTransform fullTransform = this.getFullTransform();
        AffineTransform inverseTransform = null;
        try {
            inverseTransform = fullTransform.createInverse();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        Point2D newPoint = (Point2D)p.clone();
        inverseTransform.transform(newPoint, newPoint);
        return rect.contains(newPoint);
    }

    protected void drawSprite(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.draw(rect);
    }
    
    public String toString() {
        return "Torso: " + rect;
    }
}
