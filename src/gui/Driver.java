package src.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Stack; 

import src.objects.*;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        //VBox layout = new VBox();
        BorderPane layout = new BorderPane();

        //StackPane root = new StackPane();
        //Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        // Create a new scene with the dimensions of the screen
        Pane root = new Pane();

        // Set up parameters of the maze
        int rows = 50;
        int cols = 50;

        //generateMaze(rows, cols, root);

        // Create a new Button instance
        Button generateButton = new Button();

        // Set the button's text
        generateButton.setText("Generate New Maze");

        // Add an action listener to the button
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Generate a new maze
                generateMaze(rows, cols, root);
            }
        });

        // Add the button to the root node
        // root.getChildren().add(generateButton);

        // Add the button and the root Pane to the VBox layout
        //layout.getChildren().addAll(generateButton, root);

        // Add the button to the top of the BorderPane layout
        layout.setTop(generateButton);
        BorderPane.setAlignment(generateButton, Pos.CENTER);

        // Add the mazePane to the center of the BorderPane layout
        layout.setCenter(root);
        
        //layout.setAlignment(Pos.CENTER);

        //Scene scene = new Scene(new Pane(), screenBounds.getWidth(), screenBounds.getHeight());

        // Create a new scene with the VBox layout as the root
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setTitle("Maze generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateMaze(int rowsIn, int colsIn, Pane root){
        // Clear the root Pane
        root.getChildren().clear();
        // Set up the maze and populate with unvisisted cells
        Maze myMaze = new Maze(rowsIn, colsIn);

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

        drawMaze(myMaze, root, colsIn, rowsIn);
    }

    private void drawMaze(Maze myMaze, Pane root, int cols, int rows) {
        // Clear the previous drawing
        root.getChildren().clear();
    
        // Create and configure the GridPane
        GridPane grid = new GridPane();
        //double cellSize = 20.0;
        double cellSize = Math.min(root.getWidth() / cols, (root.getHeight()-20) / rows);
        grid.setHgap(cellSize);
        grid.setVgap(cellSize);
    
        // Loop over the cells in the maze and add Rectangles to the GridPane
        for (int row = 0; row < myMaze.rows; row++) {
            for (int col = 0; col < myMaze.columns; col++) {
                Cell cell = myMaze.getCellByIndex(row, col);
                Rectangle rect = new Rectangle(cellSize, cellSize);
                rect.setFill(Color.TRANSPARENT);
    
                //if (cell.isVisited()) {
                //    rect.setFill(Color.WHITE);
                //} else {
                //    rect.setFill(Color.BLACK);
                //}
    
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

        // Center the maze in the StackPane
        root.setLayoutX((root.getWidth() - myMaze.columns * cellSize) / 2);
        root.setLayoutY((root.getHeight() - myMaze.rows * cellSize) / 2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}