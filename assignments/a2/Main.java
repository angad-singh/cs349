import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // App window
        JFrame frame = new JFrame("JSketch - Paint Freely!");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(650, 550));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Model model = new Model();

        // Create a menubar with File menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem newImage = new JMenuItem("New Canvas");
        JMenuItem load = new JMenuItem("Load Canvas");
        JMenuItem save = new JMenuItem("Save Canvas");

        // add actionListeners to the menu items
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.saveDrawing();
            }
        });

        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.loadDrawing();
                model.notifyObservers();
            }
        });

        newImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.newDrawing();
            }
        });

        file.add(newImage);
        file.add(load);
        file.add(save);

        menuBar.add(file);

        // create a toolbar for the app
        JToolBar toolbar = new JToolBar("Toolbar");
        toolbar.setOrientation(SwingConstants.VERTICAL);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1));
        // create the tools palette
        ToolPalette tools = new ToolPalette(model);
        // create the line thickness palette
        LineThicknessPalette lineThickness = new LineThicknessPalette(model);
        // create the color palette
        ColorPalette colorPalette = new ColorPalette(model);
        // create the canvas
        MyCanvas canvas = new MyCanvas(model);

        /*
         * Inspired by
         * https://stackoverflow.com/questions/13042504/keypressed-event-in-java 
         * remove selection on pressing Escape
         */
        InputMap im = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = canvas.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "onEscape");

        am.put("onEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.resetSelectedShape();
            }
        });

        p.add(tools);
        p.add(colorPalette);
        p.add(lineThickness);

        toolbar.add(p);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(toolbar, BorderLayout.WEST);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
