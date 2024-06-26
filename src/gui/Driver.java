package src.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

import java.util.Stack; 

import src.objects.*;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Create a new scene with the dimensions of the screen
        Scene scene = new Scene(new Pane(), screenBounds.getWidth(), screenBounds.getHeight());
        Pane root = (Pane) scene.getRoot();

        // Set up parameters of the maze
        int rows = 25;
        int cols = 25;
        
        // Set up the maze and populate with unvisisted cells
        Maze myMaze = new Maze(rows, cols);
        
        // Start with the cell at position (0, 0)
        Cell start = myMaze.getCellByIndex(0, 0);
        Stack<Cell> stack = new Stack<>();
        stack.push(start);
        start.setVisited();  // change status to visited

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
        
            // Find not visited cell
            Cell next = myMaze.getUnvisitednNeighbour(current.getRow(), current.getColumn());
            if (next != null) {
                // If exists, remove the wall, push to the stack
                myMaze.removeWall(current, next);
                stack.push(next);
                next.setVisited();
            } else {
                // If doesn`t, backtrack
                stack.pop();
            }
        }

        printMaze(myMaze);
        drawMaze(myMaze, root);

        primaryStage.setTitle("Maze generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void printMaze(Maze myMaze) {
        for (int row = 0; row < myMaze.rows; row++) {
            for (int col = 0; col < myMaze.columns; col++) {
                Cell cell = myMaze.getCellByIndex(row, col);
                if (cell.isVisited()) {
                    System.out.print("V ");  // V for visited
                } else {
                    System.out.print("U ");  // U for unvisited
                }
            }
            System.out.println();  // New line after each row
        }
    }

    private void drawMaze(Maze myMaze, Pane root) {
        // Clear the previous drawing
        root.getChildren().clear();
    
        // Create and configure the GridPane
        GridPane grid = new GridPane();
        double cellSize = 20.0;
        grid.setHgap(cellSize);
        grid.setVgap(cellSize);
    
        // Loop over the cells in the maze and add Rectangles to the GridPane
        for (int row = 0; row < myMaze.rows; row++) {
            for (int col = 0; col < myMaze.columns; col++) {
                Cell cell = myMaze.getCellByIndex(row, col);
                Rectangle rect = new Rectangle(cellSize, cellSize);
    
                if (cell.isVisited()) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.BLACK);
                }
    
                // Draw walls
                if (cell.hasTopWall()) {
                    Line topWall = new Line(col * cellSize, row * cellSize, (col + 1) * cellSize, row * cellSize);
                    topWall.setStrokeWidth(2);
                    root.getChildren().add(topWall);
                }
                if (cell.hasRightWall()) {
                    Line rightWall = new Line((col + 1) * cellSize, row * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
                    rightWall.setStrokeWidth(2);
                    root.getChildren().add(rightWall);
                }
                if (cell.hasBottomWall()) {
                    Line bottomWall = new Line(col * cellSize, (row + 1) * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
                    bottomWall.setStrokeWidth(2);
                    root.getChildren().add(bottomWall);
                }
                if (cell.hasLeftWall()) {
                    Line leftWall = new Line(col * cellSize, row * cellSize, col * cellSize, (row + 1) * cellSize);
                    leftWall.setStrokeWidth(2);
                    root.getChildren().add(leftWall);
                }
    
                grid.add(rect, col, row);
            }
        }
    
        // Add the GridPane to the root node of the scene
        root.getChildren().add(grid);
    }


    public static void main(String[] args) {
        launch(args);
    }
}