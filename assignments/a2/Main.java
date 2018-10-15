import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // App window
        JFrame frame = new JFrame("JSketch - Paint Freely!");
        frame.getContentPane().setLayout(new BorderLayout());
        // frame.setMinimumSize(128, 128);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");

        menuBar.add(file);
        menuBar.add(view);

        JMenuItem newImage = new JMenuItem("New");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

        file.add(newImage);
        file.add(load);
        file.add(save);

        JMenuItem fullSize = new JMenuItem("Full Size");
        JMenuItem window = new JMenuItem("Fit to Window");

        view.add(fullSize);
        view.add(window);

        // create a panel to hold all the views in the app
        // JPanel appWindow = new JPanel(new BorderLayout());
        // appWindow.setSize(800, 600);

        // Panel to hold all the ps
        // JPanel toolbar = new JPanel(new GridLayout(3,1));
        // toolbar.setSize(200, 600);
        
        Model model = new Model();
        JToolBar toolbar = new JToolBar("toolbar");
        toolbar.setOrientation(SwingConstants.VERTICAL);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1));
        // Menu menu = new Menu (model);
        ToolPalette tools = new ToolPalette(model);
        LineThicknessPalette lineThickness = new LineThicknessPalette(model);
        ColorPalette colorPalette = new ColorPalette(model);
        MyCanvas canvas = new MyCanvas();

        // Graphics g = frame.getGraphics();

        // canvas.paint(g);
        

        // ToolPalette tools1 = new ToolPalette(model);
        // ToolPalette tools2 = new ToolPalette(model);

        
        // appWindow.add(menu, BorderLayout.NORTH);
        // appWindow.add(toolbar, BorderLayout.WEST);
       

        // Canvas canvas = new Canvas();
        // canvas.setBackground(new Color(255,0,0));

        

        // canvas.setSize(400, 400);
        // frame.add(canvas);
        p.add(tools);
        p.add(colorPalette);
        p.add(lineThickness);

        toolbar.add(p);

        // frame.pack();
        // frame.setVisible(true);
        // appWindow.add(canvas, BorderLayout.EAST);
        
        // add the panel to the app window
        frame.setJMenuBar(menuBar);
        // JPanel panel = new JPanel();
        // Canvas c = new Canvas();
        // panel.add(new Canvas());
        // frame.setContentPane(c);
        frame.getContentPane().add(toolbar, BorderLayout.WEST);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        // frame.add(appWindow);
        
        // View view = new View(model);
        // Menu menu = new Menu(model);
        frame.setVisible(true);

    }
}
