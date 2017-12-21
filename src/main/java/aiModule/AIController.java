package aiModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AIController{
    private double difficultLevel;
    private Board mainBoard;

    private Thread moveThread = null;

    public AIController(double difficultLevel, Board mainBoard) {
        this.difficultLevel = difficultLevel;
        this.mainBoard = mainBoard;
    }

    public synchronized void notifyNextTetromino(Tetromino tetromino) {
        MoveMaker moveMaker = new MoveMaker(this.mainBoard, this.difficultLevel);
        this.moveThread = new Thread(moveMaker);
        this.moveThread.start();
    }

}