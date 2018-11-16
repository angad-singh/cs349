
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;
import java.awt.geom.NoninvertibleTransformException;


public abstract class Sprite {
    
    protected enum InteractionMode {
        IDLE,
        DRAGGING,
        SCALING,
        ROTATING
    }

    protected enum Part {
        TORSO,
        FACE,
        UPPERARM,
        LOWERARM,
        UPPERLEG,
        LOWERLEG
    }

    private Sprite parent = null;                               // Pointer to our parent
    private Vector<Sprite> children = new Vector<Sprite>();     // Holds all of our children
    private AffineTransform transform = new AffineTransform();  // Our transformation matrix
    protected Point2D lastPoint = null;                         // Last mouse point
    protected InteractionMode interactionMode = InteractionMode.IDLE;    // current state
    protected Part bodyPart = null;
    public double anchor_X = 0;
    public double anchor_Y = 0;
    public boolean right = true;

    public Sprite() {
    }
    
    public Sprite(Sprite parent) {
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void addChild(Sprite s) {
        children.add(s);
        s.setParent(this);
    }
    public Sprite getParent() {
        return parent;
    }
    private void setParent(Sprite s) {
        this.parent = s;
    }

    public abstract boolean pointInside(Point2D p);

    protected void handleMouseDownEvent(MouseEvent e) {
        lastPoint = e.getPoint();
        if (e.getButton() == MouseEvent.BUTTON1) {
            interactionMode = InteractionMode.DRAGGING;
        }
        // Handle rotation, scaling mode depending on input
    }

    protected void handleMouseDragEvent(MouseEvent e) {
        
        Point2D oldPoint = lastPoint;
        Point2D newPoint = e.getPoint();

        double x_diff = newPoint.getX() - oldPoint.getX();
        double y_diff = newPoint.getY() - oldPoint.getY();

        switch (interactionMode) {
            case IDLE:
                ; // no-op (shouldn't get here)
                break;
            case DRAGGING:        
                transform.translate(x_diff, y_diff);
                break;
            case ROTATING:
                double theta = Math.atan2(newPoint.getY(), newPoint.getX()) - Math.atan2(oldPoint.getY(), oldPoint.getX());
                // Provide rotation code here
                // add fields that track the max rotation for the body part
                double anchor_X = this.anchor_X;
                double anchor_Y = this.anchor_Y;

                theta = this.right ? theta*3 : -theta*3;

                // System.out.println("theta = " + Math.toDegrees(theta));
                transform.rotate(theta, anchor_X, anchor_Y);
                // transform = AffineTransform.getRotateInstance(theta*2,45,70);
                break;
            case SCALING:
                // double scaleFactor = Math.hypot(x_diff, y_diff);
                // AffineTransform fullTransform = this.getFullTransform();
                // AffineTransform inverseTransform = null;
                // try {
                //     inverseTransform = fullTransform.createInverse();
                // } catch (NoninvertibleTransformException ex) {
                //     ex.printStackTrace();
                // }
                // double original_X = transform.getTranslateX();
                // double original_Y = transform.getTranslateY();
                // transform.setTransform(inverseTransform);

                // transform.translate(original_X, original_Y);
                transform.scale(x_diff, y_diff);
                // transform.translate(-original_X, -original_Y);
                // transform.concatenate(fullTransform);
                break;
                
        }
        // Save our last point, if it's needed next time around
        lastPoint = e.getPoint();
    }
    
    protected void handleMouseUp(MouseEvent e) {
        interactionMode = InteractionMode.IDLE;
    }
    
    public Sprite getSpriteHit(MouseEvent e) {
        for (Sprite sprite : children) {
            Sprite s = sprite.getSpriteHit(e);
            if (s != null) {
                return s;
            }
        }
        if (this.pointInside(e.getPoint())) {
            return this;
        }
        return null;
    }
    
    public AffineTransform getFullTransform() {
        AffineTransform returnTransform = new AffineTransform();
        Sprite curSprite = this;
        while (curSprite != null) {
            returnTransform.preConcatenate(curSprite.getLocalTransform());
            curSprite = curSprite.getParent();
        }
        return returnTransform;
    }

    public AffineTransform getLocalTransform() {
        return (AffineTransform)transform.clone();
    }
    
    public void transform(AffineTransform t) {
        transform.concatenate(t);
    }

    public void draw(Graphics2D g) {
        AffineTransform oldTransform = g.getTransform();

        g.setTransform(this.getFullTransform());
        
        this.drawSprite(g);
        
        g.setTransform(oldTransform);

        for (Sprite sprite : children) {
            sprite.draw(g);
        }
    }
    
    protected abstract void drawSprite(Graphics2D g);
}
