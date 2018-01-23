package aiModule;

import aiModule.mocks.AIBoardMock;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MoveMaker implements Runnable, IMoveMaker, Serializable {

    private final static Coefficients defaultCoefficients =
            new Coefficients(-.5, 0.5, -0.5, -0.5);

    private AIBoardMock mainAIBoardMock;
    private boolean stopFlag = false;

    private boolean isBestMove;
    private MoveInformation currentMove;


    MoveMaker(AIBoardMock mainAIBoardMock, double difficultLevel) {
        this.mainAIBoardMock = mainAIBoardMock;
        mainAIBoardMock.addListener(this);
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
                TimeUnit.MILLISECONDS.sleep(1000); //should be bigger value
                if (this.isBestMove) {
                    try {
                        findBestMove();
                    }catch (IndexOutOfBoundsException e){

                    }
                }
                makeMove();
                System.out.println(this.mainAIBoardMock);
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
        BoardStatus originalBoardStatus = new BoardAI(this.mainAIBoardMock.getBoardState());

        this.currentMove = new MoveInformation(0, 0);
        Score bestScore = new Score(Double.NEGATIVE_INFINITY);

        for (int endPosition = -9; endPosition <= 9; endPosition++) {
            for (int tetrominoRotation = 0; tetrominoRotation < 4; tetrominoRotation++) {
                BoardAI copy = new BoardAI(this.mainAIBoardMock.getBoardState());

                setRotation(copy, tetrominoRotation);
                setEndPosition(copy, endPosition);
                simulateGravity(copy);

                Score score = new Score(new Coefficients(-0.5, 0.76, -0.35, -0.18));
                score.compareWithNewBoard(copy);

                if (score.hasBetterValue(bestScore)) {
                    bestScore = score;
                    this.currentMove = new MoveInformation(endPosition, tetrominoRotation);
                }
            }
        }
    }

    private void simulateGravity(BoardAI copy) {
        for (int i = 0; i < 20; i++) {
            copy.moveDown();
        }
    }

    private void makeMove() {
        if (this.currentMove.rotation != 0) {
            this.mainAIBoardMock.rotate();
        } else if (this.currentMove.position < 0) {
            this.mainAIBoardMock.moveLeft();
        } else if (this.currentMove.position > 0) {
            this.mainAIBoardMock.moveRight();
        } else {
//            this.mainAIBoardMock.totalMoveDown();
            this.mainAIBoardMock.moveDown();
        }
    }

    private void setEndPosition(BoardAI copy, int endPosition) {
        if (endPosition < 0) {
            while (endPosition != 0) {
                endPosition += 1;
                copy.moveLeft();
            }
        } else {
            while (endPosition != 0) {
                endPosition -= 1;
                copy.moveRight();
            }
        }
    }

    private void setRotation(BoardAI boardAI, int tetrominoRotation) {
        for (int k = 0; k < tetrominoRotation; k++) {
            boardAI.rotate();
        }
    }


}
