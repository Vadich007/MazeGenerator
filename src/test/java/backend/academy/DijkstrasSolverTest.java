package backend.academy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DijkstrasSolverTest {

    BaseSolver solver = new DijkstrasSolver();
    static Wall[][] walls = new Wall[3][3];
    Cell[][] grid = new Cell[3][3];

    @BeforeAll
    public static void init() {
        walls[0][0] = new Wall(false, false);
        walls[0][1] = new Wall(false, true);
        walls[0][2] = new Wall(true, false);
        walls[1][0] = new Wall(false, false);
        walls[1][1] = new Wall(false, true);
        walls[1][2] = new Wall(true, false);
        walls[2][0] = new Wall(true, true);
        walls[2][1] = new Wall(false, true);
        walls[2][2] = new Wall(true, true);
    }

    @Test
    public void solvePassageCellsTest() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Cell(i, j, CellType.PASSAGE);
            }
        }

        var maze = new Maze(3, 3, grid, walls);
        var start = new Coordinate(0, 0);
        var end = new Coordinate(0, 2);

        var path = solver.solve(maze, start, end);
        ArrayList<Coordinate> expect = new ArrayList<>();
        expect.add(end);
        expect.add(new Coordinate(0, 1)) ;
        expect.add(start);
        for (var i : path) {
            assertThat(expect.getFirst()).isEqualTo(i);
            expect.removeFirst();
        }
    }

    @Test
    public void solveRandomCellsTest() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Cell(i, j, CellType.PASSAGE);
            }
        }

        grid[0][1].setType(CellType.DIRT);
        grid[0][2].setType(CellType.DIRT);


        var maze = new Maze(3, 3, grid, walls);
        var start = new Coordinate(0, 0);
        var end = new Coordinate(1, 2);

        var path = solver.solve(maze, start, end);
        ArrayList<Coordinate> expect = new ArrayList<>();
        expect.add(end);
        expect.add(new Coordinate(1, 1));
        expect.add(new Coordinate(1, 0));
        expect.add(start);
        for (var i : path) {
            assertThat(expect.getFirst()).isEqualTo(i);
            expect.removeFirst();
        }
    }

}
