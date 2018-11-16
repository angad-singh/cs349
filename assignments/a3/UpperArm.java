

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;


public class UpperArm extends Sprite {

    private Ellipse2D ellipse = null;

    public UpperArm(int width, int height) {
        super();
        this.initialize(width, height);
    }
    public UpperArm(int width, int height, Sprite parentSprite) {
        super(parentSprite);
        this.initialize(width, height);
    }
    
    private void initialize(int width, int height) {
        ellipse = new Ellipse2D.Double(0, 0, width, height);
        this.bodyPart = Part.UPPERARM;
        this.anchor_X = 0;
        this.anchor_Y = 0;
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
        return ellipse.contains(newPoint);
    }

    protected void drawSprite(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.draw(ellipse);
    }
    
    public String toString() {
        return "UpperArm: " + ellipse;
    }
}
