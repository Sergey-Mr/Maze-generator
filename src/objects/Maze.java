package src.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a maze with a grid of cells in a 2D array.
 * Each cell can be visited or unvisited and has four walls.
 */
public class Maze {
    public int rows;
    public int columns;
    public Cell[][] maze;

    /**
     * Constructs a new Maze with the given number of rows and columns.
     * @param rowsIn the number of rows in the maze
     * @param colsIn the number of columns in the maze
     */
    public Maze(int rowsIn, int colsIn){
        this.rows = rowsIn;
        this.columns = colsIn;

        generateEmptyMaze();
    }

    /**
     * Generates an empty maze with all cells unvisited and all walls intact (set to true).
     */
    public void generateEmptyMaze(){
        this.maze = new Cell[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.maze[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Gets one of unvisited neighbours of the cell at the given row and column.
     * @param row the row of the cell
     * @param column the column of the cell
     * @return an unvisited neighbour of the cell, or null if there are no unvisited neighbours
     */
    public Cell getUnvisitednNeighbour(int row, int column){
        // Contains all unvisited neighbours of the current cell
        List<Cell> unvisitedNeighbours = new ArrayList<>();

        // Check the cell above
        if (row > 0 && !maze[row - 1][column].isVisited()) {
            unvisitedNeighbours.add(maze[row - 1][column]);
        }
    
        // Check the cell below
        if (row < rows - 1 && !maze[row + 1][column].isVisited()) {
            unvisitedNeighbours.add(maze[row + 1][column]);
        }
    
        // Check the cell to the left
        if (column > 0 && !maze[row][column - 1].isVisited()) {
            unvisitedNeighbours.add(maze[row][column - 1]);
        }
    
        // Check the cell to the right
        if (column < columns - 1 && !maze[row][column + 1].isVisited()) {
            unvisitedNeighbours.add(maze[row][column + 1]);
        }
    
        // If there are any unvisited neighbors, return one at random
        if (!unvisitedNeighbours.isEmpty()) {
            int index = new Random().nextInt(unvisitedNeighbours.size());
            return unvisitedNeighbours.get(index);
        }
    
        // If there are no unvisited neighbors, return null
        return null;
    }

    /**
     * Removes the wall between the current cell and the next cell.
     * @param current the current cell
     * @param next the next cell
     */
    public void removeWall(Cell current, Cell next){
        // Get the relevant position of the next cell
        int dx = next.getColumn() - current.getColumn();
        int dy = next.getRow() - current.getRow();

        // Remove the corresponding wall of each cell
        if (dx == 1) { // Next cell is to the right
            current.setRightWall(false);
            next.setLeftWall(false);

        } else if (dx == -1) { // Next cell is to the left
            current.setLeftWall(false);
            next.setRightWall(false);

        } else if (dy == 1) { // Next cell is below
            current.setBottomWall(false);
            next.setTopWall(false);

        } else if (dy == -1) { // Next cell is above
            current.setTopWall(false);
            next.setBottomWall(false);

        }

    }

    /**
     * Gets the cell from the maze array at the given row and column.
     * @param rowIn the row of the cell
     * @param columnIn the column of the cell
     * @return the cell at the given row and column
     * @throws IndexOutOfBoundsException if the row or column is out of bounds
     */
    public Cell getCellByIndex(int rowIn, int columnIn){
        if (rowIn >= 0 && rowIn < this.getRows() && columnIn >= 0 && columnIn < this.getColumns()) {
            // If coordinates exist in the maze, return the cell
            return this.getMaze()[rowIn][columnIn];
        }
        else {
            // Throw exception otherwise
            throw new IndexOutOfBoundsException("Cell index out of boundes");
        }
    }

    /**
    * Gets the number of rows in the maze.
    * @return the number of rows in the maze
    */
    public int getRows(){
        return this.rows;
    }

    /**
    * Gets the number of columns in the maze.
    * @return the number of columns in the maze
    */
    public int getColumns(){
        return this.columns;
    }

    /**
    * Sets the number of columns in the maze.
    * @param colsIn the new number of columns in the maze
    */
    public void setColumns(int colsIn){
        this.columns = colsIn;
    }

    /**
    * Sets the number of rows in the maze.
    * @param rowsIn the new number of rows in the maze
    */
    public void setRows(int rowsIn){
        this.rows = rowsIn;
    }

    /**
    * Gets the 2D array of cells representing the maze.
    * @return the 2D array of cells representing the maze
    */
    public Cell[][] getMaze(){
        return this.maze;
    }
}


