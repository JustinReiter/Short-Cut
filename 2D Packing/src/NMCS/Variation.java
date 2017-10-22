package NMCS;

import java.util.ArrayList;
import java.util.List;

public class Variation {
    int score;
    List<Action> sequence = new ArrayList();

    public Variation(int nodeScore, List<Action> nodeSequence) {
        this.score = nodeScore;
        this.sequence = new ArrayList(nodeSequence);
    }

    public Variation(Variation variation) {
        this.score = variation.score;
        this.sequence = new ArrayList(variation.sequence);
    }
}
