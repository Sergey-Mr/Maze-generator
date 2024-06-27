package src.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;

import java.util.Stack; 

import src.objects.*;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Create a SplitPane layout
        SplitPane layout = new SplitPane();

        // Create a new scene with the dimensions of the screen
        Pane mazePane = new Pane();
        VBox controlBox = new VBox();
        
        // Add spacing between every element of the control box
        controlBox.setSpacing(10);

        // Create new Label instances for rows and columns input
        Label rowsLabel = new Label("Enter number of rows");
        Label colsLabel = new Label("Enter number of columns");

        // Create new TextField instances for rows and columns input
        TextField rowsInput = new TextField();
        TextField colsInput = new TextField();

        // Add a text property listener to ensure only integers can be entered
        rowsInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rowsInput.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        colsInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                colsInput.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Create a new Button instance
        Button generateButton = new Button();

        // Set the button's text
        generateButton.setText("Generate New Maze");

        // Add an action listener to the button
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String rowsText = rowsInput.getText();
                String colsText = colsInput.getText();
                
                // Check if the inputs are empty or zero
                if (rowsText.isEmpty() || colsText.isEmpty() || rowsText.equals("0") || colsText.equals("0")) {
                    // Show a warning
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter numbers greater than 0 to generate the maze!");
                
                    alert.showAndWait();
                }
                else{
                    // Get the number of rows and columns from the input fields
                    int rows = Integer.parseInt(rowsInput.getText());
                    int cols = Integer.parseInt(colsInput.getText());
    
                    // Generate a new maze
                    generateMaze(rows, cols, mazePane);
                }


            }
        });

        // Add the labels, input fields and button to the VBox
        controlBox.getChildren().addAll(rowsLabel, rowsInput, colsLabel, colsInput, generateButton);
        controlBox.setAlignment(Pos.CENTER);

        // Add the mazePane and buttonBox to the SplitPane
        layout.getItems().addAll(mazePane, controlBox);

        // Set the divider position so the buttonBox takes up 1/4 of the width
        layout.setDividerPositions(0.75);

        // Create a new scene with the SplitPane layout as the root
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
        double cellSize = Math.min(root.getWidth() / cols, (root.getHeight()-20) / rows);
        grid.setHgap(cellSize);
        grid.setVgap(cellSize);
    
        // Loop over the cells in the maze and add Rectangles to the GridPane
        for (int row = 0; row < myMaze.rows; row++) {
            for (int col = 0; col < myMaze.columns; col++) {
                Cell cell = myMaze.getCellByIndex(row, col);
                Rectangle rect = new Rectangle(cellSize, cellSize);
                rect.setFill(Color.TRANSPARENT);
    
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