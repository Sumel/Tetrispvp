package tetrispvp.board;


import com.google.inject.Inject;
import tetrispvp.board.Mocks.Block;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

class DTETBlockMover implements BlockMover {

    private Point blockPosition;
    private Block currentBlock;
    private MutableBoard board;
    private CollisionChecker collisionChecker;
    private List<BlockCollidedBelowListener> blockCollidedBelowListeners = new LinkedList<BlockCollidedBelowListener>();
    private List<BlockSpawnedListener> blockSpawnedListeners = new LinkedList<BlockSpawnedListener>();
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

    @Inject
    public DTETBlockMover(MutableBoard board, CollisionChecker collisionChecker) {
        this.board = board;
        blockPosition = new Point();
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void spawnNewBlock(Block newBlock) {
        if (currentBlock != null) {
            solidifyBlock();
        }
        blockPosition.setLocation(board.getWidth() / 2, -2);
        currentBlock = newBlock;
        showNewBlock();
        onBlockSpawned(currentBlock);
    }

    private void solidifyBlock() {
        List<GridFieldWithPosition> fields = new ArrayList<GridFieldWithPosition>();
        addSolidFieldsToList(currentBlock, blockPosition, fields);
        board.setFields(fields);
    }

    private void showNewBlock() {
        List<GridFieldWithPosition> fields = new ArrayList<GridFieldWithPosition>();
        addBlockFieldsToList(currentBlock, blockPosition, fields);
        board.setFields(fields);
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
        List<Point> collidingPoints = null;
        for (int i = 0; i < Math.abs(y); ++i) {
            Point testingPosition = new Point(workingPosition);
            testingPosition.y += Math.signum(y);
            if (collisionChecker.collides(testingPosition, currentBlock, collidingPoints)) {
                break;
            } else {
                workingPosition = testingPosition;
                result = true;
            }
        }
        if (result) {
            changePosition(workingPosition);
            if (collidingPoints != null) {
                onCollision(collidingPoints);
            }
        }
        return result;
    }

    public void changePosition(Point newPosition) {
        List<GridFieldWithPosition> fields = new ArrayList<GridFieldWithPosition>();
        addEmptyFieldsToList(currentBlock, blockPosition, fields);
        addBlockFieldsToList(currentBlock, newPosition, fields);
        blockPosition = newPosition;
        board.setFields(fields);
    }

    @Override
    public Point getPosition() {
        return (Point) blockPosition.clone();
    }

    private void removeFieldFromList(List<GridFieldWithPosition> fields, Point point) {
        Predicate<GridFieldWithPosition> removePredicate = g -> g.getRowNumber() == point.y && g.getColumnNumber() == point.x;
        fields.removeIf(removePredicate);
    }

    private void addEmptyFieldsToList(Block block, Point position, List<GridFieldWithPosition> fields) {
        List<List<GridField>> blockFields = block.getBoardFields();
        GridField emptyField = BoardField.GetEmptyBoardField();
        for (int i = 0; i < blockFields.size(); ++i) {
            for (int j = 0; j < blockFields.get(i).size(); j++) {
                GridField currentBlockField = blockFields.get(i).get(j);
                if (currentBlockField.isOccupied()) {
                    int row = i + position.y;
                    int column = j + position.x;
                    removeFieldFromList(fields, new Point(column, row));
                    if (column >= 0 && row >= 0 && !board.getFieldAtPosition(column, row).isLocked()) {
                        fields.add(new GridFieldWithPosition(row, column, emptyField));
                    }
                }
            }
        }
    }

    private void addBlockFieldsToList(Block block, Point position, List<GridFieldWithPosition> fields) {
        List<List<GridField>> blockFields = block.getBoardFields();
        for (int i = 0; i < blockFields.size(); ++i) {
            for (int j = 0; j < blockFields.get(i).size(); j++) {
                GridField currentBlockField = blockFields.get(i).get(j);
                if (currentBlockField.isOccupied()) {
                    int row = i + position.y;
                    int column = j + position.x;
                    removeFieldFromList(fields, new Point(column, row));
                    if (column >= 0 && row >= 0 && !board.getFieldAtPosition(column, row).isLocked()) {
                        fields.add(new GridFieldWithPosition(row, column, currentBlockField));
                    }
                }
            }
        }
    }

    private void addSolidFieldsToList(Block block, Point position, List<GridFieldWithPosition> fields) {
        List<List<GridField>> blockFields = block.getBoardFields();
        for (int i = 0; i < blockFields.size(); ++i) {
            for (int j = 0; j < blockFields.get(i).size(); j++) {
                GridField currentBlockField = blockFields.get(i).get(j);
                if (currentBlockField.isOccupied()) {
                    GridField currentBoardField = new BoardField(true, true, currentBlockField.getColor(), currentBlockField.getPowerUpID());
                    int row = i + position.y;
                    int column = j + position.x;
                    removeFieldFromList(fields, new Point(column, row));
                    fields.add(new GridFieldWithPosition(row, column, currentBoardField));
                }
            }
        }
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
        return rotateCurrentBlockAndApply(true);
    }

    @Override
    public boolean rotateCounterClockwise() {
        return rotateCurrentBlockAndApply(false);
    }

    private boolean rotateCurrentBlockAndApply(boolean rotateClockwise) {
        rotateBlock(currentBlock, rotateClockwise);
        if (!collisionChecker.collides(blockPosition, currentBlock)) {
            applyRotationToBoard(rotateClockwise, currentBlock, blockPosition, blockPosition);
            return true;
        }
        Point[] wallkicks = (rotateClockwise) ? wallkicksClockwise : wallkicksCounterClockwise;
        int kickIndex = getValidWallkick(wallkicks);
        if (kickIndex == -1) {
            //reverse rotation, because there's no space to rotate
            rotateBlock(currentBlock, !rotateClockwise);
            return false;
        } else {
            Point point = wallkicks[kickIndex];
            Point newBlockPosition = new Point(blockPosition.x + point.x, blockPosition.y + point.y);
            applyRotationToBoard(rotateClockwise, currentBlock, blockPosition, newBlockPosition);
            blockPosition = newBlockPosition;

            return true;
        }
    }

    private void rotateBlock(Block block, boolean rotateClockwise) {
        if (rotateClockwise) {
            block.rotateClockwise();
        } else {
            block.rotateCounterClockwise();
        }
    }

    private void applyRotationToBoard(boolean blockIsRotatedClockwise, Block rotatedBlock, Point previousPosition, Point newPosition) {
        List<GridFieldWithPosition> fields = new ArrayList<GridFieldWithPosition>();
        //reverse rotation before removing old blocks
        rotateBlock(rotatedBlock, !blockIsRotatedClockwise);
        addEmptyFieldsToList(currentBlock, previousPosition, fields);
        rotateBlock(rotatedBlock, blockIsRotatedClockwise);
        addBlockFieldsToList(currentBlock, newPosition, fields);
        blockPosition = newPosition;
        board.setFields(fields);
    }

    @Override
    public boolean isBlockCollidingBelow() {
        Point pointBelow = (Point) blockPosition.clone();
        pointBelow.y++;
        return collisionChecker.collides(pointBelow, currentBlock);
    }

    @Override
    public void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener) {
        blockCollidedBelowListeners.add(newListener);
    }

    @Override
    public void addBlockSpawnedListener(BlockSpawnedListener newListener) {
        blockSpawnedListeners.add(newListener);
    }

    private void onCollision(List<Point> points) {
        for (BlockCollidedBelowListener listener : blockCollidedBelowListeners) {
            listener.BlockCollidedBelow(points);
        }
    }

    private void onBlockSpawned(Block block) {
        for (BlockSpawnedListener listener : blockSpawnedListeners) {
            listener.blockSpawned(block);
        }
    }
}
