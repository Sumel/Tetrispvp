package aiModule;

import aiModule.mocks.AIBoard;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MoveMaker implements Runnable, IMoveMaker {

    private final static Coefficients defaultCoefficients =
            new Coefficients(-.5, 0.5, -0.5, -0.5 );

    private AIBoard mainAIBoard;
    private boolean stopFlag = false;

    private boolean isBestMove;
    private MoveInformation currentMove;


    MoveMaker(AIBoard mainAIBoard, double difficultLevel) {
        this.mainAIBoard = mainAIBoard;
        choseTypeOfMove(difficultLevel);
    }

    private void choseTypeOfMove(double difficultLevel) {
        Random random = new Random();
        this.isBestMove = random.nextDouble() < difficultLevel;
        if (!this.isBestMove) {
            int position = random.nextInt(10) - 5;
            int rotation = random.nextInt(4);
            this.currentMove = new MoveInformation(position, rotation);
        }
    }

    @Override
    public void run() {
        while (!this.stopFlag) {
            try {
                TimeUnit.SECONDS.sleep(1);
                if(this.isBestMove){
                    findBestMove();
                }
                makeMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void setEndFlag() {
        this.stopFlag = true;
    }

    private void findBestMove() {
        BoardStatus originalBoardStatus = new BoardStatus(this.mainAIBoard.getBoardState());
        Score score = new Score(originalBoardStatus, defaultCoefficients);

        this.currentMove = new MoveInformation(0,0);
        Score bestScore = new Score(Double.NEGATIVE_INFINITY);

        for (int endPosition = -9; endPosition <= 9; endPosition++) {
            for (int tetrominoRotation = 0; tetrominoRotation < 4; tetrominoRotation++) {
                AIBoard copiedAIBoard = new AIBoard(originalBoardStatus.value);

                setRotation(copiedAIBoard, tetrominoRotation);
                setEndPosition(copiedAIBoard, endPosition);
                simulateGravity(copiedAIBoard);

                score.compareWithNewBoard(new BoardStatus(copiedAIBoard.getBoardState()));

                if(score.hasBetterValue(bestScore)){
                    bestScore = score;
                    this.currentMove = new MoveInformation(endPosition, tetrominoRotation);
                }
            }
        }
    }

    private void simulateGravity(AIBoard copiedAIBoard) {
        copiedAIBoard.totalMoveDown();
    }

    private void makeMove() {
        if (this.currentMove.rotation != 0) {
            this.mainAIBoard.rotate();
        } else if (this.currentMove.position < 0) {
            this.mainAIBoard.moveLeft();
        } else if (this.currentMove.position > 0) {
            this.mainAIBoard.moveRight();
        } else {
            this.mainAIBoard.totalMoveDown();
        }
    }

    private void setEndPosition(AIBoard copiedAIBoard, int endPosition) {
        if (endPosition < 0) {
            while (endPosition != 0) {
                endPosition += 1;
                copiedAIBoard.moveLeft();
            }
        } else {
            while (endPosition != 0) {
                endPosition -= 1;
                copiedAIBoard.moveRight();
            }
        }

    }

    private void setRotation(AIBoard copiedAIBoard, int tetrominoRotation) {
        for (int k = 0; k < tetrominoRotation; k++) {
            copiedAIBoard.rotate();
        }
    }



}
