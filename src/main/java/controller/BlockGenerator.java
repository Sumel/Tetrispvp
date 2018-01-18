package controller;

import mocks.Block;
import mocks.BlockManager;

public class BlockGenerator {

    private static BlockGenerator blockGenerator;
    private BlockManager blockManager;

    public BlockGenerator() {
        this.blockManager = BlockManager.getBlockManager();
    }

    public static BlockGenerator getBlockGenerator() {
        if(blockGenerator == null) {
            blockGenerator = new BlockGenerator();
        }
        return blockGenerator;
    }

    public Block nextRandomBlock() {
        return blockManager.randomBlock();

    }

    public Block nextStraightLineBlock() {
        return blockManager.straightBlock();
    }

}
