import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ColorPalette extends JPanel implements Observer {

    private Model model;
    // Add the components
    JButton red = new JButton("Red");
    JButton blue = new JButton("Blue");
    JButton green = new JButton("Green");
    JButton yellow = new JButton("Yellow");
    JButton black = new JButton("Black");
    JButton white = new JButton("White");

    /**
     * Create a new View.
     */
    public ColorPalette(Model model) {
        // Set up the window.
        // this.setTitle("Your program title here!");
        // this.setMinimumSize(new Dimension(128, 128));
        this.setSize(200, 200);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(3, 2, 3, 3));

        // Use this to change border depending on what's selected
        // change the north button to be a toolbar using flow layout
        this.add(red);
        this.add(blue);
        this.add(green);
        this.add(yellow);
        this.add(black);
        this.add(white);

        this.model = model;
        model.addObserver(this);

        red.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.RED);
                model.notifyObservers();
                // repaint();
            }
        });

        blue.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.BLUE);
                model.notifyObservers();
                // repaint();
            }
        });

        green.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.GREEN);
                model.notifyObservers();
                // repaint();
            }
        });

        yellow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.YELLOW);
                model.notifyObservers();
                // repaint();
            }
        });

        black.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.BLACK);
                model.notifyObservers();
            }
        });

        white.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrDrawColor(Color.WHITE);
                model.notifyObservers();
                // repaint();
            }
        });
        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // switch (this.model.getCurrDrawColor()) {
        // case Color.RED:
        if (this.model.getCurrDrawColor().equals(Color.RED)){
            red.setBorder(new LineBorder(Color.BLACK, 3));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (this.model.getCurrDrawColor().equals(Color.BLUE)){
        // case Color.BLUE:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 3));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (this.model.getCurrDrawColor().equals(Color.GREEN)){
        // case Color.GREEN:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 3));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (this.model.getCurrDrawColor().equals(Color.YELLOW)){
        // case Color.YELLOW:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 3));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (this.model.getCurrDrawColor().equals(Color.BLACK)) {
        // case Color.BLACK:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 3));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (this.model.getCurrDrawColor().equals(Color.WHITE)){
        // case Color.WHITE:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 3));
        } else {
        // default:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            white.setBorder(new LineBorder(Color.BLACK, 0));
        }
        // }
    }
}
