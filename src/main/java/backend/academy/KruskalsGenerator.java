package backend.academy;

import java.util.ArrayList;
import java.util.HashSet;

public class KruskalsGenerator implements BaseGenerator {


    @Override @SuppressWarnings({"MagicNumber", "CyclomaticComplexity"})
    public Maze generate(int height, int width) {
        Cell[][] maze = new Cell[height][width];
        final double TARGET_VALUE = 0.5;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = new Cell(i, j, randomCellType());
            }
        }

        Wall[][] walls = new Wall[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                walls[i][j] = new Wall(true, true);
            }
        }

        ArrayList<HashSet<Cell>> s = new ArrayList<>();
        for (var line : maze) {
            for (var cell : line) {
                var hs = new HashSet<Cell>();
                hs.add(cell);
                s.add(hs);
            }
        }

        while (s.size() != 1) {
            Coordinate wallCoordinate =
                new Coordinate((int) (Math.random() * walls.length), (int) (Math.random() * walls[0].length));
            var wall = walls[wallCoordinate.x()][wallCoordinate.y()];
            var currWall = Math.random();
            if (currWall > TARGET_VALUE && wallCoordinate.x() < height - 1 && wall.bottom) { // bottom
                var upCell = maze[wallCoordinate.x()][wallCoordinate.y()];
                var downCell = maze[wallCoordinate.x() + 1][wallCoordinate.y()];
                var upSet = findSet(upCell, s);
                var downSet = findSet(downCell, s);
                if (!upSet.contains(downCell)) {
                    walls[wallCoordinate.x()][wallCoordinate.y()].bottom = false;
                    upSet.addAll(downSet);
                    s.remove(downSet);
                }
            } else if (currWall <= 0.5 && wallCoordinate.y() < width - 1 && wall.right) { //right
                var leftCell = maze[wallCoordinate.x()][wallCoordinate.y()];
                var rightCell = maze[wallCoordinate.x()][wallCoordinate.y() + 1];
                var leftSet = findSet(leftCell, s);
                var rightSet = findSet(rightCell, s);
                if (!leftSet.contains(rightCell)) {
                    walls[wallCoordinate.x()][wallCoordinate.y()].right = false;
                    leftSet.addAll(rightSet);
                    s.remove(rightSet);
                }
            }
        }
        return new Maze(height, width, maze, walls);
    }

    private HashSet<Cell> findSet(Cell cell, ArrayList<HashSet<Cell>> s) {
        for (var set : s) {
            if (set.contains(cell)) {
                return set;
            }
        }
        return null;
    }

    @SuppressWarnings("MagicNumber")
    private CellType randomCellType() {
        final double dirt = 2.7;
        final double coin = 0.3;
        double randomCell = Math.random() * 3;
        if (randomCell <= coin) {
            return CellType.COIN;
        } else if (randomCell >= dirt) {
            return CellType.DIRT;
        } else {
            return CellType.PASSAGE;
        }
    }
}
