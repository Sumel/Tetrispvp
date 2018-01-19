package GUI.Block;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlockImplementation implements Block {
    private static final int boundingBoxSize = 4;
    private static final int possibleRotations = 4;
    private GridField[][][] fields;
    private int currentRotation = 0;
    private BlockType type;

    public BlockImplementation(BlockType blockType) {
        type = blockType;
        fillWithEmpty();
        createTetromino();
    }

    @Override
    public List<List<GridField>> getBoardFields() {
        List<List<GridField>> out = new ArrayList<>();
        for (GridField[] col : fields[currentRotation]) {
            out.add(Collections.unmodifiableList(Arrays.asList(col)));
        }
        return Collections.unmodifiableList(out);
    }

    @Override
    public void rotateClockwise() {
        currentRotation = (currentRotation + 1) % possibleRotations;
    }

    @Override
    public void rotateCounterClockwise() {
        currentRotation = (currentRotation - 1 + possibleRotations) % possibleRotations;
    }

    private void fillWithEmpty() {
        BlockGridField emptyField = new BlockGridField(false, Color.BLACK, -1);
        fields = new BlockGridField[possibleRotations][boundingBoxSize][boundingBoxSize];
        for (GridField[][] mtx : fields) {
            for (GridField[] row : mtx) {
                Arrays.fill(row, emptyField);
            }
        }
    }

    private void createTetromino() {
        Color color = type.getColor();
        int[][][] indexes = new TetrominoIndexes().takeIndexes(type);
        for (int i = 0; i < possibleRotations; ++i) {
            for (int j = 0; j < boundingBoxSize; ++j) {
                int x = indexes[i][j][0];
                int y = indexes[i][j][1];
                fields[i][x][y] = new BlockGridField(true, color, -1);
            }
        }
    }

    public void setPowerUp(int numberOfField, int powerUpId) {
        if (numberOfField < 4 && numberOfField >= 0) {
            int[][][] indexes = new TetrominoIndexes().takeIndexes(type);
            for (int i = 0; i < possibleRotations; i++) {
                int x = indexes[i][numberOfField][0];
                int y = indexes[i][numberOfField][1];
                GridField field = fields[i][x][y];
                fields[i][x][y] = new BlockGridField(field.isOccupied(), field.getColor(), powerUpId);
            }
        }
    }
}
