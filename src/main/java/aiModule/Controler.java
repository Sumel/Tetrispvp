package aiModule;

public class Controler {
    private AIController aiController;
    private Board board;

    Controler(){
        this.board = new Board();
        this.aiController = new AIController(0.1, this.board);
    }

    public void nextTetromino(){
        Tetromino t = new Tetromino();
        this.aiController.notifyNextTetromino(t);

    }


}
