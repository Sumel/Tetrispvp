package controller;

import GUI.Block.Block;
import GUI.Block.BlockManager;

import java.util.LinkedList;

public class BlockGenerator {

    private static BlockGenerator blockGenerator;
    private static BlockManager blockManager;

    private LinkedList<Block> blockQueue;
    private final int blockQueueSize = 20;

    private BlockGenerator() {
        blockManager = BlockManager.getInstance();
        blockQueue = new LinkedList<>();

        for (int i = 0; i < blockQueueSize; i++) {
            addRandomBlockToQueue();
        }
    }

    public static BlockGenerator getBlockGenerator() {
        if (blockGenerator == null) {
            blockGenerator = new BlockGenerator();
        }
        return blockGenerator;
    }

    private void addRandomBlockToQueue() {
        blockQueue.addLast(blockManager.getRandomBlock());
    }


    private Block getFirstBlock() {
        return blockQueue.removeFirst();
    }

    private void addStraightBlockToQueue() {
        blockQueue.addFirst(blockManager.getStraightBlock());
    }

    public Block spawnBlock() {
        addRandomBlockToQueue();
        return getFirstBlock();
    }

    public void nextStraightLineBlock(int numberOfLines) {
        for (int line = 0; line < numberOfLines; line++) {
            addStraightBlockToQueue();
        }
    }

}
