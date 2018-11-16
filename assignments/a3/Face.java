

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;


public class Face extends Sprite {

    private Ellipse2D circle = null;

    public Face(int width, int height) {
        super();
        this.initialize(width, height);
        this.bodyPart = Part.FACE;
        this.right = false;
    }

    public Face(int width, int height, Sprite parentSprite) {
        super(parentSprite);
        this.initialize(width, height);
        this.bodyPart = Part.FACE;
    }
    
    private void initialize(int width, int height) {
        circle = new Ellipse2D.Double(0, 0, width, height);
        this.anchor_X = 15;
        this.anchor_Y = 50;
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
        boolean result = circle.contains(newPoint);
        // if(result) {
        //     interactionMode = InteractionMode.ROTATING;
        // }

        return result;
    }

    protected void drawSprite(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.draw(circle);
    }
    
    public String toString() {
        return "Face: " + circle;
    }
}
