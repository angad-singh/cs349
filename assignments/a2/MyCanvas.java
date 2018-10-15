
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyCanvas extends JComponent {

    private Model model;
    Point current = new Point();
    Point start = new Point();

    /**
     * Create a new View.
     */
    MyCanvas(Model model) {
        super();
        this.model = model;
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
                // model.setStart(start.x, start,y);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                current.x = e.getX();
                current.y = e.getY();
                // if the currently selected tool is a shape then add to the list
                if (model.getCurrTool() <= 3) {
                    Drawable shape = new Drawable();
                    shape.x = Math.min(start.x, current.x);
                    shape.y = Math.min(start.y, current.y);
                    shape.width = Math.abs(start.x - current.x);
                    shape.height = Math.abs(start.y - current.y);
                    // shape.lineThickness = this.model.getCurrThickness();
                    // shape.drawColor = model.getCurrColor();
                    shape.isFilled = false;
                    shape.type = model.getCurrTool();

                    model.addShape(shape);
                    repaint();
                }
                // model.setEndPos(current.x, current.y);
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
            // draw rectangles
            if (shapeItem.type == 1) {
                // g2.setColor(shapeItem.drawColor);
                if (shapeItem.isFilled) {
                    System.out.println(shapeItem.isFilled);

                    g2.fillRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                } else {
                    g2.drawRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                }
            }
            // draw circles
            if (shapeItem.type == 2) {
                if (shapeItem.isFilled) {
                    g2.fillOval(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                } else {
                    g2.drawOval(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                }
            }
        }
        // g2.setBackground(Color.BLACK);

    }
    // super();
    // Set up the window.
    // this.setTitle("Your program title here!");
    // this.setMinimumSize(new Dimension(128, 128));
    // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add the components
    // change the north button to be a toolbar using flow layout
    // this.add(new JButton("Line1"));
    // this.add(new JButton("Line2"));
    // this.add(new JButton("Line3"));
    // this.add(new JButton("Line4"));
    // change the west button to have a tool bar
    // this.add(new JButton("West"), BorderLayout.WEST);

    // Layouts can be nested ...

    // Box is an easy-to-create JPanel with a BoxLayout
    // Box b = Box.createHorizontalBox();
    // b.add(Box.createHorizontalGlue());
    // b.add(new JButton("Ok"));
    // b.add(Box.createHorizontalStrut(20));
    // b.add(new JButton("Cancel"));

    // this.add(b, BorderLayout.SOUTH);

    // Hook up this observer so that it will be notified when the model
    // changes.
    // this.model = model;
    // model.addObserver(this);

    // setVisible(true);
    // events

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        // select the appropriate line thickness from the list
        System.out.println("Model changed!");
    }
}
