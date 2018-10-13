import java.awt.BorderLayout;
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
        
        Model model = new Model();
        Menu menu = new Menu (model);

        appWindow.add(menu, BorderLayout.NORTH);
        
        // add the panel to the app window
        frame.getContentPane().add(appWindow);
        
        // View view = new View(model);
        // Menu menu = new Menu(model);
        frame.setVisible(true);

    }
}
