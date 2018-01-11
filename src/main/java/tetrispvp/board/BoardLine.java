package tetrispvp.board;


import java.util.List;

public class BoardLine implements LinesClearedListener.Line {
    private final int lineNumber;
    private final List<GridField> fields;

    public BoardLine(int lineNumber, List<GridField> fields) {
        this.lineNumber = lineNumber;
        this.fields = fields;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public List<GridField> getFieldsInLine() {
        return fields;
    }
}
