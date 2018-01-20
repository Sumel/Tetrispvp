package tetrispvp.board.Mocks;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tetrispvp.board.GridField;

import java.awt.*;
import java.util.*;
import java.util.List;
import javafx.scene.paint.Color;

public class BlockImplementation implements GUI.Block.Block {

    private static final int boundingBoxSize = 4;
    private static final int possibleRotations = 4;
    private GridField[][][] fields;
    private int currentRotation = 0;

    public BlockImplementation(char blockType) {
        fillWithEmpty();
        switch (blockType) {
            case 'I':
                createTetrominoI();
                break;
            default:
                throw new NotImplementedException();

        }
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

    @Override
    public void setPowerUp(int numberOfField, int powerUpId) {
        //do nothing
    }

    private void fillWithEmpty() {
        BlockField emptyField = new BlockField(false, Color.BLACK, -1);
        fields = new BlockField[possibleRotations][boundingBoxSize][boundingBoxSize];
        for (GridField[][] mtx : fields) {
            for (GridField[] row : mtx) {
                Arrays.fill(row, emptyField);
            }
        }
    }

    private void createTetrominoI() {
        Color color = Color.AQUA;
        BlockField block = new BlockField(true, color, -1);
        int[][][] indexes =
                {
                        {
                                {0, 1},
                                {1, 1},
                                {2, 1},
                                {3, 1}
                        },
                        {
                                {2, 0},
                                {2, 1},
                                {2, 2},
                                {2, 3},
                        },
                        {
                                {0, 2},
                                {1, 2},
                                {2, 2},
                                {3, 2}

                        },
                        {
                                {1, 0},
                                {1, 1},
                                {1, 2},
                                {1, 3}
                        }
                };
        for (int i = 0; i < possibleRotations; ++i) {
            for (int j = 0; j < boundingBoxSize; ++j) {
                int x = indexes[i][j][0];
                int y = indexes[i][j][1];
                fields[i][x][y] = block;
            }
        }
    }
}
