
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class MyCanvas extends JComponent implements Observer{

    private Model model;
    Point current = new Point();
    Point start = new Point();

    /**
     * Create a new View.
     */
    MyCanvas(Model model) {
        super();
        this.model = model;
        model.addObserver(this);
        this.setSize(600, 600);
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                current.x = e.getX();
                current.y = e.getY();
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                start.x = e.getX();
                start.y = e.getY();
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                current.x = e.getX();
                current.y = e.getY();
                // if the currently selected tool is a shape then add to the list
                if (model.getCurrTool() <= 3) {
                    // TODO move this to model?
                    Drawable shape = new Drawable();
                    shape.x = (model.getCurrTool() < 2) ? Math.min(start.x, current.x) : start.x;
                    shape.y = (model.getCurrTool() < 2) ? Math.min(start.y, current.y) : start.y;
                    shape.x1 = current.x;
                    shape.y1 = current.y;
                    shape.width = Math.abs(start.x - current.x);
                    shape.height = Math.abs(start.y - current.y);
                    // shape.lineThickness = this.model.getCurrThickness();
                    shape.drawColor = model.getCurrDrawColor();
                    shape.fillColor = model.getCurrFillColor();
                    shape.isFilled = false;
                    shape.type = model.getCurrTool();

                    model.addShape(shape);
                    repaint();
                }
            }

            public void mouseClicked(MouseEvent e) {
                current.x = e.getX();
                current.y = e.getY();

                if (model.getCurrTool() == 4) {
                    // SELECT tool
                    model.selectShapes(current);
                } else if (model.getCurrTool() == 5) {
                    // ERASE tool
                    model.deleteShapes(current);
                } else if (model.getCurrTool() == 6) {
                    // FILL tool
                    model.fillShapes(current);
                }
            }
        });

    }

    public void paintComponent(Graphics g) {
        // super.paint(g);
        // cast to get 2D drawing methods
        // this.model.set(1, 2);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        // g2.setStroke(new BasicStroke(this.model.getCurrThickness()));
        // g2.setColor(this.model.getColor());

        // loop through the model's shape list and grab the drawable objects
        // check contrasints and call appropriate methods
        for (Drawable shapeItem : this.model.getShapeList()) {
            // TODO set color
            // draw rectangles
            if (shapeItem.type == 1) {
                if (shapeItem.isFilled) {
                    g2.setColor(shapeItem.fillColor);
                    g2.fillRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                } else {
                    g2.setColor(shapeItem.drawColor);
                    g2.drawRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                }
            }
            // draw circles
            if (shapeItem.type == 2) {
                int radius = (int) Math.hypot(Math.abs(shapeItem.x1 - shapeItem.x), Math.abs(shapeItem.y1 - shapeItem.y));
                if (shapeItem.isFilled) {
                    g2.setColor(shapeItem.fillColor);
                    g2.fillOval(shapeItem.x - radius, shapeItem.y - radius, 2*radius, 2*radius);
                } else {
                    g2.setColor(shapeItem.drawColor);
                    g2.drawOval(shapeItem.x - radius, shapeItem.y - radius, 2*radius, 2*radius);
                }
            }
            // draw lines
            if (shapeItem.type == 3) {
                g2.setColor(shapeItem.drawColor);
                g2.drawLine(shapeItem.x, shapeItem.y, shapeItem.x1, shapeItem.y1);
            }
        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        repaint();
    }
}
