package NMCS;

import Objects.*;

import java.util.ArrayList;
import java.util.List;

public class InputOutput {
    public static ArrayList<BoardCuts> cutHistory(ArrayList<Item> itemList) {
        Node node = new Node(new Sheet(10, 10), itemList);

        Variation variation = NMCS.IteratedNested(node, 3);

        System.out.println("variation length: " + variation.sequence.size());
        Node badNode = new Node(node);
        for (int i = 0; i < variation.sequence.size(); i++) {
            Node.hasChildren(badNode);
            Node.child(badNode, variation.sequence.get(i));
//            System.out.println(badNode.sheetCount);
        }

        int numberOfSheets = badNode.sheetCount;
//        System.out.println("number" + numberOfSheets);

//        boolean[][][][] cutHistory = new boolean[variation.sequence.size()][node.sheet.X_DIMENSION][node.sheet.Y_DIMENSION][badNode.sheetCount + 1];

        ArrayList<BoardCuts> cutHistory = new ArrayList();

        BoardCuts boardCuts = new BoardCuts();
        boolean[][] newBoolean = new boolean[node.sheet.X_DIMENSION][node.sheet.Y_DIMENSION];
        for (int z = 0; z < newBoolean.length; z++) {
            for (int k = 0; k < newBoolean[0].length; k++) {
                newBoolean[z][k] = true;
            }
        }
        boardCuts.add(boardCuts, newBoolean);

//        Node.child(node, variation.sequence.get(0));

        int sheet =  0;
        for (int i = 0; i < variation.sequence.size(); i++) {
            Node.child(node, variation.sequence.get(i));

//            sheet = node.sheetCount;
//            int sheet = node.sheetCount;
//            int cutNum = i;

            boolean[][] booleanGrid = new boolean[node.sheet.X_DIMENSION][node.sheet.Y_DIMENSION];
            for (int x = 0; x < node.sheet.X_DIMENSION; x++) {
                for (int y = 0; y < node.sheet.Y_DIMENSION; y++) {
                    boolean cut = false;
                    if (node.sheet.grid[x][y].occupied) {
                        cut = true;
                    }
                    booleanGrid[x][y] = cut;
//
////                    cutHistory[cutNum][x][y][node.sheetCount] = cut;
//                    System.out.println("cutNum: " + cutNum + "  x " + x + " y: " + y + " Sheet: " + node.sheetCount  +  "  booleanvalue: " + cut);
                }
            }

//            System.out.println(node.sheetCount);
//            Node.hasChildren(node);
//            Node.child(node, variation.sequence.get(i));

//            Node.hasChildren(node);
//            Node.child(node, variation.sequence.get(i));

            if (node.sheetCount != sheet) {
                System.out.println("number of cuts in sheet: " + boardCuts.size());
                cutHistory.add(new BoardCuts(boardCuts));
                boardCuts.clear();
                boardCuts = new BoardCuts();

                newBoolean = new boolean[node.sheet.X_DIMENSION][node.sheet.Y_DIMENSION];
                for (int z = 0; z < newBoolean.length; z++) {
                    for (int k = 0; k < newBoolean[0].length; k++) {
                        newBoolean[z][k] = true;
                    }
                }
                boardCuts.add(boardCuts, newBoolean);
                boardCuts.add(boardCuts, booleanGrid);
            } else {
                boardCuts.add(boardCuts, booleanGrid);
            }

            sheet = node.sheetCount;
            Node.hasChildren(node);
        }
        System.out.println("number of cuts in sheet: " + boardCuts.size());
        cutHistory.add(new BoardCuts(boardCuts));

        if (cutHistory.size() > 3) {
            ArrayList<BoardCuts> newHistory = new ArrayList();

            for (int i = cutHistory.size() - 2; i >= 0; i--) {
                newHistory.add(cutHistory.get(i));
            }
            newHistory.add(cutHistory.get(cutHistory.size() - 1));
            return newHistory;
        }
        return cutHistory;
    }
}
