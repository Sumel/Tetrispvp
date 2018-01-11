package aiModule;

import aiModule.mocks.AIBoard;

import java.io.Serializable;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MoveMaker implements Runnable, IMoveMaker, Serializable {

    private final static Coefficients defaultCoefficients =
            new Coefficients(-.5, 0.5, -0.5, -0.5 );

    private AIBoard mainAIBoard;
    private boolean stopFlag = false;

    private boolean isBestMove;
    private MoveInformation currentMove;


    MoveMaker(AIBoard mainAIBoard, double difficultLevel) {
        this.mainAIBoard = mainAIBoard;
        mainAIBoard.addListener(this);
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
                TimeUnit.MILLISECONDS.sleep(10); //should be bigger value
                if(this.isBestMove){
                    findBestMove();
                }
                makeMove();
                System.out.println(new BoardStatus(this.mainAIBoard.getBoardState()));
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
        Score score = new Score(originalBoardStatus, new Coefficients(-0.5, 0.76, -0.35, -0.18));

        this.currentMove = new MoveInformation(0,0);
        Score bestScore = new Score(Double.NEGATIVE_INFINITY);

        for (int endPosition = -9; endPosition <= 9; endPosition++) {
            for (int tetrominoRotation = 0; tetrominoRotation < 4; tetrominoRotation++) {
                AIBoard copiedAIBoard = AIBoard.deepClone(this.mainAIBoard);

                if(!setRotation(copiedAIBoard, tetrominoRotation)){
                    continue;
                }
                if(!setEndPosition(copiedAIBoard, endPosition)){
                    continue;
                }
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

    private boolean setEndPosition(AIBoard copiedAIBoard, int endPosition) {
        if (endPosition < 0) {
            while (endPosition != 0) {
                endPosition += 1;
                if(!copiedAIBoard.moveLeft()){
                    return false;
                }
            }
        } else {
            while (endPosition != 0) {
                endPosition -= 1;
                if(!copiedAIBoard.moveRight()){
                    return false;
                }
            }
        }
        return true;

    }

    private boolean setRotation(AIBoard copiedAIBoard, int tetrominoRotation) {
        for (int k = 0; k < tetrominoRotation; k++) {
            if(!copiedAIBoard.rotate()){
                return false;
            }
        }
        return true;
    }



}
