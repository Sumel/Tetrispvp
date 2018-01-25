package controller.mocks;

public class TetrisBoard {

    public void moveLeft() {
        System.out.println("\tMoved left");
    }

    public void moveRight() {
        System.out.println("\tMoved right");
    }

    public void moveDown() {
        System.out.println("\tMove down");
    }

    public void fall() {
        System.out.println("\tFall");
    }

    public void rotate() {
        System.out.println("\tRotate");
    }

    public void spawnNewBlock(Block block) {
        System.out.println(("\nNext block spawned"));
    }

    public void endGame(){
        System.out.println("\nGame ended");
    }


    public void addBlockSpawnedListener(BlockSpawnedListener listner) {

    }
}
