package NMCS;

public class Pair {
    int bestScore;
    int iterations;

    public Pair(int bestScore, int iterations) {
        this.bestScore = bestScore;
        this.iterations = iterations;
    }

    public Pair(Pair pair) {
        this.bestScore = pair.bestScore;
        this.iterations = pair.iterations;
    }
}
