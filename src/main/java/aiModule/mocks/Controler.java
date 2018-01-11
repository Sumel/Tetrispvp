package aiModule.mocks;

import aiModule.AIController;

public class Controler {
    private AIController aiController;
    private aiModule.mocks.AIBoard AIBoard;

    Controler(){
        this.AIBoard = new AIBoard();
        this.aiController = new AIController(0.1, this.AIBoard);
    }

    public void nextTetromino(){
        Tetromino t = new Tetromino();
        this.aiController.notifyNextTetromino();

    }


}
