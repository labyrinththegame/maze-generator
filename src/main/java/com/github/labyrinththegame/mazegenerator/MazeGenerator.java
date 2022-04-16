package com.github.labyrinththegame.mazegenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.labyrinththegame.mazegenerator.algo.Direction;
import com.github.labyrinththegame.mazegenerator.algo.Ellers;
import com.github.labyrinththegame.mazegenerator.algo.Maze;
import com.github.labyrinththegame.mazegenerator.algo.TileMaze;
import com.github.labyrinththegame.mazegenerator.algo.coordinates.Node;
import com.github.labyrinththegame.mazegenerator.data.MazeCell;
import com.github.labyrinththegame.mazegenerator.data.MazeData;
import com.github.labyrinththegame.mazegenerator.pathfinder.Coordinate;
import com.github.labyrinththegame.mazegenerator.pathfinder.PathFinder;

import java.util.ArrayList;
import java.util.List;

import static com.github.labyrinththegame.mazegenerator.Utils.toJson;

public class MazeGenerator {
    public static void main(String[] args) throws JsonProcessingException {
        MazeGenerator generator = new MazeGenerator();
        generator.generate(10, 10);
    }

    public MazeData generate(int rows, int columns) throws JsonProcessingException {
        Maze maze = new Ellers(columns, rows);
        maze.generate();
        MazeData mazeData = convertMap(maze);
        String json = toJson(mazeData);
        System.out.println(maze);
        System.out.println(json);

        PathFinder pathFinder = new PathFinder();
        TileMaze tileMaze = new TileMaze(maze);
        Coordinate start = new Coordinate(1, 1, null);
        Coordinate exit = new Coordinate(tileMaze.getWidth() - 2, tileMaze.getHeight() - 2, null);
        List<Coordinate> path = pathFinder.solve(tileMaze, start, exit);
        List<Node> unTiledPath = pathFinder.unTilePath(path);
        mazeData.setPath(unTiledPath);

        return mazeData;
    }

    private MazeData convertMap(Maze maze) {
        MazeData data = new MazeData();
        data.setRows(maze.getHeight());
        data.setColumns(maze.getWidth());
        List<MazeCell> cells = new ArrayList<>();
        for (int y = 0; y < maze.getHeight(); ++y) {
            for (int x = 0; x < maze.getWidth(); ++x) {
                MazeCell cell = new MazeCell(
                        maze.isWall(x, y, Direction.NORTH) ? true : null,
                        maze.isWall(x, y, Direction.EAST) ? true : null,
                        maze.isWall(x, y, Direction.SOUTH) ? true : null,
                        maze.isWall(x, y, Direction.WEST) ? true : null
                );
                cells.add(cell);
            }
        }
        data.setMap(cells);
        return data;
    }
}
