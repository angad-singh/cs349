
import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.math.*;
import java.awt.geom.*;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.awt.event.*;
import java.io.*;

public class Model extends Observable {
    // private int type;
    // private int startX;
    // private int startY;
    // private int endX;
    // private int endY;
    private int currTool;
    private Color drawColor = Color.BLACK;
    private Color fillColor = Color.BLACK;
    private int thickness;

    /** The observers that are watching this model for changes. */
    private ArrayList<Observer> observers;
    private ArrayList<Drawable> ShapeList = new ArrayList<Drawable>();
    private Drawable SelectedShape;

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    // public void setType(int i) {
    // this.type = i;
    // }

    public void addShape(Drawable shape) {
        this.ShapeList.add(shape);
        // notifyObservers();
    }

    // public void setStartPos(int x, int y) {
    // this.startX = x;
    // this.startY = y;
    // }

    public void setCurrTool(int i) {
        this.currTool = i;
    }

    public int getCurrTool() {
        return this.currTool;
    }

    // public void setEndPos(int x, int y) {
    // this.endX = x;
    // this.endY = y;
    // }

    public ArrayList<Drawable> getShapeList() {
        return this.ShapeList;
    }

    public Drawable getSelectedShape() {
        return this.SelectedShape;
    }

    public void resetSelectedShape() {
        this.SelectedShape = null;
    }

    public Color getCurrDrawColor() {
        return this.drawColor;
    }

    public Color getCurrFillColor() {
        return this.fillColor;
    }

    public void setCurrDrawColor(Color c) {
        this.drawColor = c;
        this.setSelectedShapeColor(c);
    }

    public void setCurrFillColor(Color c) {
        this.fillColor = c;
    }

    public int getCurrThickness() {
        return this.thickness;
    }

    public void setCurrThickness(int i) {
        this.thickness = i;
        this.setSelectedShapeThickness(i);
    }

    public boolean rectHitTest(Point current, Drawable shapeItem) {
        return ((current.x <= shapeItem.x + shapeItem.width) && (current.x >= shapeItem.x)
                && (current.y <= shapeItem.y + shapeItem.height) && (current.y >= shapeItem.y));
    }

    public boolean circleHitTest(Point current, Drawable shapeItem) {
        Point center = new Point(shapeItem.x, shapeItem.y);
        double radius = (int) Math.hypot(Math.abs(shapeItem.x - shapeItem.x1), Math.abs(shapeItem.y - shapeItem.y1));
        double dist = Math.hypot(center.x - current.x, center.y - current.y);
        return dist <= radius;
    }

    public boolean lineHitTest(Point current, Drawable shapeItem) {
        double mouseDist = Line2D.ptSegDist(shapeItem.x, shapeItem.y, shapeItem.x1, shapeItem.y1, current.x, current.y);
        return mouseDist <= 5.00;
    }

    public void setSelectedShapeColor(Color c) {
        if (this.getCurrTool() == 4) {
            Drawable selectedShape = this.getSelectedShape();
            if (selectedShape != null) {
                selectedShape.drawColor = c;
            }
        }
    }

    public void setSelectedShapeThickness(int thickness) {
        if (this.getCurrTool() == 4) {
            Drawable selectedShape = this.getSelectedShape();
            if (selectedShape != null) {
                selectedShape.lineThickness = thickness;
            }
        }
    }

    public void deleteShapes(Point current) {

        Iterator<Drawable> it = ShapeList.iterator();

        while (it.hasNext()) {
            // DELETE rectangles
            Drawable shapeItem = it.next();
            if (shapeItem.type == 1) {
                if (this.rectHitTest(current, shapeItem)) {
                    it.remove();
                    notifyObservers();
                    break;
                }
            }
            // DELETE circles
            else if (shapeItem.type == 2) {
                if (this.circleHitTest(current, shapeItem)) {
                    it.remove();
                    notifyObservers();
                    break;
                }
            }
            // DELETE line
            else if (shapeItem.type == 3) {
                if (this.lineHitTest(current, shapeItem)) {
                    it.remove();
                    notifyObservers();
                    break;
                }
            }
        }
    }

    public void fillShapes(Point current) {

        Iterator<Drawable> it = ShapeList.iterator();

        while (it.hasNext()) {
            // FILL rectangles
            Drawable shapeItem = it.next();
            if (shapeItem.type == 1) {
                if (this.rectHitTest(current, shapeItem)) {
                    shapeItem.isFilled = true;
                    shapeItem.drawColor = this.getCurrDrawColor();
                    // shapeItem.fillColor = this.getCurrFillColor();
                    notifyObservers();
                    break;
                }
            }
            // FILL circles
            else if (shapeItem.type == 2) {
                if (this.circleHitTest(current, shapeItem)) {
                    shapeItem.isFilled = true;
                    shapeItem.drawColor = this.getCurrDrawColor();
                    // shapeItem.fillColor = this.getCurrFillColor();
                    notifyObservers();
                    break;
                }
            }
        }
    }

