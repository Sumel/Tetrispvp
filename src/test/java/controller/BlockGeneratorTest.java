package controller;

import controller.mocks.Block;
import controller.mocks.BlockType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockGeneratorTest {

    @Test
    public void testIsBlockGeneratorSingleton() {
        // given
        BlockGenerator firstBlockGenerator = BlockGenerator.getBlockGenerator();
        BlockGenerator secondBlockGenerator = BlockGenerator.getBlockGenerator();
        // then
        assertEquals(firstBlockGenerator, secondBlockGenerator);
    }

    @Test
    public void testGenerateStraightBlockOnce() {
        //given
        BlockGenerator blockGenerator = BlockGenerator.getBlockGenerator();
        //when
        blockGenerator.nextStraightLineBlock(1);
        Block block = blockGenerator.spawnBlock();
        //then
        assertEquals(BlockType.I, block.getType());
    }

    @Test
    public void testGenerateStraightBlockTwice() {
        //given
        BlockGenerator blockGenerator = BlockGenerator.getBlockGenerator();
        //when
        blockGenerator.nextStraightLineBlock(2);
        Block block1 = blockGenerator.spawnBlock();
        Block block2 = blockGenerator.spawnBlock();
        //then
        assertEquals(BlockType.I, block1.getType());
        assertEquals(BlockType.I, block2.getType());
    }

}
