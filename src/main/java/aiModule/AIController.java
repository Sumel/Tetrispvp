package aiModule;


import aiModule.mocks.AIBoard;

public class AIController implements  IAIController{
    private final double difficultLevel;
    private AIBoard mainAIBoard;

    private Thread moveThread = null;
    private MoveMaker moveMaker = null;

    public AIController(double difficultLevel, AIBoard mainAIBoard) {
        this.difficultLevel = difficultLevel;
        this.mainAIBoard = mainAIBoard;
    }

    public void notifyNextTetromino() {
        if(this.moveMaker != null){
            this.moveMaker.setEndFlag();
        }
        this.moveMaker = new MoveMaker(this.mainAIBoard, this.difficultLevel);
        this.moveThread = new Thread(moveMaker);
        this.moveThread.start();
    }

}