package aiModule.mocks;

import java.util.ArrayList;
import java.util.List;

public class AIBoard implements Board {
    private final static int boardWidth = 10;
    private final static int boardHeight = 22;
    private List<List<GridField>> boardStatus; // 22 list of 10 GridFields

    private Tetromino currentTetromino;
    private int tetrominoPosX = 0, tetrominoPosY = 0;
    private int completedLines = 0;

    private class BoardSizeException extends Exception {}

    AIBoard() {
        this.boardStatus = new ArrayList<>();
        for (int i = 0; i < boardHeight; i++) {
            addTopLineOfGridFields();
        }
    }

    public AIBoard(List<List<GridField>> boardStatus) {
        this.boardStatus = boardStatus;
    }

    @Override
    public GridField getFieldAtPosition(int x, int y) {
        return boardStatus.get(y).get(x);
    }

    @Override
    public List<List<GridField>> getBoardState() {
        return boardStatus;
    }

    @Override
    public int getWidth() {
        return boardWidth;
    }

    @Override
    public int getHeight() {
        return boardHeight;
    }

    @Override
    public boolean insertTetromino(Tetromino t) {
        this.currentTetromino = t;
        this.tetrominoPosX = 5;
        this.tetrominoPosY = 0;

        try {
            setTempTetromino();
            return true;
        } catch (GridField.OccupiedException | BoardSizeException e) {
            return false;
        }
    }

    @Override
    public boolean moveLeft() {
        this.tetrominoPosX -= 1;
        return updateTempTetromino();
    }

    @Override
    public boolean moveRight() {
        this.tetrominoPosX += 1;
        return updateTempTetromino();
    }

    @Override
    public boolean rotate() {
        this.currentTetromino.rotate();
        while (this.tetrominoPosX + this.currentTetromino.getWidth() > boardWidth) {
            this.tetrominoPosX -= 1;
        }
        return updateTempTetromino();
    }

    @Override
    public boolean moveDown() {
        this.tetrominoPosY += 1;
        return updateTempTetromino();
    }

    @Override
    public void totalMoveDown() {
        boolean isMoved = moveDown();
        while (isMoved) {
            isMoved = moveDown();
        }
    }

    private boolean updateTempTetromino() {
        try {
            removeOldTempTetromino();
            setTempTetromino();
            return true;
        } catch (GridField.OccupiedException | BoardSizeException e) {
            return false;
        }
    }

    private void removeOldTempTetromino() throws GridField.OccupiedException {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                GridField gf = getFieldAtPosition(i, j);
                gf.setTempOccupied(false);
            }
        }
    }

    private void setTempTetromino() throws BoardSizeException, GridField.OccupiedException {
        for (int i = tetrominoPosX; i < this.currentTetromino.getWidth(); i++) {
            if (i >= boardWidth || i < 0) {
                throw new BoardSizeException();
            }
            for (int j = tetrominoPosY; j < this.currentTetromino.getHeight(); j++) {
                if (j >= boardHeight || j < 0) {
                    throw new BoardSizeException();
                }
                GridField gf = getFieldAtPosition(i, j);
                gf.setTempOccupied(false);
            }
        }

        removeLines();
    }

    private void removeLines() {
        int deletedLines = 0;
        for (int i = boardHeight; i>=0; i--) {
            if(this.boardStatus.get(i).stream().allMatch(GridField::isOccupied)){
                deletedLines += 1;
                this.boardStatus.remove(i);
            }
        }
        for (int i = 0; i < deletedLines; i++) {
            addTopLineOfGridFields();
        }
        this.completedLines += deletedLines;
    }

    private void addTopLineOfGridFields() {
        List<GridField> gridField = new ArrayList<>();
        for (int j = 0; j < boardWidth; j++) {
            gridField.add(new GridField());
        }
        this.boardStatus.add(0, gridField);
    }


//    public void showBoard() {
//        for (int r = 0; r < boardHeight; r++) {
//            for (int c = 0; c < boardWidth; c++) {
//                if (boardStatus.get(c).get(r).isOccupied())
//                    System.out.print(1 + " ");
//                else
//                    System.out.print(0 + " ");
//            }
//            System.out.println();
//        }
//    }


}
