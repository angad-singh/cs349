
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Canvas extends JComponent implements Observer {

    private Model model;

    /**
     * Create a new View.
     */
    public Canvas(Model model) {
        // Set up the window.
        // this.setTitle("Your program title here!");
        // this.setMinimumSize(new Dimension(128, 128));
        this.setSize(600, 600);
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
        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

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
