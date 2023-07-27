package pgdp.penguinDate;

import pgdp.tree.Tree;

public class PenguinDateGenerator {

    private Tree<Penguin> tree;

    public PenguinDateGenerator(Tree<Penguin> tree) {
        this.tree = tree;
    }

    public boolean canGoOnADate(Penguin p1, Penguin p2) {
        return (tree.distanceBetweenNodes(p1, tree.LCA(p1, p2)) >= p1.getAllowance() && tree.distanceBetweenNodes(p2, tree.LCA(p1, p2)) >= p2.getAllowance());

    }

    public Tree<Penguin> getTree() {
        return this.tree;
    }

}
