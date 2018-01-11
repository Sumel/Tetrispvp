package aiModule;

import aiModule.mocks.GridField;

import java.util.List;

class BoardStatus {
    List<List<GridField>> value;

    BoardStatus(List<List<GridField>> value) {
        this.value = value;
    }

    public int getHoles() {
        int total = 0;
        for (List<GridField> line : this.value) {
            boolean check = false;
            for (GridField gridField : line) {
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

    private int getColumnHeight(int column) {        //column - [0, 1, ..., 9]
        int boardHeight = 22;
        int currentRow = 0;
        while (isInBoardRange(currentRow, boardHeight) && isEmpty(currentRow, column)) {
            currentRow++;
        }
        return boardHeight - currentRow;
    }

    private boolean isInBoardRange(int currentColumn, int boardHeight) {
        return currentColumn < boardHeight;
    }

    private boolean isEmpty(int row, int column) {
        return this.value.get(row).get(column).isOccupied();
    }

    public int getAggregateHeight() {
        int total = 0;
        for (int c = 0; c < 10; c++) {
            total += this.getColumnHeight(c);
        }
        return total;
    }

    public void insertAt(int x, int y, GridField value){
        this.value.get(y).set(x, value);
    }

}

