## Overview

JSketch is paint app written in JAVA `10.0.2` and follows MVC model. The model stores all the data and there are multiple views that get updated as the model changes.
There are 4 views:
- `ColorPalette.java`
- `LineThicknessPalette.java`
- `ToolPalette,java`
- `MyCanvas.java`

Run the command `make run` to launch the app from command line.

Some of the icons used in this app have been sourced from external website.

Licences for all the icons used in this project.
Icons made by http://www.freepik.com Freepik from Flaticon (www.flaticon.com) is licensed by http://creativecommons.org/licenses/by/3.0/ (Creative Commons BY 3.0)

Icons made by https://www.flaticon.com/authors/bqlqn bqlqn from (https://www.flaticon.com/) Flaticon is licensed by (http://creativecommons.org/licenses/by/3.0/) Creative Commons BY 3.0

Icons made by https://www.flaticon.com/authors/smashicons (Smashicons) from (https://www.flaticon.com/) Flaticon is licensed by (http://creativecommons.org/licenses/by/3.0/) Creative Commons BY 3.0

Icons made by https://www.flaticon.com/authors/pixel-perfect Pixel perfect from https://www.flaticon.com/ Flaticon uis licensed by http://creativecommons.org/licenses/by/3.0/  Creative Commons BY 3.0

## Usage
Users can select any shape, color and thickness form the palette and drag across the screen to draw shapes. They can use the select tool to choose any shape and change its properties such as color and thickness by clicking on any button from the color or line palette. Any selected shape will have a dotted border of the same color to indicate that this shape is selected. `ESC` key can be pressed to remove the selection.

Users can also save their work using the `File` menu. `Save Canvas` option can be used to export current state of the canvas as a `JSON` file. This file can be later opened up using the `Load Canvas` option. `New Canvas` option can be used to clear the canvas and start over again.

`Erase` tool can be used to delete shapes from the canvas. Select the tool and click on any shape to delete the shape from the canvas.

The tool bar on the left side is undockable. Just click below the File menu and drag and your mouse to undock it.

Click here
![toolv](/uploads/38ec66b99aaff9d4026b611f15063f02/toolv.png)

Undocked toolbar
![undocked](/uploads/9285bbd4babf7868e92b5b0a251de3d7/undocked.png)


If the user has drawn multiple overlapping shapes and they try to perform an action on the shapes, the shape that was added first will be acted upon first.
