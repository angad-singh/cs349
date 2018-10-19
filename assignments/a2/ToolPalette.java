import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/*
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="https://www.flaticon.com/authors/bqlqn" title="bqlqn">bqlqn</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="https://www.flaticon.com/authors/pixel-perfect" title="Pixel perfect">Pixel perfect</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*/
public class ToolPalette extends JPanel implements Observer {

    private Model model;
    
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
        JButton select = new JButton(new ImageIcon("images/select.png"));
        JButton erase = new JButton(new ImageIcon("images/erase.png"));
        JButton fill = new JButton(new ImageIcon("images/fill.png"));
        JButton line = new JButton(new ImageIcon("images/line.png"));
        JButton circle = new JButton(new ImageIcon("images/circle.png"));
        JButton rectangle = new JButton(new ImageIcon("images/rectangle.png"));

        select.setMinimumSize(new Dimension(65, 30));
        select.setPreferredSize(new Dimension(65, 30));

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
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 2));
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
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 2));
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
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 2));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        select.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(4);
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 2));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        erase.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(5);
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 2));
                fill.setBorder(new LineBorder(Color.BLACK, 0));
                // repaint();
            }
        });

        fill.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setCurrTool(6);
                // repaint();
                model.resetSelectedShape();
                rectangle.setBorder(new LineBorder(Color.BLACK, 0));
                circle.setBorder(new LineBorder(Color.BLACK, 0));
                line.setBorder(new LineBorder(Color.BLACK, 0));
                select.setBorder(new LineBorder(Color.BLACK, 0));
                erase.setBorder(new LineBorder(Color.BLACK, 0));
                fill.setBorder(new LineBorder(Color.BLACK, 2));
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
        // System.out.println("Model changed! whatttttttt");
        // repaint();
    }
}
