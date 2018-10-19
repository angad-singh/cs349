import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import javax.swing.JPanel;

public class Drawable extends JPanel {
    // fields
    public int x;
    public int y;
    public int x1;
    public int y1;
    public int width;
    public int height;
    public int lineThickness;
    public boolean isFilled;
    public Color drawColor;
    public Color fillColor;
    public int type;
    public int translateX;
    public int translateY;
    public boolean isTranslated;
    public boolean isSelected;

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
}
