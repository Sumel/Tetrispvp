package powerUps.mocks;

public class GameController {
	
	Block nextBlock;
	
	public void setNextBlockAsStraightLine(){
		nextBlock.setType(BlockType.STRAIGHT_LINE);
	}
	
}
