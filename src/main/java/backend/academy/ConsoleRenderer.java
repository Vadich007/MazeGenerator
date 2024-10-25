package backend.academy;

import java.io.PrintStream;
import java.util.List;

public class ConsoleRenderer implements BaseRenderer {
    private final PrintStream ps;

    ConsoleRenderer() {
        this.ps = System.out;
    }

    private String getChar(CellType type) {
        return switch (type) {
            case CellType.PASSAGE -> Bricks.WHITE_BRICK;
            case CellType.WALL -> Bricks.BLACK_BRICK;
            case CellType.DIRT -> Bricks.BROWN_BRICK;
            case CellType.COIN -> Bricks.YELLOW_BRICK;
            case CellType.PATH -> Bricks.GREEN_BRICK;
            case CellType.START -> Bricks.PURPLE_BRICK;
            case CellType.END -> Bricks.BLUE_BRICK;
            default -> null;
        };
    }

    @Override @SuppressWarnings("CyclomaticComplexity")
    public void render(Maze maze) {
        ps.println(getChar(CellType.WALL).repeat(maze.width() * 2 + 1));
        for (int i = 0; i < maze.height(); i++) {
            StringBuilder first = new StringBuilder(getChar(CellType.WALL));
            StringBuilder second = new StringBuilder(getChar(CellType.WALL));
            for (int j = 0; j < maze.width(); j++) {
                first.append(getChar(maze.grid()[i][j].type()));

                if (maze.walls()[i][j].bottom) {
                    second.append(getChar(maze.walls()[i][j].type()));
                } else if ((maze.grid()[i][j].type() == CellType.PATH || maze.grid()[i][j].type() == CellType.START
                        || maze.grid()[i][j].type() == CellType.END)
                        && i + 1 < maze.height()
                        && (maze.grid()[i + 1][j].type() == CellType.PATH || maze.grid()[i + 1][j].type() == CellType.END
                        || maze.grid()[i + 1][j].type() == CellType.START)) {
                    second.append(getChar(CellType.PATH));
                } else {
                    second.append(getChar(CellType.PASSAGE));
                }

                if (maze.walls()[i][j].right) {
                    first.append(getChar(maze.walls()[i][j].type()));
                } else if ((maze.grid()[i][j].type() == CellType.PATH || maze.grid()[i][j].type() == CellType.START
                        || maze.grid()[i][j].type() == CellType.END)
                        && j + 1 < maze.width()
                        && (maze.grid()[i][j + 1].type() == CellType.PATH || maze.grid()[i][j + 1].type() == CellType.START
                        || maze.grid()[i][j + 1].type() == CellType.END)) {
                    first.append(getChar(CellType.PATH));
                } else {
                    first.append(getChar(CellType.PASSAGE));
                }

                second.append(getChar(maze.walls()[i][j].type()));
            }
            ps.println(first);
            ps.println(second);
        }
    }

    @Override
    public void render(Maze maze, List<Coordinate> path) {
        for (var coordinate : path) {
            maze.grid()[coordinate.x()][coordinate.y()].setType(CellType.PATH);
        }
        maze.grid()[path.getFirst().x()][path.getFirst().y()].setType(CellType.END);
        maze.grid()[path.getLast().x()][path.getLast().y()].setType(CellType.START);
        render(maze);
    }
}