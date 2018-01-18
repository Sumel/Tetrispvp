package controller;

import mocks.Board;

public class MoveController implements IMoveController{

    Board board;
    TetrisTimer tetrisTimer;

    MoveController(Board board, TetrisTimer tetrisTimer){
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
