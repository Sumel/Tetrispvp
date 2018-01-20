import GUI.Block.*;
import GUI.Comunication.GameModeData;
import GUI.GameMode.AIMode;
import GUI.GameMode.GameMode;
import GUI.GameMode.Mode;
import GUI.GameMode.PvPMode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Tests {

    @Test
    void blockTest() {
        BlockImplementation block = new BlockImplementation(BlockType.L);
        block.setPowerUp(3,3);
        block.setPowerUp(0,2);
        block.setPowerUp(1,1);
        block.setPowerUp(2,0);
        //System.out.println(block.getInitialShape().get(0).get(0).getState());
        Block block1 = block;
        for(int k=0; k<4; k++) {
            List<List<GridField>> fields = block1.getBoardFields();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(fields.get(i).get(j).getPowerUpID() + "\t");
                }
                System.out.println();
            }
            System.out.println();
            block.rotateClockwise();
        }
    }

    @Test
    void blockManagerTest() {
        BlockManager manager = new BlockManager();
        for (int i = 2; i < 4; i++) {
            for (int j = 1; j < 3; j++) {

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