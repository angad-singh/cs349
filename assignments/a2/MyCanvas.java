
import java.io.*;
import java.math.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyCanvas extends JComponent {

    private Model model;
    Point current = new Point(); // mouse point
    Point start = new Point(150, 150); // click point

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
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
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
                repaint();
                // model.setEndPos(current.x, current.y);
            }
        });

    }

    public void paintComponent(Graphics g) {
        // super.paint(g);
        // cast to get 2D drawing methods
        // this.model.set(1, 2);
       
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        // g2.setStroke(new BasicStroke(this.model.getCurrThickness()));
        // g2.setColor(this.model.getColor());

        Drawable shape = new Drawable();
        shape.x = start.x;
        shape.y = start.y;
        shape.width = Math.abs(start.x - current.x);
        shape.height = Math.abs(start.y - current.y);
        // shape.lineThickness = this.model.getCurrThickness();
        shape.isFilled = false;
        shape.type = this.model.getCurrTool();

        this.model.addShape(shape);
        // loop through the model's shape list and grab the drawable objects
        // check contrasints and call appropriate methods
        for (Drawable shapeItem : this.model.getShapeList()) {
            if (shapeItem.type == 1) {// rectangle
                if (shapeItem.isFilled) {
                    g2.fillRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                } else {
                    g2.drawRect(shapeItem.x, shapeItem.y, shapeItem.width, shapeItem.height);
                }
            }
        }

        // g2.drawLine(10, 10, 100, 100);
        // g2.fillRect(10, 10, 200, 200);

        g2.setBackground(Color.BLACK);
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
