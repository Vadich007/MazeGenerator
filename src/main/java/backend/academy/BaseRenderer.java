package backend.academy;

import java.util.List;

public interface BaseRenderer {
    void render(Maze maze);

    void render(Maze maze, List<Coordinate> path);
}
