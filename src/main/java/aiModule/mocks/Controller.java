package aiModule.mocks;

import aiModule.AIController;
import aiModule.BoardStatus;

public class Controller {
    private AIController aiController;
    private aiModule.mocks.AIBoard AIBoard;

    public Controller(){
        this.AIBoard = new AIBoard();
        this.aiController = new AIController(1, this.AIBoard);
    }

    public void nextTetromino(){
        Tetromino t = new Tetromino();
        this.AIBoard.insertTetromino(t);
        printBoard();
        this.aiController.notifyNextTetromino();
    }

    public void printBoard(){
        BoardStatus bs = new BoardStatus(AIBoard.getBoardState());
        System.out.println(bs);
    }
}
