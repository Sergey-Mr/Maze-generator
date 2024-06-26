package src.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.Stack; 

import src.objects.*;

//import javafx.event.ActionEvent; 
//import javafx.event.EventHandler; 
//import javafx.scene.control.*;  
//import javafx.scene.control.Alert.AlertType; 
//import java.time.LocalDate; 

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Create a new scene with the dimensions of the screen
        Scene scene = new Scene(new Pane(), screenBounds.getWidth(), screenBounds.getHeight());

        // Set up parameters of the maze
        int rows = 25;
        int cols = 25;
        
        // Set up the maze and populate with unvisisted cells
        

        primaryStage.setTitle("Maze generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}