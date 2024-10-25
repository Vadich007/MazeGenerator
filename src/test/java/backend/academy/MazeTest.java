package backend.academy;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeTest {
    @Test
    public void initTest() {
        Wall[][] walls = new Wall[3][3];
        Cell[][] grid = new Cell[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Cell(i, j, CellType.PASSAGE);
                walls[i][j] = new Wall(false, false);
            }
        }
        var maze = new Maze(3,3, grid, walls);

        assertThat(maze.height()).isEqualTo(3);
        assertThat(maze.width()).isEqualTo(3);
        assertThat(maze.grid()).isEqualTo(grid);
        assertThat(maze.walls()).isEqualTo(walls);
    }
}
