package GUI.Block;

import java.util.List;

public interface Block {
    List<List<tetrispvp.board.GridField>> getBoardFields();

    void rotateClockwise();

    void rotateCounterClockwise();

    void setPowerUp(int numberOfField, int powerUpId);
}
