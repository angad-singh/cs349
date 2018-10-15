import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import javax.swing.JPanel;

public class Drawable extends JPanel {
    // fields
    public int x;
    public int y;
    public int width;
    public int height;
    public int lineThickness;
    public boolean isFilled;
    public Color drawColor;
    // public Color fillColor;
    public int type;

    // Constructor
    public Drawable() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public Drawable(int x, int y, int width, int height, int thickness, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lineThickness = thickness;
        this.type = type;
    }

    // public void paint(Graphics g) {
    //     Graphics2D g2 = (Graphics2D) g;
    //     g2.setColor(this.drawColor);
    //     g2.setStroke(new BasicStroke(this.lineThickness));

    //     if (this.type == 1) {
    //         if (isFilled) {
    //             g2.fillRect(10, 10, 100, 100);
    //         } else {
    //             g2.drawRect(this.x, this.y, this.width, this.height);
    //         }
    //     }
    // }
}
