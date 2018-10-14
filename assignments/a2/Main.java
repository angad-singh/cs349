import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // App window
        JFrame frame = new JFrame("JSketch - Paint Freely!");
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
        JPanel appWindow = new JPanel(new BorderLayout());
        appWindow.setSize(800, 600);

        // Panel to hold all the toolbars
        JPanel toolbar = new JPanel(new GridLayout(3,1));
        toolbar.setSize(200, 600);
        
        Model model = new Model();
        // Menu menu = new Menu (model);
        ToolPalette tools = new ToolPalette(model);
        LineThicknessPalette lineThickness = new LineThicknessPalette(model);
        ColorPalette colorPalette = new ColorPalette(model);
        MyCanvas canvas = new MyCanvas(model);

        // ToolPalette tools1 = new ToolPalette(model);
        // ToolPalette tools2 = new ToolPalette(model);

        toolbar.add(tools);
        toolbar.add(colorPalette);
        toolbar.add(lineThickness);

        // appWindow.add(menu, BorderLayout.NORTH);
        appWindow.add(toolbar, BorderLayout.WEST);
        appWindow.add(canvas, BorderLayout.EAST);
        
        // add the panel to the app window
        frame.getContentPane().add(appWindow);
        frame.setJMenuBar(menuBar);
        // frame.setContentPane(canvas);
        
        // View view = new View(model);
        // Menu menu = new Menu(model);
        frame.setVisible(true);

    }
}
