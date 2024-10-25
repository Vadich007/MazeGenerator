package backend.academy;

public class Cell {
    private Coordinate coordinate;
    private CellType type;

    Cell(int x, int y, CellType type) {
        this.coordinate = new Coordinate(x, y);
        this.type = type;
    }

    public int x() {
        return coordinate.x();
    }

    public int y() {
        return coordinate.y();
    }

    public Coordinate coordinate() {
        return coordinate;
    }

    public CellType type() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
}
