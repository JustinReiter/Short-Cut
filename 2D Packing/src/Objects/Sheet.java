package Objects;

public class Sheet {
    public final int X_DIMENSION;
    public final int Y_DIMENSION;
    public Cell[][] grid;

    public Sheet(int X_DIMENSION, int Y_DIMENSION) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        grid = new Cell[X_DIMENSION][Y_DIMENSION];

        for (int x = 0; x < X_DIMENSION; x++) {
            for (int y = 0; y < Y_DIMENSION; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }

    public Sheet(Sheet sheet) {
        this.X_DIMENSION = sheet.X_DIMENSION;
        this.Y_DIMENSION = sheet.Y_DIMENSION;
        grid = new Cell[X_DIMENSION][Y_DIMENSION];
        for (int i = 0; i < sheet.grid.length; i++) {
//            System.arraycopy(sheet.grid[i], 0, this.grid[i], 0, sheet.grid.length);
            for (int k = 0; k < sheet.grid[0].length; k++) {
                this.grid[i][k] = new Cell(sheet.grid[i][k]);
            }
        }
    }

    //returns true if successful
    public boolean remove(Item inputItem, Arrangement arrangement) {
//        System.out.println(arrangement.direction);
        Item item = new Item(inputItem.rotate(inputItem, arrangement.direction));

        for (int i = 0; i < item.size(); i++) {
            Cell cell = item.get(i);

            int x = cell.x + arrangement.coordinate.x;
            int y = cell.y + arrangement.coordinate.y;

//            this.display();

            if (x < 0 || x >= X_DIMENSION|| y < 0 || y >= Y_DIMENSION || this.grid[x][y].occupied == false) {
//                System.out.println("failed to remove cell " + X_DIMENSION + "   " + Y_DIMENSION);
                return false;
            }

//            Cell newCell = grid[x][y];
//            newCell.occupied =
            this.grid[x][y].occupied = false;
        }
//        this.display();
        return true;
    }

    public void display() {
        for (int y = Y_DIMENSION - 1; y >= 0; y--) {
            for (int x = 0; x < X_DIMENSION; x++) {
                if (grid[x][y].occupied) {
                    System.out.print("X ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isEdge(int x, int y) {
        if (x == 0 || x == X_DIMENSION - 1 || y == 0 || y == Y_DIMENSION - 1) return true;

        if (grid[x][y + 1].occupied == false || grid[x + 1][y].occupied == false || grid[x][y - 1].occupied == false || grid[x - 1][y].occupied == false) return true;
        return false;
    }
}
