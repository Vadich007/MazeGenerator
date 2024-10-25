package backend.academy;

public class Wall {
    public boolean right;
    public boolean bottom;
    private final CellType type = CellType.WALL;

    Wall(boolean right, boolean bottom) {
        this.right = right;
        this.bottom = bottom;
    }

    public CellType type() {
        return type;
    }
}
