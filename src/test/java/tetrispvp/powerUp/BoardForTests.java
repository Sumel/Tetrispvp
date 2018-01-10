package tetrispvp.powerUp;

import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;
import powerUps.mocks.MutableBoard;

import java.util.ArrayList;
import java.util.List;

public class BoardForTests {
    public MutableBoard getBoard(){
        MutableBoard board = new MutableBoard();
        List<List<GridField>> b = new ArrayList<>();
        List<GridField> line = new ArrayList<>();

        for (int j = 0; j < board.getWidth(); j++) {
            GridField gf = new GridField();
            gf.setState(FieldState.OCCUPIED);
            line.add(gf);
        }
        b.add(line);

        for(int i = 1; i < board.getHeight() - 3; i++){
            line = new ArrayList<>();
            for(int j = 0; j < board.getWidth(); j++){
                line.add(new GridField());
            }
            b.add(line);
        }

        line = new ArrayList<>();
        for(int i = board.getHeight() - 3; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                GridField gf = new GridField();
                gf.setState(FieldState.OCCUPIED);
                line.add(gf);
            }
            b.add(line);
        }
        board.setBoard(b);
        printBoard(board);

        return board;
    }

    public List<GridField> getLine(FieldState state, int size){
        List<GridField> line = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            GridField gf = new GridField();
            gf.setState(state);
            line.add(gf);
        }

        return line;
    }

    public void printBoard(MutableBoard board){
        for(int i = 0; i < board.getHeight(); i++){
            for (int j = 0; j < board.getWidth(); j++){
                List<GridField> temp = board.getBoard().get(i);
                System.out.print(temp.get(j).getState() + " ");
            }
            System.out.println();
        }
    }
}
