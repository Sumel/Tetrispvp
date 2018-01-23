package aiModule;


import aiModule.mocks.AIBoardMock;
import aiModule.mocks.Tetromino;

public class AIController implements  IAIController{
    private final double difficultLevel;
    private AIBoardMock mainAIBoardMock;

    private Thread moveThread = null;
    private MoveMaker moveMaker = null;

    public AIController(double difficultLevel, AIBoardMock mainAIBoardMock) {
        this.difficultLevel = difficultLevel;
        this.mainAIBoardMock = mainAIBoardMock;
    }

    public void notifyNextTetromino(Tetromino t) {
        if (this.moveMaker != null) {
            this.moveMaker.setEndFlag();
        }
        this.moveMaker = new MoveMaker(this.mainAIBoardMock, this.difficultLevel,t);
        this.moveThread = new Thread(moveMaker);
        this.moveThread.start();
    }

}