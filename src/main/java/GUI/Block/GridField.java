package GUI.Block;


import javafx.scene.paint.Color;

public class GridField {

    private long powerUpId = -1;
    private FieldState state = FieldState.EMPTY;
    private BlockType blockType=null;
    private Color color;

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        this.state = state;
    }

    boolean hasPowerUp(){
        if(powerUpId == -1)
            return false;
        return true;
    }

    long getPowerUp(){
        return powerUpId;
    }

    public void setPowerUp(long powerUpId) {
        this.powerUpId = powerUpId;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlock(BlockType type){
        this.blockType=type;
        this.state=FieldState.OCCUPIED;
        switch (type) {
            case I:
                this.color=Color.CYAN;
                break;
            case T:
                this.color=Color.PURPLE;
                break;
            case L:
                this.color=Color.ORANGE;
                break;
            case J:
                this.color=Color.BLUE;
                break;
            case S:
                this.color=Color.GREEN;
                break;
            case Z:
                this.color=Color.RED;
                break;
            case O:
                this.color=Color.YELLOW;
                break;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
