package com.github.labyrinththegame.mazegenerator.pathfinder;

import com.github.labyrinththegame.mazegenerator.algo.Direction;
import com.github.labyrinththegame.mazegenerator.algo.Maze;
import com.github.labyrinththegame.mazegenerator.algo.TileMaze;
import com.github.labyrinththegame.mazegenerator.algo.coordinates.Node;

import java.util.*;

/**
 * @see <a href="https://www.baeldung.com/java-solve-maze">https://www.baeldung.com/java-solve-maze</a>
 */
public class PathFinder {
    public List<Coordinate> solve(TileMaze maze, Coordinate start, Coordinate exit) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        HashSet<Coordinate> explored = new HashSet<>();

        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            //Check maze.isValidLocation (i.e. the nodes which are outside the maze or are part of the wall)
            if (explored.contains(cur)) {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY())) {
                explored.add(cur);//TODO check needed or not
                continue;
            }

            if (exit.equals(cur)) {
                return backtrackPath(cur);
            }

            for (Direction direction : Direction.values()) {
                if (isValid(maze, cur, direction)) {
                    Coordinate coordinate = new Coordinate(
                            cur.getX() + direction.dx,
                            cur.getY() + direction.dy,
                            cur
                    );
                    nextToVisit.add(coordinate);
                    explored.add(cur);
                }
            }
        }
        return Collections.emptyList();
    }

    private boolean isValid(TileMaze maze, Coordinate curPoint, Direction direction) {
        int newX = curPoint.getX() + direction.dx;
        int newY = curPoint.getY() + direction.dy;
        if (newX < 0 || newX >= maze.getWidth() || newY < 0 || newY >= maze.getHeight()) {
            return false;
        }
        return !maze.isWall(newX, newY);
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }

        return path;
    }

    /**
     * @param tiledPath path for {@link TileMaze}
     * @return converts path from solution for {@link TileMaze} to solution for {@link Maze}
     */
    public List<Node> unTilePath(List<Coordinate> tiledPath) {
        List<Node> result = new ArrayList<>();
        boolean odd = true;
        for (Coordinate coordinate : tiledPath) {
            if (odd) {
                result.add(new Node(coordinate.x / 2, coordinate.y / 2));
                odd = false;
            } else {
                odd = true;
            }
        }
        return result;
    }
}
