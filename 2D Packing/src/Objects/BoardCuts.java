package Objects;

import java.util.List;
import java.util.ArrayList;

public class BoardCuts {
    List<boolean[][]> states;

    public BoardCuts() {
        this.states = new ArrayList();
    }

    public BoardCuts(BoardCuts boardCuts) {
        this.states = new ArrayList<boolean[][]>(boardCuts.states);

//        for (int i = 0; i < boardCuts.states.size(); i++) {
//            boolean[][] state = new boolean[10][10];
//            for (int x = 0; x < 10; x++) {
//                for (int y = 0; y < 10; y++) {
//                    state[x][y] = boardCuts.get(i)[x][y];
//                }
//            }
//            this.states.add(state);
//        }
    }

    public static void add(BoardCuts boardCuts, boolean[][] state) {
        boardCuts.states.add(state);
    }

    public boolean[][] get(int index) {
        return states.get(index);
    }

    public int size() {
        return states.size();
    }

    public void clear() {
        states.clear();
    }

    public static void print(BoardCuts boardCuts) {
        for (int i = 0; i < boardCuts.states.size(); i++) {
            boolean[][] state = boardCuts.states.get(i);

            for (int x = 0; x < state.length; x++) {
                for (int y = 0; y < state[0].length; y++) {
                    System.out.println(state[x][y]);
                }
            }
        }
    }
}
