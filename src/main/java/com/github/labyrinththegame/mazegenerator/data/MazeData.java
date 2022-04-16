package com.github.labyrinththegame.mazegenerator.data;

import com.github.labyrinththegame.mazegenerator.algo.coordinates.Node;

import java.util.List;
import java.util.Objects;

public class MazeData {
    private String seed;
    private int rows;
    private int columns;
    private List<MazeCell> map;
    private List<Node> path;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<MazeCell> getMap() {
        return map;
    }

    public void setMap(List<MazeCell> map) {
        this.map = map;
    }

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MazeData{" +
                "seed='" + seed + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                ", map=" + map +
                ", path=" + path +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeData mazeData = (MazeData) o;
        return rows == mazeData.rows && columns == mazeData.columns && Objects.equals(seed, mazeData.seed) && Objects.equals(map, mazeData.map) && Objects.equals(path, mazeData.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seed, rows, columns, map, path);
    }
}
