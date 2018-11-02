# CS349 A2
Student: a289sing
Marker: Bahareh Sarrafzadeh


Total: 65 / 100 (65.00%)

Code: 
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes:   


## FUNCTIONAL REQUIREMENTS (60)

1. [5/5] Saving and loading data. File-Save can be used to save the model and File-Load can be used to load and restore the shapes that were saved. The user should be promoted for a file-save location using a JDialog.

2. [2/2] File-New can be used to discard the current document and create a new blank document.

3. [1/3] Selection tool. The user can select this tool in the toolbar then click on a shape to select it. There should be some visual indication which shape is selected. Pressing ESC on the keyboard will clear shape selection.

-1 Selection is unreliable (sometimes clicking within a shape doesn't select it, or clicking outside of a shape selects something) 
-1 Sometimes clicking on a shape to select it causes multiple shapes to jump to another side of the canvas!

4. [1/2] Erase tool. The user can select this tool in the toolbar then click on a shape to delete it.

-1 Erase tool works only sometimes

5. [2/4] Line drawing tool. The user can select this tool in the toolbar and then draw lines in the canvas by holding and dragging the mouse.

-2 A preview of the line does not appear while the mouse button is being dragged

6. [2/4] Rectangle drawing tool. The user can select this tool in the toolbar and then draw rectangles in the canvas by holding and dragging the mouse.

-2 A preview of the rectangle does not appear while the mouse button is being dragged

7. [0/4] Circle drawing tool. The user can select this tool in the toolbar and then draw circles or ellipses in the canvas by holding and dragging the mouse.

-2 Circle drawing doesn't work as expected (the first corner [or centre] of the circle/ellipse should correspond to the position of mouse press, and the second corner [or radius] should correspond to the position of the mouse release)
-2 A preview of the circle/ellipse does not appear while the mouse button is being dragged

8. [2/3] Fill tool: The user can select this tool in the toolbar and then click on a shape (rectangle or circle/ellipse) to fill it with the current colour.

-1 Fill tool doesn't always work

9. [6/7] Colour palette. Displays at least 6 colors in a graphical view, which the user can select to set the property for drawing a new shape.

-1 Sometimes multiple clicks are required to pick a color. The UI is slow and the interaction is not smooth in general.

10. [2/3] Colour picker. The user can click on the "Chooser" button to bring up a colour chooser dialog to select a colour.

-1 There is no visual indication of the selected colour

11. [7/7] Line thickness palette. Displays at least 3 line widths, graphically, and allows the user to select line width for new shapes.

12. [2/2] Selecting a shape will cause the color palette and line thickness palette to update their state to reflect the currently selected shape.

13. [3/10] Moving shapes. Users can move shapes around the screen by selecting, then dragging them. There should be an indication which shape is selected. 

-5 Movement is not smooth (for example, the shape jumps around while being moved)
-2 Overlapping shapes are very difficult to select and hence it's not possible to test how they can be moved.

14. [2/2] The interface should clearly indicate which tool, color and line thickness are selected at any time.

15. [1/2] The interface should enable/disable controls if appropriate.

-1 There was at least one situation when a control could not be used, but it was not disabled:
When New is selected from the File menue the Toolbars are not cleared and still indicate the properties of a recently selected shape.


## LAYOUT (15)

1. [1/1] The starting appication window is no larger than 1600x1200.

-1 The application starts with a window that is larger than 1600x1200

2. [2/4] Swing widgets are used appropriately to implement the window layout.

-2 There was an issue with the layout (for example, some controls were difficult to use or incorrectly positioned)
Very hard to select shapes, clicking on the toolbar is very flakey, etc.

3. [5/5] The application should support dynamic resizing of the window. The tool palettes should expand and contract based upon available space, and they should remain usable at all times.

4. [0/5] If the user resizes the window smaller than the canvas, scrollbars should appear.

-5 Scrollbars do not appear when the window is smaller than the drawing


## TECHNICAL REQUIREMENTS AND MVC (15)

1. [5/5] Makefile exists, `make` and `make run` works successfully with default arguments.

2. [5/10] Model-View-Controller (MVC) used effectively (i.e. shape model contained in a model class, at least two views used to represent the canvas and/or toolbars).

-5 There is only one view


## ADDITIONAL/BONUS FEATURES (10 + 10 optional bonus)

1. [9/10] Application incorporates one or more enhancements totaling 10%, as described in the requirements.

+10 Undockable toolbars: toolbars can be undocked to create floating palettes, which can be repositioned around the screen.
-1 Creating a New canvas doesnt reset the toolbars

2. [0/10] Bonus feature: Resizing. The user can toggle between scaling the canvas full-size, or scaling the canvas and its contents to the window. The "Full size" mode is like the default (the canvas and its contents remain the same size as you resize the window, and scrollbars appear to let you scroll the image). In the "Fit to Window" mode, the canvas and its contents resize to fit the window as the window is resized.
