package GUI.Block;

import java.util.LinkedList;
import java.util.List;

public class Block {
    private List<List<GridField>> initialShape = new LinkedList<>();

    public Block() {
        for (int j = 0; j < 4; j++) {
            initialShape.add(new LinkedList<>());
            for (int i = 0; i < 4; i++) {
                initialShape.get(j).add(new GridField());
            }
        }
    }

    public List<List<GridField>> getInitialShape() {
        return initialShape;
    }

    public void printBlock(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(initialShape.get(i).get(j).getBlockType()+",\t");
            }
            System.out.println();
        }
    }
}
