package controller;

import controller.mocks.TetrisBoard;

public class MoveController implements IMoveController {

    public TetrisBoard board;
    TetrisTimer tetrisTimer;

    MoveController(TetrisBoard board, TetrisTimer tetrisTimer) {
        this.board = board;
        this.tetrisTimer = tetrisTimer;
        tetrisTimer.init();
    }

    @Override
    public void moveLeft() {
        board.moveLeft();
    }

    @Override
    public void moveRight() {
        board.moveRight();
    }

    @Override
    public void moveDown() {
        board.moveDown();
        tetrisTimer.reset();
    }

    @Override
    public void fall() {
        board.fall();
        tetrisTimer.reset();
    }

    @Override
    public void rotate() {
        board.rotate();
    }

}
