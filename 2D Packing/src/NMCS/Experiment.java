package NMCS;
import Objects.*;

import java.util.ArrayList;
import java.util.List;

public class Experiment {

    static int X_DIMENSION = 10;
    static int Y_DIMENSION = 10;
    static int LEVEL = 0;

    public static void main(String[] args) {
//        for (int i = 0; i < 4; i++) {
//            Sheet sheet = new Sheet(10, 10);
////            sheet.display();
//
//            Item item = new Item();
//            item.addCell(new Cell(0, 0));
//            item.addCell(new Cell(1, 0));
//            item.addCell(new Cell(2, 0));
//            item.addCell(new Cell(1, 1));
//            item.addCell(new Cell(3, 0));
//            item.addCell(new Cell(3, 1));
//
//            sheet.remove(item, new Arrangement(new Coordinate(5, 5), i));
//
//            sheet.display();
//        }
//        System.out.println("test");

        Sheet sheet = new Sheet(10, 10);

        Item item1 = new Item();
        item1.addCell(new Cell(0, 0));
        item1.addCell(new Cell(1, 0));
        item1.addCell(new Cell(2, 0));
        item1.addCell(new Cell(0, 1));
        item1.addCell(new Cell(0, 2));
        item1.addCell(new Cell(1, 2));
        item1.addCell(new Cell(2, 2));


//        item1.addCell(new Cell(3, 0));
//        item1.addCell(new Cell(3, 1));

        Item item2 = new Item();
        item2.addCell(new Cell(0, 0));
        item2.addCell(new Cell(1, 0));
//        item2.addCell(new Cell(2, 0));
//        item1.addCell(new Cell(0, 1));
//        item1.addCell(new Cell(2, 1));

        Item item3 = new Item();
        item3.addCell(new Cell(0, 1));
        item3.addCell(new Cell(0, 2));
        item3.addCell(new Cell(0, 3));
        item3.addCell(new Cell(0, 4));
        item3.addCell(new Cell(0, 5));
        item3.addCell(new Cell(0, 6));
////        item1.addCell(new Cell(2, 0));
//        item3.addCell(new Cell(3, 1));
//        item3.addCell(new Cell(4, 0));
//        item3.addCell(new Cell(5, 0));

        List<Item> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(new Item(item1));
            list.add(new Item(item2));
            list.add(new Item(item3));
        }
        list.add(item1);
        list.add(item2);
        list.add(item3);

//        System.out.println("test");
        Node node = new Node(sheet, list);
//        System.out.println(node.itemList.get(0).size());
//        System.exit(0);
//        Pair pair = NMCS.iterativeNested(new Node(node), LEVEL);
//        System.out.println("score " + pair.bestScore);
    }
}
