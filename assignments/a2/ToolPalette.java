import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

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
        // Use this to change border depending on what's selected
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
                rectangle.setBorder(new LineBorder(Color.BLACK, 3));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        circle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(2);
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 3));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        line.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(3);
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 3));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        select.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(4);
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 3));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        erase.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(5);
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 3));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        fill.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(6);
                // repaint();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 3));
            }
        });

        setVisible(true);
    }

    // public void paintComponent(Graphics g) {
    //     switch (this.model.getCurrTool()) {
    //     case 1:
    //         rectangle.setBorder(new LineBorder(Color.BLACK, 3));
    //     case 2:
    //         circle.setBorder(new LineBorder(Color.BLACK, 3));
    //     case 3:
    //         line.setBorder(new LineBorder(Color.BLACK, 3));
    //     case 4:
    //         select.setBorder(new LineBorder(Color.BLACK, 3));
    //     case 5:
    //         erase.setBorder(new LineBorder(Color.BLACK, 3));
    //     case 6:
    //         fill.setBorder(new LineBorder(Color.BLACK, 3));
    //     }
    // }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed! whatttttttt");
        // repaint();
    }
}
