package aiModule.mocks;

import aiModule.AIController;

public class Controller {
    private AIController aiController;
    private AIBoardMock AIBoardMock;

    public Controller(){
        this.AIBoardMock = new AIBoardMock();
        this.aiController = new AIController(1, this.AIBoardMock);
    }

    public void nextTetromino(){
        Tetromino t = new Tetromino();
        this.AIBoardMock.insertTetromino(t);
        printBoard();
        this.aiController.notifyNextTetromino();
    }

    public void printBoard(){
        System.out.println(this.AIBoardMock);
    }
}
