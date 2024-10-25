package backend.academy;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KruskalsGeneratorTest {

    BaseGenerator generator = new KruskalsGenerator();

    @Test
    public void generateTest() {
        var maze = generator.generate(3 ,3);
        assertThat(maze.height()).isEqualTo(3);
        assertThat(maze.width()).isEqualTo(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertThat(maze.grid()[i][j].type() == CellType.PASSAGE
                    || maze.grid()[i][j].type() == CellType.COIN
                    || maze.grid()[i][j].type() == CellType.DIRT).isEqualTo(true);
                assertThat(maze.grid()[i][j].x()).isEqualTo(i);
                assertThat(maze.grid()[i][j].y()).isEqualTo(j);
                assertThat(maze.grid()[i][j].coordinate()).isEqualTo(new Coordinate(i, j));
                assertThat(maze.walls()[i][j]).isNotNull();
                assertThat(maze.walls()[i][j].type()).isEqualTo(CellType.WALL);
            }
        }

    }
}
