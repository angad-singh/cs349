
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
                if (model.getCurrTool() == 4){ //drag selected shape around
                    if (model.getSelectedShape() != null){
                        model.getSelectedShape().translateX = current.x;
                        model.getSelectedShape().translateY = current.y;
                        model.getSelectedShape().isTranslated = true;
                    }
                }
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
                    shape.lineThickness = model.getCurrThickness();
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
        super.paintComponent(g);
        // loop through the model's shape list and grab the drawable objects
        // check contrasints and call appropriate methods
        Graphics2D g2 = (Graphics2D) g;

        for (Drawable shapeItem : this.model.getShapeList()) {
            Stroke solid = new BasicStroke(shapeItem.lineThickness);
            // Stroke dashed = new BasicStroke(shapeItem.lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                    // new float[] { 9 }, 0);
            Stroke dashed = new BasicStroke(shapeItem.lineThickness);
            g2.setColor(shapeItem.drawColor);
            g2.setStroke(solid);
            if (shapeItem.isTranslated){
                g2.translate(shapeItem.translateX, shapeItem.translateY);
            }
            // draw rectangles
            if (shapeItem.type == 1) {
                if (shapeItem.isSelected) {
                    g2.setStroke(dashed);
                }
                if (shapeItem.isFilled) {
                    // g2.setColor(shapeItem.fillColor);
                    g2.fillRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                    // g2.setColor(shapeItem.drawColor);
                    // g2.drawRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                } else {
                    g2.drawRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                }
            }
            // draw circles
            if (shapeItem.type == 2) {
                if (shapeItem.isSelected) {
                    g2.setStroke(dashed);
                }
                int radius = (int) Math.hypot(Math.abs(shapeItem.x1 - shapeItem.x), Math.abs(shapeItem.y1 - shapeItem.y));
                if (shapeItem.isFilled) {
                    // g2.setColor(shapeItem.fillColor);
                    g2.fillOval(shapeItem.x - radius, shapeItem.y - radius, 2*radius, 2*radius);
                    // g2.setColor(shapeItem.drawColor);
                    // g2.drawOval(shapeItem.x - radius, shapeItem.y - radius, 2 * radius, 2 * radius);
                } else {
                    g2.drawOval(shapeItem.x - radius, shapeItem.y - radius, 2*radius, 2*radius);
                }
            }
            // draw lines
            if (shapeItem.type == 3) {
                if (shapeItem.isSelected) {
                    g2.setStroke(dashed);
                }
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
