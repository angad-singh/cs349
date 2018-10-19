
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
    private int currTool;
    private Color drawColor = Color.BLACK;
    private Color fillColor = Color.BLACK;
    private int thickness;

    /** The observers that are watching this model for changes. */
    private ArrayList<Observer> observers;
    // list of shapes drawn on the canvas
    private ArrayList<Drawable> ShapeList = new ArrayList<Drawable>();
    // currently selected shape
    private Drawable SelectedShape;

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    public void addShape(Drawable shape) {
        this.ShapeList.add(shape);
        // notifyObservers();
    }

    // set currently selected tool
    public void setCurrTool(int i) {
        this.currTool = i;
    }

    // get currently selected tool
    public int getCurrTool() {
        return this.currTool;
    }

    // get all the shapes currently on canvas
    public ArrayList<Drawable> getShapeList() {
        return this.ShapeList;
    }

    public Drawable getSelectedShape() {
        return this.SelectedShape;
    }

    // clear the previuosly seletected shape
    public void resetSelectedShape() {
        this.SelectedShape = null;
        for (Drawable shapeItem : this.getShapeList()) {
            shapeItem.isSelected = false;
        }
        notifyObservers();
    }

    // get currently selected drawing color
    public Color getCurrDrawColor() {
        return this.drawColor;
    }

    public Color getCurrFillColor() {
        return this.fillColor;
    }

    // set the currently selected draw color
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

    // hit test for rectangle to check if current point is inside or not
    public boolean rectHitTest(Point current, Drawable shapeItem) {
        return ((current.x <= shapeItem.x + shapeItem.width) && (current.x >= shapeItem.x)
                && (current.y <= shapeItem.y + shapeItem.height) && (current.y >= shapeItem.y));
    }

    // hit test for circle to check if current point is inside or not
    public boolean circleHitTest(Point current, Drawable shapeItem) {
        Point center = new Point(shapeItem.x, shapeItem.y);
        double radius = (int) Math.hypot(Math.abs(shapeItem.x - shapeItem.x1), Math.abs(shapeItem.y - shapeItem.y1));
        double dist = Math.hypot(center.x - current.x, center.y - current.y);
        return dist <= radius;
    }

    // hit test for line to check if current point is within 5 pixels or not
    public boolean lineHitTest(Point current, Drawable shapeItem) {
        double mouseDist = Line2D.ptSegDist(shapeItem.x, shapeItem.y, shapeItem.x1, shapeItem.y1, current.x, current.y);
        return mouseDist <= 5.00;
    }

    // set the selected shape's color to current color
    public void setSelectedShapeColor(Color c) {
        if (this.getCurrTool() == 4) {
            Drawable selectedShape = this.getSelectedShape();
            if (selectedShape != null) {
                selectedShape.drawColor = c;
            }
        }
    }

    // set the selected shape's thickness to current thickness
    public void setSelectedShapeThickness(int thickness) {
        if (this.getCurrTool() == 4) {
            Drawable selectedShape = this.getSelectedShape();
            if (selectedShape != null) {
                selectedShape.lineThickness = thickness;
            }
        }
    }

    // delete the shapes that are clicked on when erase tool is selected
    // break is used to break out of the loop and only delete one shape
    // when there are overlapping shapes
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

    // fill the shapes that are clicked on when fill tool is selected
    // break is used to break out of the loop and only fill one shape
    // when there are overlapping shapes
    public void fillShapes(Point current) {

        Iterator<Drawable> it = ShapeList.iterator();

        while (it.hasNext()) {
            // FILL rectangles
            Drawable shapeItem = it.next();
            if (shapeItem.type == 1) {
                if (this.rectHitTest(current, shapeItem)) {
                    shapeItem.isFilled = true;
                    // shapeItem.drawColor = this.getCurrDrawColor();
                    shapeItem.fillColor = this.getCurrFillColor();
                    notifyObservers();
                    break;
                }
            }
            // FILL circles
            else if (shapeItem.type == 2) {
                if (this.circleHitTest(current, shapeItem)) {
                    shapeItem.isFilled = true;
                    // shapeItem.drawColor = this.getCurrDrawColor();
                    shapeItem.fillColor = this.getCurrFillColor();
                    notifyObservers();
                    break;
                }
            }
        }
    }

    // select the shapes that are clicked on when select tool is selected
    // break is used to break out of the loop and only select one shape
    // when there are overlapping shapes
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
     *
     * Load an already saved json file.
     */
    public void loadDrawing() {
        JSONParser parser = new JSONParser();

        JFileChooser openfile = new JFileChooser();
        // open the dialogue box
        int sf = openfile.showOpenDialog(openfile);
        // if user pressed anyhting other than okay, return
        if (sf != JFileChooser.APPROVE_OPTION) {
            return;
        }
        // open the selected file
        File file = openfile.getSelectedFile();
        // clear the canvase
        this.newDrawing();

        try {

            Object obj = parser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) obj;
            // update the current tool and thickness
            this.setCurrThickness(((Long) jsonObject.get("thickness")).intValue());
            this.setCurrTool(((Long) jsonObject.get("currTool")).intValue());
            int totalShapes = ((Long) jsonObject.get("totalShapes")).intValue();

            // load all the shapes
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
     * Save current canvas to a JSON file.
     */

    public void saveDrawing() {
        // add current state of the model to a JSON object.
        JSONObject obj = new JSONObject();
        obj.put("currTool", this.getCurrTool());
        obj.put("thickness", this.getCurrThickness());
        obj.put("totalShapes", this.getShapeList().size());

        int i = 0;
        // add all the shapes to the JSON object.
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

        // open the Save dialogue box
        JFileChooser savefile = new JFileChooser();
        int sf = savefile.showSaveDialog(savefile);
        // if the user pressed Save button
        if (sf == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter file = new FileWriter(savefile.getSelectedFile());
                file.write(obj.toJSONString());
                file.close();
                JOptionPane.showMessageDialog(null, "File has been saved", "File Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // if the user decided to not save the file
        } else if (sf == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "File save has been canceled");
        }
    }

    // clear the canvas
    public void newDrawing() {
        this.SelectedShape = null;
        this.ShapeList.clear();
        notifyObservers();
    }
}
