package controller;

import mocks.Block;
import mocks.BlockManager;

import java.util.ArrayList;
import java.util.List;

public class BlockGenerator {

    private static BlockGenerator blockGenerator;
    private BlockManager blockManager;

    private List<Block> blockQueue;

    public BlockGenerator(int queueSize) {

        this.blockManager = BlockManager.getBlockManager();
        blockQueue = new ArrayList<>();

        for(int i =0; i< queueSize; i++){
            nextRandomBlock();
        }
    }

    public static BlockGenerator getBlockGenerator(int size) {
        if(blockGenerator == null) {
            blockGenerator = new BlockGenerator(size);
        }
        return blockGenerator;

    }

    private void nextRandomBlock() {

        blockQueue.add(blockManager.randomBlock());

    }

    public Block spawnBlock(){

        Block block = blockQueue.remove(0);
        nextRandomBlock();
        return block;
    }

    public void nextStraightLineBlock(int numberOfLines) {

        for(int i=0;i<numberOfLines; i++){
            blockQueue.set(i, blockManager.straightBlock());

        }
    }

}
