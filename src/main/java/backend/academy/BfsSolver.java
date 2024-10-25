package backend.academy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsSolver implements BaseSolver {

    @Override @SuppressWarnings("CyclomaticComplexity")
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int[][] dist = new int[maze.height()][maze.width()];
        Coordinate[][] from = new Coordinate[maze.height()][maze.width()];

        final int INF = Integer.MAX_VALUE;
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                dist[i][j] = INF;
            }
        }

        dist[start.x()][start.y()] = 0;

        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty() && dist[end.x()][end.y()] == INF) {
            var v = q.remove();
            if (v.x() + 1 < maze.height() && !maze.walls()[v.x()][v.y()].bottom && dist[v.x() + 1][v.y()] == INF) {
                dist[v.x() + 1][v.y()] = dist[v.x()][v.y()] + maze.grid()[v.x()][v.y()].type().ordinal();
                q.add(new Coordinate(v.x() + 1, v.y()));
                from[v.x() + 1][v.y()] = new Coordinate(v.x(), v.y());
            }

            if (v.x() - 1 >= 0 && !maze.walls()[v.x() - 1][v.y()].bottom && dist[v.x() - 1][v.y()] == INF) {
                dist[v.x() - 1][v.y()] = dist[v.x()][v.y()] + maze.grid()[v.x()][v.y()].type().ordinal();
                q.add(new Coordinate(v.x() - 1, v.y()));
                from[v.x() - 1][v.y()] = new Coordinate(v.x(), v.y());
            }

            if (v.y() + 1 < maze.width() && !maze.walls()[v.x()][v.y()].right && dist[v.x()][v.y() + 1] == INF) {
                dist[v.x()][v.y() + 1] = dist[v.x()][v.y()] + maze.grid()[v.x()][v.y()].type().ordinal();
                q.add(new Coordinate(v.x(), v.y() + 1));
                from[v.x()][v.y() + 1] = new Coordinate(v.x(), v.y());
            }

            if (v.y() - 1 >= 0 && !maze.walls()[v.x()][v.y() - 1].right && dist[v.x()][v.y() - 1] == INF) {
                dist[v.x()][v.y() - 1] = dist[v.x()][v.y()] + maze.grid()[v.x()][v.y()].type().ordinal();
                q.add(new Coordinate(v.x(), v.y() - 1));
                from[v.x()][v.y() - 1] = new Coordinate(v.x(), v.y());
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
