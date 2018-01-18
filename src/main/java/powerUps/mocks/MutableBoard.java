package powerUps.mocks;

import java.util.ArrayList;
import java.util.List;

public class MutableBoard {
	
	private List<List<GridField>> board;
	private List<LinesClearedListener> linesClearedListeners = new ArrayList<>();


	
	public void flipBoard(){
		boolean end = false;
		int notToReverse = 0;
		for(int i = 0; i < getHeight() && !end; i++){
			for(int j = 0; j < getWidth() && !end; j++){
				if(board.get(i).get(j).getState() == FieldState.OCCUPIED)
					end = true;
			}
			if(!end)
				notToReverse++;
		}
		for(int i = notToReverse; i < getHeight() -((getHeight() - notToReverse)/2) ; i++){
			List<GridField> temp = board.get(i);
			board.set(i, board.get(getHeight() - (i - notToReverse) - 1));
			board.set(getHeight() - (i - notToReverse) - 1, temp);
		}

	}
	
	public void clearLine(int linePosition){

		List<GridField> clearedLine = board.get(linePosition);
		for(int i = linePosition; i > 0; i--){
			board.set(i, board.get(i-1));
		}
		List<GridField> newLine = new ArrayList<>();
		for(int i = 0; i < getWidth(); i++) {
            newLine.add(new GridField());
        }
		board.set(0, newLine);

		for(LinesClearedListener l : linesClearedListeners)
			l.lineCleared(clearedLine);
	}
	
	public void addLine(int lineNumber, GridField field, boolean moveUp){
        for(int i = 0; i < getHeight()-2; i++){
            board.set(i, board.get(i+1));
        }

        List<GridField> line;
        for(int i = getHeight()-lineNumber; i < getHeight(); i++) {
            line = new ArrayList<>();
            for (int j = 0; j < getWidth(); j++) {
                GridField gf = new GridField();
                gf.setState(FieldState.BLOCKED);
                line.add(gf);
            }
            board.set(i, line);
        }
	}
	
	public void addLinesClearedListener(LinesClearedListener newListener){
		linesClearedListeners.add(newListener);
	}
	
	public int getHeight() {
        return 20;
    }

    public int getWidth() {
        return 10;
    }

    public List<List<GridField>> getBoard() {
        return board;
    }

    public void setBoard(List<List<GridField>> board) {
        this.board = board;
    }
    
    public int lineClearedListenersSize(){
    	return linesClearedListeners.size();
    }
    
    public int highestRowWithLockedFields() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (board.get(i).get(j).getState() == FieldState.BLOCKED) {
                    return i;
                }
            }
        }
        return getHeight();
    }
}
