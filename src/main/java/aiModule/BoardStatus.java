package aiModule;

import aiModule.mocks.GridField;

import java.util.List;

public class BoardStatus {
    public List<List<GridField>> value;

    public BoardStatus(List<List<GridField>> value) {
        this.value = value;
    }

    public int getHoles() {
        int total = 0;
        for (int column = 0; column < 10; column++) {
            boolean check = false;
            for (int row = 0; row < 22; row++) {
                GridField gridField = this.value.get(row).get(column);
                if (gridField.isOccupied()) {
                    check = true;
                } else if (!gridField.isOccupied() && check) {
                    total++;
                }
            }

        }
        return total;
    }

    public int getBumpiness() {
        int total = 0;
        for (int c = 0; c < 9; c++)
            total += Math.abs(this.getColumnHeight(c) - this.getColumnHeight(c + 1));
        return total;
    }

    public int getColumnHeight(int column) {        //column - [0, 1, ..., 9]
        int boardHeight = 22;
        int currentRow = 0;
        while (isInBoardRange(currentRow, boardHeight) && isEmpty(currentRow, column)) {
            currentRow++;
        }
        return boardHeight - currentRow;
    }

    private boolean isInBoardRange(int currentRow, int boardHeight) {
        return currentRow < boardHeight;
    }

    private boolean isEmpty(int row, int column) {
        return !this.value.get(row).get(column).isOccupied();
    }

    public int getAggregateHeight() {
        int total = 0;
        for (int c = 0; c < 10; c++) {
            total += this.getColumnHeight(c);
        }
        return total;
    }

    public void insertAt(int x, int y, GridField value) {
        this.value.get(y).set(x, value);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (List<GridField> line : this.value) {
            for (GridField gf : line) {
                b.append(gf);
            }
            b.append('\n');
        }
        return "BoardStatus{\n" + b.toString() + "}";
    }
}

