package controller.mocks;

import java.util.Random;

public class BlockManager {

    private static BlockManager blockManager = null;
    private Random random = new Random();

    public static BlockManager getBlockManager(){
        if(blockManager == null){
            blockManager = new BlockManager();
        }
        return blockManager;
    }

    public Block getRandomBlock() {
        return new Block(BlockType.values()[random.nextInt(BlockType.values().length)]);
    }

    public Block getStraightBlock() {
        return new Block(BlockType.I);
    }
}
