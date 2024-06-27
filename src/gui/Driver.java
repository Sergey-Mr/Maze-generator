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
import javafx.scene.shape.Line;
import javafx.event.EventHandler;

import java.util.Stack; 

import src.objects.*;

/**
 * The main driver class for the Maze application.
 * This class extends the JavaFX Application class and overrides the start method.
 */
public class Driver extends Application {

    /**
     * The main entry point for the applications.
     * The start method is called after the system is ready for the application to begin running.
     * @param primaryStage the primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // SplitPane for entire window
        SplitPane layout = new SplitPane();

        // Separate panes for maze and control sections
        Pane mazePane = new Pane();
        VBox controlBox = new VBox();
        
        // Add spacing between every element of the control box
        controlBox.setSpacing(10);

        // Labels for rows and columns inputs
        Label rowsLabel = new Label("Enter number of rows");
        Label colsLabel = new Label("Enter number of columns");

        // Rows and columns input for user
        TextField rowsInput = new TextField();
        TextField colsInput = new TextField();

        // Ensure that only integers can be entered in textfields
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

        // Button to generate a maze
        Button generateButton = new Button();
        generateButton.setText("Generate New Maze");

        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get text input from user
                String rowsText = rowsInput.getText();
                String colsText = colsInput.getText();
                
                // Check if the inputs are empty or zero
                if (rowsText.isEmpty() || colsText.isEmpty() || rowsText.equals("0") || colsText.equals("0")) {
                    // If true, show warning
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter numbers greater than 0 to generate the maze!");
                
                    alert.showAndWait();
                }
                else{
                    // Otherwise, convert input to integers
                    int rows = Integer.parseInt(rowsInput.getText());
                    int cols = Integer.parseInt(colsInput.getText());
    
                    // Generate a new maze
                    generateMaze(rows, cols, mazePane);
                }


            }
        });

        // Assemble the controlBox
        controlBox.getChildren().addAll(rowsLabel, rowsInput, colsLabel, colsInput, generateButton);
        controlBox.setAlignment(Pos.CENTER);

        // Add panes to the SplitPane
        layout.getItems().addAll(mazePane, controlBox);

        // Set the divider position
        layout.setDividerPositions(0.75);

        // Create a new scene and show the window
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setTitle("Maze generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Generates a new maze with the given number of rows and columns.
     * @param rowsIn the number of rows in the maze
     * @param colsIn the number of columns in the maze
     * @param root the Pane in which to display the maze
     */
    private void generateMaze(int rowsIn, int colsIn, Pane root){
        // Set up the maze
        Maze myMaze = new Maze(rowsIn, colsIn);

        // Start with the cell at position (0, 0)
        Cell start = myMaze.getCellByIndex(0, 0);
        Stack<Cell> stack = new Stack<>();
        stack.push(start);
        start.setVisited();  // change status of the first cell to visited

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
        
            // Find unvisited cell
            Cell next = myMaze.getUnvisitednNeighbour(current.getRow(), current.getColumn());
            if (next != null) {
                // If exists, remove the wall, push to the stack
                myMaze.removeWall(current, next);
                stack.push(next);
                next.setVisited();

            } else {
                // If doesn`t, backtrack until unvisited cell found
                stack.pop();
            }
        }

        // Draw the maze on a given pane
        drawMaze(myMaze, root, colsIn, rowsIn);
    }

    /**
     * Draws the given maze in the given Pane.
     * @param myMaze the maze to draw
     * @param root the Pane in which to draw the maze
     * @param cols the number of columns in the maze
     * @param rows the number of rows in the maze
     */
    private void drawMaze(Maze myMaze, Pane root, int cols, int rows) {
        // Clear the previous drawing
        root.getChildren().clear();
    
        // Calculate the size of the cells based on number of rows and columns
        GridPane grid = new GridPane();
        double cellSize = Math.min(root.getWidth() / cols, (root.getHeight()-20) / rows);
        grid.setHgap(cellSize);
        grid.setVgap(cellSize);
    
        // Loop over the cells in the maze and draw walls
        for (int row = 0; row < myMaze.getRows(); row++) {
            for (int col = 0; col < myMaze.getColumns(); col++) {
                Cell cell = myMaze.getCellByIndex(row, col);

                // Draw top wall if it exists ((xstart, ystart), (xend, yend))
                if (cell.hasTopWall()) {
                    Line topWall = new Line(col * cellSize, row * cellSize, (col + 1) * cellSize, row * cellSize);
                    topWall.setStrokeWidth(2);
                    root.getChildren().add(topWall);
                }

                // Draw right wall if it exists
                if (cell.hasRightWall()) {
                    Line rightWall = new Line((col + 1) * cellSize, row * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
                    rightWall.setStrokeWidth(2);
                    root.getChildren().add(rightWall);
                }

                // Draw bottom wall if it exists
                if (cell.hasBottomWall()) {
                    Line bottomWall = new Line(col * cellSize, (row + 1) * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
                    bottomWall.setStrokeWidth(2);
                    root.getChildren().add(bottomWall);
                }

                // Draw left wall if it exists
                if (cell.hasLeftWall()) {
                    Line leftWall = new Line(col * cellSize, row * cellSize, col * cellSize, (row + 1) * cellSize);
                    leftWall.setStrokeWidth(2);
                    root.getChildren().add(leftWall);
                }
            }
        }
    
        // Add the pane to the root node of the scene
        root.getChildren().add(grid);
    }

    /**
     * The main method for the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}