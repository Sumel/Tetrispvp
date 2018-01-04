package tetrispvp.board;


import tetrispvp.board.Mocks.Block;

import java.awt.*;


class DTETBlockMover implements BlockMover {

    private Point blockPosition;
    private Block currentBlock;
    private MutableBoard board;
    private CollisionChecker collisionChecker;
    private final static Point[] wallkicksClockwise = {
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 1),
            new Point(-1, 1)
    };

    private final static Point[] wallkicksCounterClockwise = {
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 1),
            new Point(1, 1)
    };

    public DTETBlockMover(MutableBoard board) {
        this.board = board;
        blockPosition = new Point();
        collisionChecker = new NormalCollisionChecker(board);
    }

    @Override
    public void spawnNewBlock(Block newBlock) {
        //TODO: position should depend on block type.
        blockPosition.setLocation(board.getWidth() / 2, -2);
    }

    @Override
    public boolean moveDown() {
        return move(0, 1);
    }

    @Override
    public boolean moveLeft() {
        return move(-1, 0);
    }

    @Override
    public boolean moveRight() {
        return move(1, 0);
    }

    @Override
    public boolean moveToBottom() {
        return move(0, 30);
    }

    // returns false if block didn't move at all
    // we move in the x axis first, and then y
    private boolean move(int x, int y) {
        if (currentBlock == null) {
            throw new IllegalStateException("Block needs to exist before it can be moved.");
        }
        boolean result = false;
        Point workingPosition = new Point(blockPosition);

        for (int i = 0; i < Math.abs(x); ++i) {
            Point testingPosition = new Point(workingPosition);
            testingPosition.x += Math.signum(x);
            if (collisionChecker.collides(testingPosition, currentBlock)) {
                break;
            } else {
                workingPosition = testingPosition;
                result = true;
            }
        }

        for (int i = 0; i < Math.abs(y); ++i) {
            Point testingPosition = new Point(workingPosition);
            testingPosition.y += Math.signum(y);
            if (collisionChecker.collides(testingPosition, currentBlock)) {
                break;
            } else {
                workingPosition = testingPosition;
                result = true;
            }
        }
        if (result) {
            changePosition(workingPosition);

        }
        return result;
    }

    private void changePosition(Point newPosition) {
        //TODO

        blockPosition = newPosition;
    }

    private void removeFieldsFromBoard() {

    }

    private int getValidWallkick(Point[] wallkicks) {
        for (int i = 0; i < wallkicks.length; ++i) {
            Point point = wallkicks[i];
            Point newPosition = new Point(blockPosition.x + point.x, blockPosition.y + point.y);
            if (!collisionChecker.collides(newPosition, currentBlock)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean rotateClockwise() {
        currentBlock.rotateClockwise();
        if (!collisionChecker.collides(blockPosition, currentBlock)) {
            return true;
        }

        int kickIndex = getValidWallkick(wallkicksClockwise);
        if (kickIndex == -1) {
            //reverse rotation, because there's no space to rotate
            currentBlock.rotateCounterClockwise();
            return false;
        } else {
            Point point = wallkicksClockwise[kickIndex];
            blockPosition = new Point(blockPosition.x + point.x, blockPosition.y + point.y);
            return true;
        }
    }

    @Override
    public boolean rotateCounterClockwise() {
        currentBlock.rotateCounterClockwise();
        if (!collisionChecker.collides(blockPosition, currentBlock)) {
            return true;
        }

        int kickIndex = getValidWallkick(wallkicksCounterClockwise);
        if (kickIndex == -1) {
            //reverse rotation, because there's no space to rotate
            currentBlock.rotateClockwise();
            return false;
        } else {
            Point point = wallkicksClockwise[kickIndex];
            blockPosition = new Point(blockPosition.x + point.x, blockPosition.y + point.y);
            return true;
        }
    }

    @Override
    public boolean isBlockCollidingBelow() {
        return false;
    }

    //TODO
    @Override
    public void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener) {

    }

    private void onCollision() {

    }
}
