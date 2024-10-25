package backend.academy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CellTest {
    @Test
    public void initTest() {
        var cell = new Cell(1, 1, CellType.PATH);
        assertThat(cell.x()).isEqualTo(1);
        assertThat(cell.y()).isEqualTo(1);
        assertThat(cell.type()).isEqualTo(CellType.PATH);
        assertThat(cell.coordinate()).isEqualTo(cell.coordinate());
    }

    @Test
    public void Test() {
        var cell = new Cell(1, 1, CellType.PATH);
        cell.setType(CellType.PASSAGE);
        assertThat(cell.type()).isEqualTo(CellType.PASSAGE);
    }
}
