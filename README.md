# Creator: Serhii Tupikin

# Description
Java application which generates mazes based on the size wanted by user. The application uses **recursive backtracking algorithm** in order to generate mazes. 

## Algorithm explanation
 Consider the space for a maze being a large grid of cells (like a large chess board), each cell starting with four walls. Starting from a random cell, the computer then selects a random neighbouring cell that has not yet been visited. The computer removes the wall between the two cells and marks the new cell as visited, and adds it to the stack to facilitate backtracking. The computer continues this process, with a cell that has no unvisited neighbours being considered a dead-end. When at a dead-end it backtracks through the path until it reaches a cell with an unvisited neighbour, continuing the path generation by visiting this new, unvisited cell (creating a new junction). This process continues until every cell has been visited, causing the computer to backtrack all the way back to the beginning cell. We can be sure every cell is visited. 

## Implementation logic
 Instead of implementing maze generation recursively, I decided to use the element of java.util Stack in order to avoid stack overflow. The maze is a 2D array containing Cell obejcts **(see [UML diagram](#uml-diagram) below)**. The user is able to select the size of the maze by specifying the number of rows and columns which 2D array will have. 

 
# Launch instructions
1. Clone the repository
```bash
git clone https://github.com/Sergey-Mr/Maze-generator.git
```
2. Navigate to the root folder:
```bash
cd Maze-generator/
```
3. Compile the application:

```bash
javac src/objects/*.java
javac --module-path lib --add-modules javafx.controls,javafx.fxml src/gui/Driver.java
```
4. Run it:
```bash
java --module-path lib --add-modules javafx.controls,javafx.fxml src.gui.Driver
```

# UML Class Diagram
<a id="uml-diagram"></a>
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

# Tehcnologies

This project was created with:

- Java 17.0.11
- JavaFX SDK 22.0.1
- Ubuntu 22.04.4 LTS