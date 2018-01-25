package controller;

import controller.mocks.TetrisBoard;
import org.junit.Test;

public class BoardMoveIntegrationTest {


    @Test
    public void testBoardMoving(){
        // given
        TetrisBoard board = new TetrisBoard();
        TetrisTimer timer = new TetrisTimer(30);
        MoveController moveController = new MoveController(board, timer);
        // when
        moveController.moveLeft();
        moveController.moveRight();
        moveController.moveDown();
        moveController.fall();
        moveController.rotate();
    }

}
