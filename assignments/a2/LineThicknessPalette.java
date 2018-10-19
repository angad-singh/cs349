
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
        this.setSize(200, 200);

        this.setLayout(new GridLayout(4, 1, 3, 3));

        line1.setMinimumSize(new Dimension(130, 50));

        // Add the components
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(line4);

        this.model = model;
        model.addObserver(this);

        // Add mouseListener to the buttons
        line1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(2);
                model.notifyObservers();
            }
        });

        line2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(4);
                model.notifyObservers();
            }
        });

        line3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(6);
                model.notifyObservers();
            }
        });

        line4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrThickness(8);
                model.notifyObservers();
            }
        });

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

    /**
     * Update with data from the model.Add border to the currently selected button.
     */
    public void update(Object observable) {
        switch (this.model.getCurrThickness()) {
        case 2:
            line1.setBorder(new LineBorder(Color.BLACK, 2));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 4:
            line2.setBorder(new LineBorder(Color.BLACK, 2));
            line1.setBorder(new LineBorder(Color.BLACK, 0));
            line3.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 6:
            line3.setBorder(new LineBorder(Color.BLACK, 2));
            line2.setBorder(new LineBorder(Color.BLACK, 0));
            line1.setBorder(new LineBorder(Color.BLACK, 0));
            line4.setBorder(new LineBorder(Color.BLACK, 0));
            break;
        case 8:
            line4.setBorder(new LineBorder(Color.BLACK, 2));
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
