package Objects;

public class Cell {
    public int x;
    public int y;
    public boolean occupied;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = true;
    }

    public int getX(Cell cell) {
        return x;
    }

    public int getY(Cell cell) {
        return y;
    }

    public Cell(int x, int y, boolean occupied) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
    }

    public Cell(Cell cell) {
        this.x = cell.x;
        this.y = cell.y;
        this.occupied = cell.occupied;
    }
}
