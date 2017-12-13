package tetrispvp.board.Mocks;

import tetrispvp.board.GridField;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BlockImplementation implements Block {

    private static final int boundingBoxSize = 4;
    private static final int possibleRotations = 4;
    private GridField[][][] fields;

    public BlockImplementation(char blockType) {
        fillWithEmpty();
        switch(blockType) {
            case 'I':
                createTetrominoI();
                break;
            case 'O':
                createTetrominoO();
                break;
            case 'S':
                createTetrominoS();
                break;
            case 'Z':
                createTetrominoZ();
                break;
            case 'J':
                createTetrominoJ();
                break;
            case 'L':
                createTetrominoL();
                break;
            case 'T':
                createTetrominoT();
                break;
            default:
                throw new IllegalArgumentException();

        }
    }


    @Override
    public GridField getFieldAtPosition(int x, int y) {
        return null;
    }

    @Override
    public List<List<GridField>> getBoardFields() {
        return null;
    }

    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateCounterClockwise() {

    }

    private void fillWithEmpty() {
        BlockField emptyField = new BlockField(false, Color.black, Optional.empty());
        fields = new BlockField[possibleRotations][boundingBoxSize][boundingBoxSize];
        for(GridField[][] mtx : fields) {
            for(GridField[] row : mtx) {
                Arrays.fill(row, emptyField);
            }
        }
    }

    private void createTetrominoI() {
        Color color = Color.cyan;
        BlockField block = new BlockField(true, color, Optional.empty());
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
                                {2, 3}

                        },
                        {
                                {1, 0},
                                {1, 1},
                                {1, 2},
                                {1, 3}
                        }
                };
        for(int i = 0;i<possibleRotations;++i){
            for(int j = 0;j<boundingBoxSize;++j){
                int x = indexes[i][j][0];
                int y = indexes[i][j][1];
                fields[i][x][y] = block;
            }
        }
    }

    private void createTetrominoO() {
        Color color = Color.yellow;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }

    private void createTetrominoS() {
        Color color = Color.green;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }

    private void createTetrominoZ() {
        Color color = Color.red;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }

    private void createTetrominoJ() {
        Color color = Color.blue;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }

    private void createTetrominoL() {
        Color color = Color.orange;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }

    private void createTetrominoT() {
        Color color = Color.pink;
        BlockField block = new BlockField(true, color, java.util.Optional.empty());
    }
}
