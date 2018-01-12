package GUI.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BlockManager {
    private Map<BlockType, Block> blocks;

    public BlockManager() {
        blocks=new HashMap<>();
        for (int i = 0; i < BlockType.values().length; i++) {
            blocks.put(BlockType.values()[i], generateBlock(BlockType.values()[i]));
        }
    }

    public Block getRandomBlock() {
        Random generator = new Random();
        int i = generator.nextInt(BlockType.values().length - 1);
        return blocks.get(BlockType.values()[i]);
    }

    public Block generateBlock(BlockType type) {
        Block block = new Block();
        List<List<GridField>> initialShape = block.getInitialShape();

        switch (type) {
            case I:
                for (int i = 0; i < 4; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.I);
                break;
            case T:
                for (int i = 0; i < 3; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.T);
                initialShape.get(3).get(1).setBlock(BlockType.T);
                break;
            case L:
                for (int i = 0; i < 3; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.L);
                initialShape.get(3).get(0).setBlock(BlockType.L);
                break;
            case J:
                for (int i = 0; i < 3; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.J);
                initialShape.get(3).get(2).setBlock(BlockType.J);
                break;
            case S:
                for (int i = 1; i < 3; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.S);
                for (int i = 0; i < 2; i++)
                    initialShape.get(3).get(i).setBlock(BlockType.S);
                break;
            case Z:
                for (int i = 0; i < 2; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.Z);
                for (int i = 1; i < 3; i++)
                    initialShape.get(3).get(i).setBlock(BlockType.Z);
                break;
            case O:
                for (int i = 1; i < 3; i++)
                    initialShape.get(2).get(i).setBlock(BlockType.O);
                for (int i = 1; i < 3; i++)
                    initialShape.get(3).get(i).setBlock(BlockType.O);
                break;
        }

        return block;
    }


}
