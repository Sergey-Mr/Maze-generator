# Maze-generator
Computer app which generates random solvable mazes
 
# Launch the app
1. Navigate to the root folder .../Maze-generator
2. Compile the application:

javac src/objects/*.java
javac --module-path lib --add-modules javafx.controls,javafx.fxml src/gui/Driver.java

3. Run it:
java --module-path lib --add-modules javafx.controls,javafx.fxml src.gui.Driver

# UML diagram
![UML Class Diagram](./images/UML-class-diagram.png)

# Directory strucutre
Maze-generator
│
├── lib
│   ├── javafx.controls
│   └── javafx.fxml
│
├── src
│   ├── gui
│   │   └── Driver.java
│   └── objects
│       └── Cell.java
│       └── Maze.java
│
├── images
│   └── UML-class-diagram.png
│
└── README.md
