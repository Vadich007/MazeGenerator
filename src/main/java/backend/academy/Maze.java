package backend.academy;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private final Wall[][] walls;

    Maze(int height, int width, Cell[][] grid, Wall[][] walls) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        this.walls = walls;
    }

    public Cell[][] grid() {
        return grid;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Wall[][] walls() {
        return walls;
    }
}
