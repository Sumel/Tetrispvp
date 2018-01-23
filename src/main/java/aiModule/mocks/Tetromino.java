package aiModule.mocks;


import java.util.*;

public class Tetromino {
    private final static Map<ShapeType, List<String[]>> shapes = new HashMap<>();

    static {
        List<String[]> shapeO, shapeI;

        shapeO = new ArrayList<>();
        shapeO.add(new String[]{
                "xx",
                "xx"
        });

        shapeI = new ArrayList<>();
        shapeI.add(new String[]{
            "xxxx"
        });
        shapeI.add(new String[]{
                "x",
                "x",
                "x",
                "x"
        });

        shapes.put(ShapeType.I, shapeI);
        shapes.put(ShapeType.O, shapeO);
    }

    private enum ShapeType{
        O, I
    }

    private ShapeType currentShape;
    private int rotation;

    Tetromino() {
        this.currentShape = new Random().nextBoolean() ? ShapeType.I : ShapeType.O;
        this.rotation = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String aShape : getShape()) {
            str.append(aShape);
        }

        return "Tetromino{"+ str.toString() + "}";
    }

    public void rotate() {
        this.rotation += 1;
        this.rotation %= Tetromino.shapes.get(this.currentShape).size();
    }

    public int getWidth(){
        //all column must have the same dimensions
        return getShape()[0].length();
    }

    public int getHeight(){
        return getShape().length;
    }

    private String[] getShape(){
        return Tetromino.shapes.get(this.currentShape).get(this.rotation);
    }

}
