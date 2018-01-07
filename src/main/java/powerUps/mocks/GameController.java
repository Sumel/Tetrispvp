package powerUps.mocks;

import java.util.LinkedList;
import java.util.List;

public class GameController {
	
	List<Block> nextBlocks = new LinkedList();
	
	public void setNextBlockAsStraightLine(int howMany){
		for(int i = 0; i < howMany && i < nextBlocks.size(); i++)
			nextBlocks.set(i, new Block(BlockType.STRAIGHT_LINE));
	}
	
}
