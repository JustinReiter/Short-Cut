package NMCS;

import Objects.Arrangement;
import Objects.Coordinate;
import Objects.Item;
import Objects.Sheet;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int sheetCount;
    int score;
    Sheet sheet;
    List<Item> itemList;
    List<Item> options;
    List<Action> children;

    public Node(Node node) {
        this.sheetCount = node.sheetCount;
        this.score = node.score;
        this.sheet = new Sheet(node.sheet);
        this.itemList = new ArrayList(node.itemList);
        this.options = new ArrayList(node.options);
//        if (node.children.size() > 0) {
//            this.children = new ArrayList(node.children);
//        }
    }

    public Node(Sheet sheet, List<Item> itemList) {
        this.sheetCount = 0;
        this.score = 0;
        this.sheet = new Sheet(sheet);
        this.itemList = new ArrayList(itemList);


        List<Item> tempList = new ArrayList(itemList);

        for (int i = 0; i < tempList.size(); i++) {
            Item item = new Item(tempList.get(i));
            for (int k = i + 1; k < tempList.size(); k++) {
                if (item.equals(item, tempList.get(k))) {
                    tempList.remove(k);
                    k--;
                }
            }
        }

        this.options = new ArrayList(tempList);
//        System.out.println("size of options: " + this.options.size());
//        System.exit(0);
    }

    public Node(Node node, int i) {
        this.sheetCount = node.sheetCount;
        this.score = node.score;
        this.sheet = new Sheet(node.sheet);
        this.itemList = new ArrayList(node.itemList);
        this.options = new ArrayList(node.options);
        if (node.children.size() > 0) {
            this.children = new ArrayList(node.children);
        }
        child(this, node.children.get(i));
    }

    public static void child(Node node, Action action) {
//        node.score--;

        Item item = action.item;
        Arrangement arrangement = action.arrangement;

//        for (int i = 0; i < item.list.size(); i++) {
//            int x = item.list.get(i).x;
//            int y = item.list.get(i).y;
//            if (x == 0 || x == node.sheet.X_DIMENSION - 1) {
//                node.score -= 1;
//            }
//
//            if (y == 0 || y == node.sheet.Y_DIMENSION - 1) {
//                node.score -= 1;
//            }
//
//            if ((x == 0 || x == node.sheet.X_DIMENSION - 1) || (y == 0 || y == node.sheet.Y_DIMENSION - 1)) {
//                continue;
//            }
//
//            if (node.sheet.grid[x][y + 1].occupied || node.sheet.grid[x + 1][y].occupied || node.sheet.grid[x - 1][y].occupied || node.sheet.grid[x][y - 1].occupied) {
//                node.score--;
//            }
//        }

        node.removeItem(node, item);
//        System.out.println(node.itemList.size());
//        System.out.println();
        node.sheet.remove(item, arrangement);
//        node.sheet.display();

    }

    public static boolean hasChildren(Node node) {
        List<Item> tempList = new ArrayList(node.itemList);

        for (int i = 0; i < tempList.size(); i++) {
            Item item = new Item(tempList.get(i));
            for (int k = i + 1; k < tempList.size(); k++) {
                if (item.equals(item, tempList.get(k))) {
                    tempList.remove(k);
                    k--;
                }
            }
        }

        node.options = new ArrayList(tempList);

        if (node.itemList.size() == 0) return false;

        for (int x = 0; x < node.sheet.X_DIMENSION; x++) {
            for (int y = 0 ; y < node.sheet.Y_DIMENSION; y++) {
                for (Item item : node.options) {
                    for (int i = 0; i < 6; i++) {
                        Sheet sheet = new Sheet(node.sheet);
                        if (sheet.remove(item, new Arrangement(new Coordinate(x, y), i))) {
                            return true;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < node.sheet.X_DIMENSION; x++) {
            for (int y = 0; y < node.sheet.Y_DIMENSION; y++) {
                if (x == 0 || x == node.sheet.X_DIMENSION - 1) {
                    node.score -= 1;
                }

                if (y == 0 || y == node.sheet.Y_DIMENSION - 1) {
                    node.score -= 1;
                }

                if ((x == 0 || x == node.sheet.X_DIMENSION - 1) || (y == 0 || y == node.sheet.Y_DIMENSION - 1)) {
                    continue;
                }

                if (node.sheet.grid[x][y + 1].occupied || node.sheet.grid[x + 1][y].occupied || node.sheet.grid[x - 1][y].occupied || node.sheet.grid[x][y - 1].occupied) {
                    node.score--;
                }
            }
        }

        //New sheet
        node.sheetCount++;
        node.score -= 100000;
        node.sheet = new Sheet(node.sheet.X_DIMENSION, node.sheet.Y_DIMENSION);
        return true;
    }

    public static void gatherChildren(Node node) {
        node.children = new ArrayList();

        for (int x = 0; x < node.sheet.X_DIMENSION; x++) {
            for (int y = 0; y < node.sheet.Y_DIMENSION; y++) {
//                if ((x == 0 || x == node.sheet.X_DIMENSION - 1) && (y == 0 || y == node.sheet.Y_DIMENSION)) {
                if (node.sheet.isEdge(x, y)) {
                    for (Item item : node.options) {
                        for (int i = 0; i < 6; i++) {
                            Sheet sheet = new Sheet(node.sheet);
                            Arrangement arrangement = new Arrangement(new Coordinate(x, y), i);
                            if (sheet.remove(item, arrangement)) {
//                            System.out.println("solution found");
//                            System.exit(0);
                                node.children.add(new Action(item, arrangement));
                            }
                        }
                    }
                }
            }
        }
//
        if (node.children.size() == 0) {
            for (int x = 0; x < node.sheet.X_DIMENSION; x++) {
                for (int y = 0; y < node.sheet.Y_DIMENSION; y++) {
//                    if (!((x == 0 || x == node.sheet.X_DIMENSION - 1) && (y == 0 || y == node.sheet.Y_DIMENSION))) {
//                    if (!node.sheet.isEdge(x, y)) {
                        for (Item item : node.options) {
                            for (int i = 0; i < 6; i++) {
                                Sheet sheet = new Sheet(node.sheet);
                                Arrangement arrangement = new Arrangement(new Coordinate(x, y), i);
                                if (sheet.remove(item, arrangement)) {
//                            System.out.println("solution found");
//                            System.exit(0);
                                    node.children.add(new Action(item, arrangement));
                                }
                            }
                        }
//                    }
                }
            }
        }
    }

    public static int numberOfChildren(Node node) {
        node.gatherChildren(node);
        return node.children.size();
    }

    public static Action randomMove(Node node) {
        return node.children.get(NMCS.randomNum.nextInt(node.children.size()));
    }

    public static void removeItem(Node node, Item item) {
        for (int i = 0; i < node.itemList.size(); i++) {
            Item currentItem = node.itemList.get(i);
            if (item.equals(currentItem, item)) {
                node.itemList.remove(i);
                return;
            }
        }
        System.out.println("No item found to remove");
        System.exit(0);
    }
}
