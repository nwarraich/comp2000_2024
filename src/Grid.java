import java.awt.Point;
import java.util.Optional;

public class Grid {
    private Cell[][] cells;

    public Grid() {
        // Initialize the grid with cells, assuming it's a rectangular grid.
        cells = new Cell[10][10]; // Example size
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell((char)('A' + i), j + 1, i * Cell.size, j * Cell.size);
            }
        }
    }

    public Optional<Cell> cellAtPoint(Point p) {
        if (p == null) {
            return Optional.empty();
        }

        int col = p.x / Cell.size;
        int row = p.y / Cell.size;

        if (col < 0 || col >= cells.length || row < 0 || row >= cells[0].length) {
            return Optional.empty();
        }

        return Optional.of(cells[col][row]);
    }
}
