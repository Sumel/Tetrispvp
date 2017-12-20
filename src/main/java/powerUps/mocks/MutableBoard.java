package powerUps.mocks;

import java.util.List;

public class MutableBoard {
	
	List<List<GridField>> board;
	List<LineClearedListener> lineClearedListeners;
	
	void reverse(){
		int size = board.size();
		for(int i = 0; i < size / 2; i++){
			List<GridField> temp = board.get(i);
			board.set(i, board.get(size - i - 1));
			board.set(size - i - 1, temp);
		}
	}
	
	void clearLine(int linePosition){
		List<GridField> clearedLine = board.get(linePosition);
		for(int i = linePosition; i < board.size() - 1; i++){
			board.set(i, board.get(i+1));
		}
		for(LineClearedListener l : lineClearedListeners)
			l.lineCleared(clearedLine);
	}
	
	void addLine(int linePosition){
		for(GridField g : board.get(linePosition))
			g.setState(FieldState.BLOCKED);
	}
	
	void addLinesClearedListener(LineClearedListener newListener){
		lineClearedListeners.add(newListener);
	}
}
