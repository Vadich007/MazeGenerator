package backend.academy;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WallTest {
    @Test
    public void initTest() {
        var wall = new Wall(false, false);
        assertThat(wall.bottom).isEqualTo(false);
        assertThat(wall.right).isEqualTo(false);
        wall.bottom = true;
        wall.right = true;
        assertThat(wall.bottom).isEqualTo(true);
        assertThat(wall.right).isEqualTo(true);
        assertThat(wall.type()).isEqualTo(CellType.WALL);
    }
}
