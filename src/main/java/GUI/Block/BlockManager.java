package GUI.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockManager {
    private static BlockManager instance = new BlockManager();

    private Map<BlockType, Block> blocks;

    public static BlockManager getInstance(){
        return instance;
    };

    private BlockManager() {
        blocks = new HashMap<>();
        for (int i = 0; i < BlockType.values().length; i++) {
            blocks.put(BlockType.values()[i], new BlockImplementation(BlockType.values()[i]));
        }
    }

    public Block getRandomBlock() {
        Random generator = new Random();
        int i = generator.nextInt(BlockType.values().length - 1);
        return blocks.get(BlockType.values()[i]);
    }

    public Block getStraightBlock(){
        return blocks.get(BlockType.I);
    }
}
