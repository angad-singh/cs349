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

        // create a panel to hold all the views in the app
        JPanel appWindow = new JPanel(new BorderLayout());
        appWindow.setSize(800, 600);

        JPanel toolbar = new JPanel(new GridLayout(3,1));
        toolbar.setSize(200, 600);
        
        Model model = new Model();
        Menu menu = new Menu (model);
        ToolPalette tools = new ToolPalette(model);
        LineThicknessPalette lineThickness = new LineThicknessPalette(model);

        // ToolPalette tools1 = new ToolPalette(model);
        // ToolPalette tools2 = new ToolPalette(model);

        toolbar.add(tools);
        toolbar.add(lineThickness);
        // toolbar.add(tools2);
        appWindow.add(menu, BorderLayout.NORTH);
        appWindow.add(toolbar, BorderLayout.WEST);
        
        // add the panel to the app window
        frame.getContentPane().add(appWindow);
        
        // View view = new View(model);
        // Menu menu = new Menu(model);
        frame.setVisible(true);

    }
}
