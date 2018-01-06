package tetrispvp.board;

public class GridFieldWithPosition {
    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public GridField getGridField() {
        return gridField;
    }

    private final int rowNumber;
    private final int columnNumber;
    private final GridField gridField;

    public GridFieldWithPosition(int rowNumber, int columnNumber, GridField gridField) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.gridField = gridField;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof GridFieldWithPosition)) {
            return false;
        }
        GridFieldWithPosition otherField = (GridFieldWithPosition) object;
        return rowNumber == otherField.getRowNumber() &&
                columnNumber == otherField.getColumnNumber() &&
                gridField.equals(otherField.getGridField());

    }
}
