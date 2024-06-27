package src.objects;

/**
 * Represents a cell in a maze.
 * Each cell has four walls, coordinates and can be visited or unvisited.
 */
public class Cell {
    public boolean topWall = true;
    public boolean bottomWall = true;
    public boolean leftWall = true;
    public boolean rightWall = true;

    public boolean visited = false;

    private int row, column; // coordinates

    /**
     * Constructs a new Cell with the given row and column in the maze`s array.
     * @param rowIn the row of the cell
     * @param columnIn the column of the cell
     */
    public Cell(int rowIn, int columnIn){
        this.row = rowIn;
        this.column = columnIn;
    }
    
    /**
     * Checks if the cell has been visited.
     * @return true if the cell has been visited, false otherwise
     */
    public boolean isVisited(){
        return this.visited;
    }

    /**
     * Marks the cell as visited.
     */
    public void setVisited(){
        this.visited = true;
    }

    /**
     * Gets the row of the cell.
     * @return the row of the cell
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Gets the column of the cell.
     * @return the column of the cell
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Sets the top wall of the cell.
     * @param topWallIn the new state of the top wall
     */
    public void setTopWall(boolean topWallIn){
        this.topWall = topWallIn;
    }

    /**
     * Sets the bottom wall of the cell.
     * @param bottomWallIn the new state of the bottom wall
     */
    public void setBottomWall(boolean bottomWallIn){
        this.bottomWall = bottomWallIn;
    }
    
    /**
     * Sets the left wall of the cell.
     * @param leftWallIn the new state of the left wall
     */
    public void setLeftWall(boolean leftWallIn){
        this.leftWall = leftWallIn;
    }

    /**
     * Sets the right wall of the cell.
     * @param rigthWallIn the new state of the right wall
     */
    public void setRightWall(boolean rigthWallIn){
        this.rightWall = rigthWallIn;
    }

    /**
     * Checks if top wall of the cell is set
     * @return true if the wall is set, false otherwise
     */
    public boolean hasTopWall() {
        return this.topWall;
    }

    /**
     * Checks if right wall of the cell is set
     * @return true if the wall is set, false otherwise
     */
    public boolean hasRightWall() {
        return this.rightWall;
    }

    /**
     * Checks if bottom wall of the cell is set
     * @return true if the wall is set, false otherwise
     */
    public boolean hasBottomWall() {
        return this.bottomWall;
    }

    /**
     * Checks if left wall of the cell is set
     * @return true if the wall is set, false otherwise
     */
    public boolean hasLeftWall() {
        return this.leftWall;
    }


}
