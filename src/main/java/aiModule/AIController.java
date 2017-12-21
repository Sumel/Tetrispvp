package aiModule;


public class AIController implements  IAIController{
    private double difficultLevel;
    private Board mainBoard;

    private Thread moveThread = null;

    public AIController(double difficultLevel, Board mainBoard) {
        this.difficultLevel = difficultLevel;
        this.mainBoard = mainBoard;
    }

    public void notifyNextTetromino(Tetromino tetromino) {
        MoveMaker moveMaker = new MoveMaker(this.mainBoard, this.difficultLevel);
        this.moveThread = new Thread(moveMaker);
        this.moveThread.start();
    }

}