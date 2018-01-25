package powerUps.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
	
	private List<Block> nextBlocks = new ArrayList<>();
    private Random random = new Random();
	
	public void setNextBlockAsStraightLine(int howMany){
		for(int i = 0; i < howMany && i < nextBlocks.size(); i++)
			nextBlocks.set(i, new Block(BlockType.STRAIGHT_LINE));
	}

	public void addRandomBlockNext(){
	    if(nextBlocks.size() < 3)
	        this.nextBlocks.add(new Block(BlockType.values()[random.nextInt(BlockType.values().length)]));
	    else
            this.nextBlocks.set(2, new Block(BlockType.values()[random.nextInt(BlockType.values().length)]));
    }

    public List<Block> getNextBlocks() {
	    for(Block b: nextBlocks){
	        System.out.print(b.getType() + ", ");
        }
        System.out.println();
        return this.nextBlocks;
    }

    public void setNextBlocks(List<Block> nextBlocks) {
        this.nextBlocks = nextBlocks;
    }
}
