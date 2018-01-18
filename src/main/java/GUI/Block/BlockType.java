package GUI.Block;

import javafx.scene.paint.Color;

public enum BlockType {
    I(Color.CYAN),
    T(Color.PURPLE),
    L(Color.ORANGE),
    J(Color.BLUE),
    S(Color.GREEN),
    Z(Color.RED),
    O(Color.YELLOW);

    private Color color;

    BlockType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
