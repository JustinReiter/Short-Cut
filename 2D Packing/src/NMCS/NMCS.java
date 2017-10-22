package NMCS;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NMCS {
    static int iterations = 0;
    static Random randomNum = new Random();
    static int bestScore = Integer.MIN_VALUE;
    static List<Action> bestSequence;

    public static Variation IteratedNested(Node node, int level) {
        bestScore = Integer.MIN_VALUE;
        iterations = 0;
        Node rootNode = new Node(node);
        Node nodeTest = new Node(rootNode);

        Variation bestVariation = new Variation(Integer.MAX_VALUE, new ArrayList());
        System.out.println("Convert to boolean");

        while (computationalBudget()) {
            System.out.println(iterations);
            iterations++;
            Variation variation;
            variation = NMCS(level, new Node(rootNode));
//            if (iterations == 1) variation = NMCS(level, new Node(rootNode));
//            else variation = NMCSSequence(level + 1, new Node(rootNode), bestVariation);
            if (variation.score >= bestScore) {
                bestScore = variation.score;
                bestSequence = new ArrayList(variation.sequence);
                bestVariation = new Variation(variation);
//                System.out.println(variation.score);
            }
            System.out.println("score: " + variation.score);
        }

//        for (int i = 0; i < bestSequence.size(); i++) {
//            node.sheet.display();
//            node.hasChildren(node);
//            Node.child(node, bestSequence.get(i));
//        }
//        node.sheet.display();
//        if (bestVariation.sequence.size() >= 3) {
//            List<Action> newSequence = new ArrayList();
//            for (int i = bestVariation.sequence.size() - 2; i >= 0; i--) {
//                newSequence.add(bestVariation.sequence.get(i));
//            }
//
//            newSequence.add(bestVariation.sequence.get(bestVariation.sequence.size() - 1));
//            return new Variation(bestVariation.score, newSequence);
//        }
        return bestVariation;
    }

    private static boolean computationalBudget() {
//        iterations++;
        if (iterations >= 1) return false;
        else return true;
//        if (bestScore != Experiment.X_DIMENSION * Experiment.Y_DIMENSION) {c
//            return true;
//        }
//        return false;
    }

    public static Variation NMCS(int level, Node node) {
        if (level == 0) {
            int ply = 0;
            List<Action> sequence = new ArrayList();
//            System.out.println("has no children");
            while (Node.hasChildren(node)) {
//                System.out.println("has children");
                Node.gatherChildren(node);
//                System.out.println(node.children.size());
//                System.exit(0);
                sequence.add(Node.randomMove(node));

//                System.out.println(node.itemList.get(0).size());
                Node.child(node, sequence.get(ply));
                ply++;
//                System.out.println("hi");
//                node.sheet.display();
//                System.exit(0);
            }
//            System.out.println("score: " + node.score);
            Variation variation = new Variation(node.score, sequence);
            return variation;
        } else {
            int ply = 0;
            List<Action> sequence = new ArrayList();
            int bestScore = node.score; //in case Node.numberOfChildren(node) == 0
            while (Node.hasChildren(node)) {
                bestScore = Integer.MIN_VALUE;
                int numberOfChildren = Node.numberOfChildren(node);
                for (int i = 0; i < numberOfChildren; i++) {
                    Node temp = new Node(node, i);
                    Variation futureVariation = NMCS(level - 1, temp);

                    if (futureVariation.score > bestScore) {
                        System.out.println("best score: " + bestScore);
                        bestScore = futureVariation.score;
                        while (sequence.size() > ply) {
                            sequence.remove(sequence.size() - 1);
                        }
                        sequence.add(node.children.get(i));
                        sequence.addAll(futureVariation.sequence);
                    }
                }
                Node.child(node, sequence.get(ply));
                ply++;
                System.out.println(ply);
            }
            Variation variation = new Variation(bestScore, sequence);
            return variation;
        }
    }

    public static Variation NMCSSequence(int level, Node node, Variation inputVariation) {
        if (level == 0) {
            int ply = 0;
            List<Action> sequence = new ArrayList();
            while (Node.hasChildren(node)) {
                Node.gatherChildren(node);
                sequence.add(Node.randomMove(node));
                Node.child(node, sequence.get(ply));
                ply++;
            }
            Variation variation = new Variation(node.score, sequence);
            return variation;
        } else {
            int ply = 0;
            List<Action> sequence = new ArrayList(inputVariation.sequence);
            int bestScore = inputVariation.score; //in case Node.numberOfChildren(node) == 0
            while (Node.hasChildren(node)) {
//                bestScore = Integer.MIN_VALUE;
                int numberOfChildren = Node.numberOfChildren(node);
                for (int i = 0; i < numberOfChildren; i++) {
                    Node temp = new Node(node, i);
                    Variation futureVariation = NMCS(level - 1, temp);
                    if (futureVariation.score > bestScore) {
                        bestScore = futureVariation.score;
                        while (sequence.size() > ply) {
                            sequence.remove(sequence.size() - 1);
                        }
                        sequence.add(node.children.get(i));
                        sequence.addAll(futureVariation.sequence);
                    }
                }
                Node.child(node, sequence.get(ply));
                ply++;
            }
            Variation variation = new Variation(bestScore, sequence);
            return variation;
        }
    }
}