package aiModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AIController{
    private double difficultLevel;
    private Board mainBoard;

    private Thread moveThread = null;
    private boolean moveFinished;

    public AIController(double difficultLevel, Board mainBoard) {
        this.difficultLevel = difficultLevel;
        this.mainBoard = mainBoard;
    }

    public void notifyNextTetromino(Tetromino tetromino) {
        if (this.moveThread != null) {
            this.moveThread.interrupt();
        }
        this.moveFinished = false;
        this.moveThread = new Thread(()->{
            try {
                while (!this.moveFinished){
                    findBestMove();
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        });
    }

    public void update(){
        this.moveFinished = true;
    }


    private void findBestMove() {
        List<List<Field>> originalBoardStatus = this.mainBoard.getBoardStatus();

        int bestEndPosition = 0;
        int bestRotation = 0;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (int endPosition = 0; endPosition < this.mainBoard.boardLength; endPosition++) {
            for (int tetrominoRotation = 0; tetrominoRotation < 4; tetrominoRotation++) {
                Board copiedBoard = new Board(originalBoardStatus);

                setRotation(copiedBoard, tetrominoRotation);
                setEndPosition(copiedBoard, endPosition);
                simulateGravity(copiedBoard);
                double currentScore = getScore(copiedBoard);

                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestEndPosition = endPosition;
                    bestRotation = tetrominoRotation;
                }
            }
        }

        makeMove(bestEndPosition, bestRotation);
    }

    private void simulateGravity(Board copiedBoard) {
        for (int i = 0; i < 25; i++) {
            copiedBoard.moveDown();
        }
    }

    private void makeMove(int endPosition, int rotation) {
        if(rotation != 0){
            this.mainBoard.rotate();
        }else if(endPosition<0){
            this.mainBoard.moveLeft();
        }else if(endPosition>0){
            this.mainBoard.moveRight();
        }else {
            this.mainBoard.moveDown();
        }
    }

    private void setEndPosition(Board copiedBoard, int endPosition) {
        int mid = (this.mainBoard.boardLength - 1) / 2;
        int move = endPosition - mid;

        while (move > 0) {
            copiedBoard.moveRight();
            move -= 1;
        }

        while (move < 0) {
            copiedBoard.moveLeft();
            move += 1;
        }

    }

    private void setRotation(Board copiedBoard, int tetrominoRotation) {
        for (int k = 0; k < tetrominoRotation; k++) {
            copiedBoard.rotate();
        }
    }

    private double getScore(Board board) {
        return 0;
    }
}