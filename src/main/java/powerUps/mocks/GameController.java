package powerUps.mocks;

public class GameController {
	
	Block nextBlock;
	
	void setNextBlockAsStraightLine(){
		nextBlock.setType(BlockType.STRAIGHT_LINE);
	}
	
}
