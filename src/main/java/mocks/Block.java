package mocks;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private BlockType type;
    private List<BlockField> fields;

    public Block(BlockType type) {
        this.type = type;
        this.fields = new ArrayList<>();
        for (int i  = 0; i < 4; i++) {
            this.fields.add(new BlockField());
        }
    }

    public BlockType getType() {
        return this.type;
    }

    /* sets powerUp on field of given id,
     * fieldId is integer from 0 to 4 */
    public void setPowerUpAtFiled(int fieldId, int powerUp) {
        this.fields.get(fieldId).setPowerUp(powerUp);
    }

}
