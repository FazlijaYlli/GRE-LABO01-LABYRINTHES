package gre.lab1.groupe12;

import gre.lab1.graph.GridGraph2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: javadoc
public final class GridGraph implements GridGraph2D {
    /**
     * Largeur de la grille.
     */
    private final int width;

    /**
     * Hauteur de la grille.
     */
    private final int height;

    /**
     * Listes d'adjacence
     */
    ArrayList<LinkedList<Integer>> neighbors;

    /**
     * Construit une grille carrée.
     *
     * @param side Côté de la grille.
     */
    public GridGraph(int side) {
        this(side, side);
    }

    /**
     * Construit une grille rectangulaire.
     *
     * @param width  Largeur de la grille.
     * @param height Hauteur de la grille.
     * @throws IllegalArgumentException si {@code width} ou {@code length} sont négatifs ou nuls.
     */
    public GridGraph(int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");

        this.width = width;
        this.height = height;

        neighbors = new ArrayList<>(width * height);
    }

    @Override
    public List<Integer> neighbors(int v) {

        return new ArrayList<>(neighbors.get(v));
    }

    @Override
    public boolean areAdjacent(int u, int v) {

        if (neighbors.get(u).isEmpty()) return false;

        for (int x : neighbors.get(u)) {
            if (x == v) return true;
        }

        return false;
    }

    @Override
    public void addEdge(int u, int v) {

        if (!neighbors.get(u).contains(v)) {
            neighbors.get(u).add(v);
            neighbors.get(v).add(u);
        }
    }

    @Override
    public void removeEdge(int u, int v) {

        if (neighbors.get(u).contains(v)) {
            neighbors.get(u).removeFirstOccurrence(v);
            neighbors.get(v).removeFirstOccurrence(u);
        }
    }

    @Override
    public int nbVertices() {
        return neighbors.size();
    }

    @Override
    public boolean vertexExists(int v) {
        return v < neighbors.size();
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    /**
     * Lie chaque sommet du graphe donné à tous ses voisins dans la grille.
     *
     * @param graph Un graphe.
     */
    public static void bindAll(GridGraph graph) {
        for (int line = 0; line < graph.height; line++) {
            for (int column = 0; column < graph.width; column++) {
                if (line != graph.height - 1)
                    graph.addEdge(line * graph.width + column, (line + 1) * graph.width + column);
                if (column != graph.width - 1)
                    graph.addEdge(line * graph.width + column, line * graph.width + column + 1);
            }
        }

    }
}
