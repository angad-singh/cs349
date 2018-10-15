import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToolPalette extends JPanel implements Observer {

    private Model model;
    // JButton select;
    // JButton erase;
    // JButton fill("Fill");
    // JButton line = new JButton("Line");
    // JButton circle = new JButton("Circle");
    // JButton rectangle

    /**
     * Create a new View.
     */
    public ToolPalette(Model model) {
        // Set up the window.
        // this.setTitle("Your program title here!");
        // this.setMinimumSize(new Dimension(128, 128));
        this.setSize(200, 200);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(3, 2, 3, 3));

        // Add the components
        JButton select = new JButton("Select");
        JButton erase = new JButton("Erase");
        JButton fill = new JButton("Fill");
        JButton line = new JButton("Line");
        JButton circle = new JButton("Circle");
        JButton rectangle = new JButton("Rectangle");
        // change the north button to be a toolbar using flow layout
        this.add(select);
        this.add(erase);
        this.add(fill);
        this.add(line);
        this.add(circle);
        this.add(rectangle);

        this.model = model;
        model.addObserver(this);

        rectangle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               model.setCurrTool(1);
            }
        });

        circle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(2);
            }
        });

        line.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(3);
            }
        });

        select.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(4);
            }
        });

        erase.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(5);
            }
        });

        fill.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(6);
            }
        });

        setVisible(true);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed!");
    }
}