    public void selectShapes(Point current) {
        boolean foundShape = false;
        for (Drawable shapeItem : this.getShapeList()) {
            // SELECT rectangles
            if (shapeItem.type == 1) {
                if (this.rectHitTest(current, shapeItem) && !foundShape) {
                    SelectedShape = shapeItem;
                    this.setCurrThickness(shapeItem.lineThickness);
                    this.setCurrDrawColor(shapeItem.drawColor);
                    shapeItem.isSelected = true;
                    foundShape = true;
                    notifyObservers();
                    // break;
                } else {
                    shapeItem.isSelected = false;
                }
            }
            // SELECT circles
            else if (shapeItem.type == 2) {
                if (this.circleHitTest(current, shapeItem) && !foundShape) {
                    SelectedShape = shapeItem;
                    this.setCurrThickness(shapeItem.lineThickness);
                    this.setCurrDrawColor(shapeItem.drawColor);
                    shapeItem.isSelected = true;
                    foundShape = true;
                    notifyObservers();
                    // break;
                } else {
                    shapeItem.isSelected = false;
                }
            }
            // SELECT line
            else if (shapeItem.type == 3) {
                if (this.lineHitTest(current, shapeItem) && !foundShape) {
                    SelectedShape = shapeItem;
                    this.setCurrThickness(shapeItem.lineThickness);
                    this.setCurrDrawColor(shapeItem.drawColor);
                    shapeItem.isSelected = true;
                    foundShape = true;
                    notifyObservers();
                    // break;
                } else {
                    shapeItem.isSelected = false;
                }
            }
        }
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeDrawable(Drawable shape) {
        this.ShapeList.remove(shape);
        // notifyObservers();
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }

    /*
     * Inspired by
     * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     * https://www.mkyong.com/java/json-simple-example-read-and-write-json/
     */
    public void loadDrawing() {
        JSONParser parser = new JSONParser();
        // Object obj;

        JFileChooser openfile = new JFileChooser();
        int sf = openfile.showOpenDialog(openfile);
        if (sf != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = openfile.getSelectedFile();
        this.newDrawing();

        try {

            Object obj = parser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) obj;
            // System.out.println(jsonObject);

            this.setCurrThickness(((Long) jsonObject.get("thickness")).intValue());
            this.setCurrTool(((Long) jsonObject.get("currTool")).intValue());
            int totalShapes = ((Long) jsonObject.get("totalShapes")).intValue();

            for (int i = 1; i <= totalShapes; ++i ){
                JSONObject shape = (JSONObject) jsonObject.get("shape"+i);
                Drawable newShape = new Drawable();

                newShape.x = ((Long) shape.get("x")).intValue();
                newShape.y = ((Long) shape.get("y")).intValue();
                newShape.x1 = ((Long) shape.get("x1")).intValue();
                newShape.y1 = ((Long) shape.get("y1")).intValue();
                newShape.height = ((Long) shape.get("height")).intValue();
                newShape.width = ((Long) shape.get("height")).intValue();
                newShape.lineThickness = ((Long) shape.get("lineThickness")).intValue();
                newShape.isTranslated = (boolean) shape.get("isTranslated");
                newShape.isFilled = (boolean) shape.get("isFilled");
                newShape.type = ((Long) shape.get("type")).intValue();
                newShape.drawColor = new Color(((Long) shape.get("drawColor")).intValue());
                // newShape.fillColor = new Color(((Long) shape.get("fillColor")).intValue());
                newShape.translateX = ((Long) shape.get("translateX")).intValue();
                newShape.translateY = ((Long) shape.get("translateY")).intValue();

                this.addShape(newShape);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
     * Inspired by
     * https://stackoverflow.com/questions/22261130/how-to-save-a-file-using-jfilechooser-showsavedialog
     * https://www.mkyong.com/java/json-simple-example-read-and-write-json/
     * 
     */

    public void saveDrawing() {
        JSONObject obj = new JSONObject();
        obj.put("currTool", this.getCurrTool());
        obj.put("thickness", this.getCurrThickness());
        obj.put("totalShapes", this.getShapeList().size());

        int i = 0;
        for (Drawable shapeItem : this.getShapeList()) {
            ++i;
            JSONObject shape = new JSONObject();
            shape.put("x", shapeItem.x);
            shape.put("y", shapeItem.y);
            shape.put("x1", shapeItem.x1);
            shape.put("y1", shapeItem.y1);
            shape.put("height", shapeItem.height);
            shape.put("width", shapeItem.width);
            shape.put("lineThickness", shapeItem.lineThickness);
            shape.put("isFilled", shapeItem.isFilled);
            shape.put("drawColor", shapeItem.drawColor.getRGB());
            // shape.put("fillColor", shapeItem.fillColor.getRGB());
            shape.put("type", shapeItem.type);
            shape.put("translateX", shapeItem.translateX);
            shape.put("translateY", shapeItem.translateY);
            shape.put("isTranslated", shapeItem.isTranslated);

            obj.put("shape" + i, shape);
        }

        JFileChooser savefile = new JFileChooser();
        int sf = savefile.showSaveDialog(savefile);
        if (sf == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter file = new FileWriter(savefile.getSelectedFile());
                file.write(obj.toJSONString());
                file.close();
                JOptionPane.showMessageDialog(null, "File has been saved", "File Saved",
                        JOptionPane.INFORMATION_MESSAGE);
                // true for rewrite, false for override

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (sf == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "File save has been canceled");
        }
    }

    public void newDrawing() {
        this.SelectedShape = null;
        this.ShapeList.clear();
        notifyObservers();
    }
}
