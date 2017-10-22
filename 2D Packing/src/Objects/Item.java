package Objects;

import java.util.ArrayList;

public class Item {

    public ArrayList<Cell> list = new ArrayList<>();
//    private ArrayList<Cell> finalList = new ArrayList<>();

    public Item() {}

    public Item(Item item) {
        this.list = new ArrayList(item.list);
    }

    public void addCell(Cell cell) {
        list.add(cell);
    }

    public int size() {
        return this.list.size();
    }

    public Cell get(int index) {
        return list.get(index);
    }

    public Item rotate(Item item, int direction) {
        Item newItem = new Item();
        for (int i = 0; i < item.list.size(); i++) {
            Cell cell = item.list.get(i);
            int x = cell.x;
            int y = cell.y;

            switch(direction) {
                case 0:
                    newItem.addCell(new Cell(x, y));
                    break;
                case 1:
                    newItem.addCell(new Cell(y, -x));
                    break;
                case 2:
                    newItem.addCell(new Cell(-x, -y));
                    break;
                case 3:
                    newItem.addCell(new Cell(-y, x));
                    break;
                case 4:
                    newItem.addCell(new Cell(-x, y));
                    break;
                case 5:
                    newItem.addCell(new Cell(x, -y));
                    break;
            }
        }

        return newItem;
    }

    public boolean equals(Item item1, Item item2) {
        if (item1.size() != item2.size()) {
            return false;
        }

        for (int i = 0; i < item1.size(); i++) {
            Cell cell1 = new Cell(item1.list.get(i));
            Cell cell2 = new Cell(item2.list.get(i));

            if ((cell1.x != cell2.x) || (cell1.y != cell2.y)) {
                return false;
            }
        }

        return true;
    }
}