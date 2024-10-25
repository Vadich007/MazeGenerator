package backend.academy;

import java.util.ArrayList;
import java.util.List;

public class DijkstrasSolver implements BaseSolver {



    @Override @SuppressWarnings({"MagicNumber", "CyclomaticComplexity"})
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int[][] dist = new int[maze.height()][maze.width()];
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        Coordinate[][] from = new Coordinate[maze.height()][maze.width()];
        final int INF = Integer.MAX_VALUE;

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                dist[i][j] = INF;
                visited[i][j] = false;
            }
        }

        dist[start.x()][start.y()] = 0;

        for (int k = 0; k < maze.height() * maze.width(); k++) {
            Coordinate nearest = new Coordinate(-1, -1);
            for (int i = 0; i < maze.height(); i++) {
                for (int j = 0; j < maze.width(); j++) {
                    if (!visited[i][j]
                        && (nearest.equals(new Coordinate(-1, -1)) || dist[nearest.x()][nearest.y()] > dist[i][j])) {
                        nearest = maze.grid()[i][j].coordinate();
                    }
                }
            }
            visited[nearest.x()][nearest.y()] = true;

            if (nearest.x() + 1 < maze.height() && !maze.walls()[nearest.x()][nearest.y()].bottom
                && dist[nearest.x() + 1][nearest.y()]
                    > dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal()) {
                dist[nearest.x() + 1][nearest.y()]
                    = dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal();
                from[nearest.x() + 1][nearest.y()] = new Coordinate(nearest.x(), nearest.y());
            }

            if (nearest.x() - 1 >= 0 && !maze.walls()[nearest.x() - 1][nearest.y()].bottom
                && dist[nearest.x() - 1][nearest.y()]
                    > dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal()) {
                dist[nearest.x() - 1][nearest.y()] =
                    dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal();
                from[nearest.x() - 1][nearest.y()] = new Coordinate(nearest.x(), nearest.y());
            }

            if (nearest.y() + 1 < maze.width() && !maze.walls()[nearest.x()][nearest.y()].right
                && dist[nearest.x()][nearest.y() + 1]
                    > dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal()) {
                dist[nearest.x()][nearest.y() + 1]
                    = dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal();
                from[nearest.x()][nearest.y() + 1] = new Coordinate(nearest.x(), nearest.y());
            }

            if (nearest.y() - 1 >= 0 && !maze.walls()[nearest.x()][nearest.y() - 1].right
                && dist[nearest.x()][nearest.y() - 1]
                    > dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal()) {
                dist[nearest.x()][nearest.y() - 1]
                    = dist[nearest.x()][nearest.y()] + maze.grid()[nearest.x()][nearest.y()].type().ordinal();
                from[nearest.x()][nearest.y() - 1] = new Coordinate(nearest.x(), nearest.y());
            }
        }
        ArrayList<Coordinate> path = new ArrayList<>();
        for (Coordinate v = end; !v.equals(start); v = from[v.x()][v.y()]) {
            path.add(v);
        }
        path.add(start);
        return path;
    }
}
