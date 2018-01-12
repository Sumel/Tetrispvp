import GUI.Block.Block;
import GUI.Block.BlockManager;
import GUI.Block.BlockType;
import GUI.Block.FieldState;
import GUI.Comunication.GameModeData;
import GUI.GameMode.AIMode;
import GUI.GameMode.GameMode;
import GUI.GameMode.Mode;
import GUI.GameMode.PvPMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    @Test
    void blockTest() {
        Block block = new Block();
        //System.out.println(block.getInitialShape().get(0).get(0).getState());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(block.getInitialShape().get(i).get(j).getState(), FieldState.EMPTY);
            }
        }
    }

    @Test
    void blockManagerTest() {
        BlockManager manager = new BlockManager();
        Block block = manager.generateBlock(BlockType.O);
        for (int i = 2; i < 4; i++) {
            for (int j = 1; j < 3; j++) {
                assertEquals(block.getInitialShape().get(i).get(j).getBlockType(), BlockType.O);
            }
        }

    }

    @Test
    void gameModeDataTest() {
        GameModeData gameModeData = new GameModeData();
        GameMode gameMode = new PvPMode();
        gameModeData.setGameMode(gameMode);
        assertEquals(gameMode, gameModeData.getMode());
    }

    @Test
    void gameModeDataPVPModeTest() {
        GameModeData gameModeData = new GameModeData();
        GameMode gameMode = new PvPMode();
        gameModeData.setGameMode(gameMode);
        assertEquals(gameMode.getMode(), Mode.PVP);
    }

    @Test
    void gameModeDataAIModeTest() {
        GameModeData gameModeData = new GameModeData();
        GameMode gameMode = new AIMode(10);
        gameModeData.setGameMode(gameMode);
        assertEquals(gameMode.getMode(), Mode.AI);
    }

    @Test
    void gameModeDataAIModeLevelTest() {
        GameModeData gameModeData = new GameModeData();
        GameMode gameMode = new AIMode(10);
        gameModeData.setGameMode(gameMode);
        assertEquals(((AIMode) gameMode).getLevel(), 10);
    }

    @Test
    void gameModeIPTest() {
        GameModeData gameModeData = new GameModeData();
        GameMode gameMode = new PvPMode();
        String ip = "127.0.0.1";
        ((PvPMode) gameMode).setIpA(ip);
        gameModeData.setGameMode(gameMode);
        assertEquals(((PvPMode) gameMode).getIPA(), ip);
    }

    @Test
    void gameModeStatusNotReadyTest() {
        GameMode gameMode = new PvPMode();
        assertFalse(((PvPMode) gameMode).areTwoPlayersReady());
    }

    @Test
    void gameModeStatusReadyTest() {
        GameMode gameMode = new PvPMode();
        ((PvPMode) gameMode).setStatusForPlayerAToReady();
        ((PvPMode) gameMode).setStatusForPlayerBToReady();
        assertTrue(((PvPMode) gameMode).areTwoPlayersReady());
    }
}