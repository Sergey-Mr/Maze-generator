package src.objects;

public class Cell {
    public boolean topWall = true;
    public boolean bottomWall = true;
    public boolean leftWall = true;
    public boolean rightWall = true;

    public boolean visited = false;

    private int row, column; // coordinates of the cell

    public Cell(int rowIn, int columnIn){
        this.row = rowIn;
        this.column = columnIn;
    }
    
    public boolean isVisited(){
        return this.visited;
    }

    public void setVisited(){
        this.visited = true;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void setTopWall(boolean topWallIn){
        this.topWall = topWallIn;
    }

    public void setBottomWall(boolean bottomWallIn){
        this.bottomWall = bottomWallIn;
    }
    
    public void setLeftWall(boolean leftWallIn){
        this.leftWall = leftWallIn;
    }

    public void setRightWall(boolean rigthWallIn){
        this.rightWall = rigthWallIn;
    }

    public boolean hasTopWall() {
        return this.topWall;
    }

    public boolean hasRightWall() {
        return this.rightWall;
    }

    public boolean hasBottomWall() {
        return this.bottomWall;
    }

    public boolean hasLeftWall() {
        return this.leftWall;
    }


}
