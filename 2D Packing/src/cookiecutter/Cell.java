package cookiecutter;


public class Cell {
    private final int x;
    private final int y;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX(Cell cell) {
        return x;
    }
    
    public int getY(Cell cell) {
        return y;
    }
}
