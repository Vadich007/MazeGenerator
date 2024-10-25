package backend.academy;

import java.util.ArrayList;

public class EulerGenerator implements BaseGenerator {

    @Override @SuppressWarnings({"MagicNumber", "CyclomaticComplexity"})
    public Maze generate(int height, int width) {
        ArrayList<Integer> sets = new ArrayList<>();
        Wall[][] walls = new Wall[height][width];
        var grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                walls[i][j] = new Wall(false, false);
                grid[i][j] = new Cell(i, j, randomCellType());
            }
        }

        int counter = 1;
        for (int i = 0; i < width; i++) {
            sets.add(counter);
            counter++;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (sets.get(j) == sets.get(j + 1) || genWall()) {
                    walls[i][j].right = true;
                } else {
                    joinSets(sets, sets.get(j), sets.get(j + 1));
                }
                if (genWall() && moreThatOneWithoutBottomWalls(sets, sets.get(j), i, walls)) {
                    walls[i][j].bottom = true;
                }
            }

            walls[i][width - 1].right = true;

            for (int j = 0; j < width; j++) {
                if (walls[i][j].bottom) {
                    sets.set(j, counter);
                    counter++;
                }
            }
        }

        for (int j = 0; j < width - 1; j++) {
            walls[height - 1][j].bottom = true;
            if (sets.get(j) != sets.get(j + 1)) {
                walls[height - 1][j].right = false;
                joinSets(sets, sets.get(j), sets.get(j + 1));
            }
        }

        walls[height - 1][width - 1].bottom = true;

        return new Maze(height, width, grid, walls);
    }

    private boolean moreThatOneWithoutBottomWalls(ArrayList<Integer> sets, int currSet, int row, Wall[][] walls) {
        int count = 0;
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i) == currSet && !walls[row][i].bottom) {
                count++;
            }
        }
        return count > 1;
    }

    private void joinSets(ArrayList<Integer> sets, int newSet, int oldSet) {
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i) == oldSet) {
                sets.set(i, newSet);
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private boolean genWall() {
        return Math.random() > 0.5;
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
