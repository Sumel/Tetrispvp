package mocks;

public class Board {

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

    public void spawnNextBlock(Block block) {
        System.out.println(("\nNext block spawned"));
    }

    public void endGame(){
        System.out.println("\nGame ended");
    }

}
