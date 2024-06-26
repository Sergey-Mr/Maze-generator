package src.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    public int rows;
    public int columns;
    public Cell[][] maze;

    public Maze(int rowsIn, int colsIn){
        this.rows = rowsIn;
        this.columns = colsIn;

        generateEmptyMaze();
    }

    public void generateEmptyMaze(){
        this.maze = new Cell[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.maze[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getUnvisitednNeighbour(int row, int column){
        // Selects the next cell to go to

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

    public void removeWall(Cell current, Cell next){
        // Removes the walls of the cells in the grid

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

    public Cell getCellByIndex(int rowIn, int columnIn){
        if (rowIn >= 0 && rowIn < this.getRows() && columnIn >= 0 && columnIn < this.getColumns()) {
            return this.getMaze()[rowIn][columnIn];
        }

        else {
            throw new IndexOutOfBoundsException("Cell index out of boundes");
        }
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public void setColumns(int colsIn){
        this.columns = colsIn;
    }

    public void setRows(int rowsIn){
        this.rows = rowsIn;
    }

    public Cell[][] getMaze(){
        return this.maze;
    }
}


