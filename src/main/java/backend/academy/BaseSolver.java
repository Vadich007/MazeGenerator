package backend.academy;

import java.util.List;

public interface BaseSolver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
