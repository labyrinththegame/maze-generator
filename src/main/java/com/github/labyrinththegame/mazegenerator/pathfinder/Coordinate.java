package com.github.labyrinththegame.mazegenerator.pathfinder;

import com.github.labyrinththegame.mazegenerator.algo.coordinates.Node;

public class Coordinate extends Node {
    private Coordinate parent;

    public Coordinate(int x, int y, Coordinate parent) {
        super(x, y);
        this.parent = parent;
    }

    public Coordinate(Node other, Coordinate parent) {
        super(other);
        this.parent = parent;
    }

    public Coordinate getParent() {
        return parent;
    }
}
