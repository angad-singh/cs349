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
        // frame.setMinimumSize(128, 128);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Model model = new Model();

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");

        menuBar.add(file);
        menuBar.add(view);

        JMenuItem newImage = new JMenuItem("New");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

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

        JMenuItem fullSize = new JMenuItem("Full Size");
        JMenuItem window = new JMenuItem("Fit to Window");

        view.add(fullSize);
        view.add(window);

        JToolBar toolbar = new JToolBar("toolbar");
        toolbar.setOrientation(SwingConstants.VERTICAL);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1));
        // Menu menu = new Menu (model);
        ToolPalette tools = new ToolPalette(model);
        LineThicknessPalette lineThickness = new LineThicknessPalette(model);
        ColorPalette colorPalette = new ColorPalette(model);
        MyCanvas canvas = new MyCanvas(model);

        // remove selection on pressing Escape
        /*
         * Inspired by
         * https://stackoverflow.com/questions/13042504/keypressed-event-in-java
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
