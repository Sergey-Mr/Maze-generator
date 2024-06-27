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
Maze-generator<br>
│<br>
├── lib<br>
│   ├── javafx.controls<br>
│   └── javafx.fxml<br>
│<br>
├── src<br>
│   ├── gui<br>
│   │   └── Driver.java<br>
│   └── objects<br>
│       └── Cell.java<br>
│       └── Maze.java<br>
│<br>
├── images<br>
│   └── UML-class-diagram.png<br>
│<br>
└── README.md<br>
