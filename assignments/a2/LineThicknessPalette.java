
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LineThicknessPalette extends JComponent implements Observer {

    private Model model;

    JButton line1 = new JButton(new ImageIcon("images/Line1.png"));
    JButton line2 = new JButton(new ImageIcon("images/Line2.png"));
    JButton line3 = new JButton(new ImageIcon("images/Line3.png"));
    JButton line4 = new JButton(new ImageIcon("images/Line4.png"));
    /**
     * Create a new View.
     */
    public LineThicknessPalette(Model model) {
        // Set up the window.
        // this.setTitle("Your program title here!");
        // this.setMinimumSize(new Dimension(128, 128));
        this.setSize(200, 200);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(4, 1, 3, 3));

        line1.setMinimumSize(new Dimension(130, 50));

        // Add the components
        // change the north button to be a toolbar using flow layout
        // Use this to change border depending on what's selected
        // change the north button to be a toolbar using flow layout
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(line4);

        this.model = model;
        model.addObserver(this);

        line1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(2);
                model.notifyObservers();
                // repaint();
            }
        });

        line2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(4);
                model.notifyObservers();
                // repaint();
            }
        });

        line3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(6);
                model.notifyObservers();
                // repaint();
            }
        });

        line4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(8);
                model.notifyObservers();
                // repaint();
            }
        });

        this.model = model;
        model.addObserver(this);

        // this.add(b, BorderLayout.SOUTH);
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                // model.set(e.getX(), e.getY());
            }
        });

        // Hook up this observer so that it will be notified when the model
        // changes.

        setVisible(true);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        switch (this.model.getCurrThickness()) {
        case 2:
            line1.setBorder(new LineBorder(Color.BLACK, 3));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 4:
            line2.setBorder(new LineBorder(Color.BLACK, 3));
            line1.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 6:
            line3.setBorder(new LineBorder(Color.BLACK, 3));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line1.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 8:
            line4.setBorder(new LineBorder(Color.BLACK, 3));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line1.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        default:
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line1.setBorder(new LineBorder(Color.BLACK, 0));

        }
    }
}
