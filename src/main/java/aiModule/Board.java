package aiModule;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int boardLength = 10;
    private List<List<Field>> boardStatus;

    Board(){
        boardStatus = new ArrayList<>();
    }

    Board(List<List<Field>> boardStatus){
        this.boardStatus = boardStatus;
    }

    public List<List<Field>> getBoardStatus() {
        return boardStatus;
    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void rotate(){

    }

    public void moveDown(){

    }
}
