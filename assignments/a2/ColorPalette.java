import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JColorChooser;

/*
* <div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*/
public class ColorPalette extends JPanel implements Observer {

    private Model model;
    // Add the components
    JButton red = new JButton(new ImageIcon("images/red.png"));
    JButton blue = new JButton(new ImageIcon("images/blue.png"));
    JButton green = new JButton(new ImageIcon("images/green.png"));
    JButton yellow = new JButton(new ImageIcon("images/yellow.png"));
    JButton black = new JButton(new ImageIcon("images/black.png"));
    JButton brown = new JButton(new ImageIcon("images/brown.png"));
    JButton chooser = new JButton(new ImageIcon("images/color.png"));

    /**
     * Create a new View.
     */
    public ColorPalette(Model model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(200, 200));
        this.setSize(200, 200);
        JPanel predefColors = new JPanel();
        predefColors.setLayout(new GridLayout(3, 2, 3, 3));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // stretch the widget horizontally and vertically
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = 2; // 2 grid cell wide
        gc.gridheight = 4; // 4 grid cells tall

        red.setPreferredSize(new Dimension(65, 35));
        red.setMinimumSize(new Dimension(65, 35));

        predefColors.add(red);
        predefColors.add(blue);
        predefColors.add(green);
        predefColors.add(yellow);
        predefColors.add(black);
        predefColors.add(brown);

        this.add(predefColors, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 3;
        this.add(chooser, gc);

        this.model = model;
        model.addObserver(this);

        // Add mouslisteners to all the color buttons
        red.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(model.getCurrTool() == 6){
                model.setCurrFillColor(Color.RED);
                } else {
                model.setCurrDrawColor(Color.RED);
                }
                model.notifyObservers();
            }
        });

        blue.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (model.getCurrTool() == 6) {
                model.setCurrFillColor(Color.BLUE);
                } else {
                model.setCurrDrawColor(Color.BLUE);
                }
                model.notifyObservers();
            }
        });

        green.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (model.getCurrTool() == 6) {
                model.setCurrFillColor(Color.GREEN);
                } else {
                model.setCurrDrawColor(Color.GREEN);
                }
                model.notifyObservers();
            }
        });

        yellow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (model.getCurrTool() == 6) {
                model.setCurrFillColor(Color.YELLOW);
                } else {
                model.setCurrDrawColor(Color.YELLOW);
                }
                model.notifyObservers();
            }
        });

        black.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (model.getCurrTool() == 6) {
                model.setCurrFillColor(Color.BLACK);
                } else {
                model.setCurrDrawColor(Color.BLACK);
                }
                model.notifyObservers();
            }
        });

        brown.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (model.getCurrTool() == 6) {
                model.setCurrFillColor(new Color(139,69,19));
                } else {
                model.setCurrDrawColor(new Color(139,69,19));
                }
                // model.setCurrDrawColor(new Color(139, 69, 19));
                model.notifyObservers();
            }
        });

        /*
         * Inspired By:
         * https://stackoverflow.com/questions/26565166/how-to-display-a-color-selector-
         * when-clicking-a-button
         */

        chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                if (newColor != null) {
                    if (model.getCurrTool() == 6) {
                model.setCurrFillColor(newColor);
                } else {
                    model.setCurrDrawColor(newColor);
                    }
                    model.notifyObservers();
                }
            }
        });

        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

    /**
     * Update with data from the model. Add border to the currently selected button.
     */
    public void update(Object observable) {
        Color c;
        if(model.getCurrTool() == 6) {
            c = model.getCurrFillColor();
        } else {
            c = model.getCurrDrawColor();
        }
        // case Color.RED:
        if (c.equals(Color.RED)) {
            red.setBorder(new LineBorder(Color.BLACK, 2));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (c.equals(Color.BLUE)) {
            // case Color.BLUE:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 2));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (c.equals(Color.GREEN)) {
            // case Color.GREEN:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 2));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (c.equals(Color.YELLOW)) {
            // case Color.YELLOW:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 2));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (c.equals(Color.BLACK)) {
            // case Color.BLACK:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 2));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        } else if (c.equals(new Color(139, 69, 19))) {
            // case Color.brown:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 2));
        } else {
            // default:
            red.setBorder(new LineBorder(Color.BLACK, 0));
            blue.setBorder(new LineBorder(Color.BLACK, 0));
            green.setBorder(new LineBorder(Color.BLACK, 0));
            yellow.setBorder(new LineBorder(Color.BLACK, 0));
            black.setBorder(new LineBorder(Color.BLACK, 0));
            brown.setBorder(new LineBorder(Color.BLACK, 0));
        }
    }
}
