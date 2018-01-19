package GUI.Block;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TetrominoIndexes {

    public int[][][] takeIndexes(BlockType blockType) {
        switch (blockType) {
            case I:
                return createTetrominoI();
            case T:
                return createTetrominoT();
            case L:
                return createTetrominoL();
            case J:
                return createTetrominoJ();
            case S:
                return createTetrominoS();
            case Z:
                return createTetrominoZ();
            case O:
                return createTetrominoO();
            default:
                throw new NotImplementedException();
        }
    }

    private int[][][] createTetrominoI() {
        return new int[][][]{
                {
                        {0, 1},
                        {1, 1},
                        {2, 1},
                        {3, 1}
                },
                {
                        {2, 3},
                        {2, 2},
                        {2, 1},
                        {2, 0},
                },
                {
                        {3, 2},
                        {2, 2},
                        {1, 2},
                        {0, 2}

                },
                {
                        {2, 0},
                        {2, 1},
                        {2, 2},
                        {2, 3}
                }
        };
    }

    private int[][][] createTetrominoT() {
        return new int[][][]{
                {
                        {2, 0},
                        {1, 1},
                        {2, 1},
                        {3, 1}
                },
                {
                        {2, 1},
                        {3, 2},
                        {3, 1},
                        {3, 0},
                },
                {
                        {2, 2},
                        {3, 1},
                        {2, 1},
                        {1, 1}
                },
                {
                        {3, 1},
                        {2, 0},
                        {2, 1},
                        {2, 2},
                }
        };
    }

    private int[][][] createTetrominoL() {
        return new int[][][]{
                {
                        {1, 0},
                        {1, 1},
                        {2, 1},
                        {3, 1}
                },
                {
                        {2, 2},
                        {3, 2},
                        {3, 1},
                        {3, 0},
                },
                {
                        {3, 2},
                        {3, 1},
                        {2, 1},
                        {1, 1}
                },
                {
                        {3, 0},
                        {2, 0},
                        {2, 1},
                        {2, 2},
                }
        };
    }

    private int[][][] createTetrominoJ() {
        return new int[][][]{
                {
                        {1, 1},
                        {2, 1},
                        {3, 1},
                        {3, 0}
                },
                {
                        {3, 2},
                        {3, 1},
                        {3, 0},
                        {2, 0},
                },
                {
                        {3, 1},
                        {2, 1},
                        {1, 1},
                        {1, 2}
                },
                {
                        {2, 0},
                        {2, 1},
                        {2, 2},
                        {3, 2},
                }
        };
    }

    private int[][][] createTetrominoS() {
        return new int[][][]{
                {
                        {1, 1},
                        {2, 1},
                        {2, 2},
                        {3, 2}
                },
                {
                        {2, 2},
                        {2, 1},
                        {3, 1},
                        {3, 0},
                },
                {
                        {3, 1},
                        {2, 1},
                        {2, 0},
                        {1, 0}
                },
                {
                        {3, 0},
                        {3, 1},
                        {2, 1},
                        {2, 2},
                }
        };
    }

    private int[][][] createTetrominoZ() {
        return new int[][][]{
                {
                        {1, 2},
                        {2, 2},
                        {2, 1},
                        {3, 1}
                },
                {
                        {3, 2},
                        {3, 1},
                        {2, 1},
                        {2, 0},
                },
                {
                        {3, 0},
                        {2, 0},
                        {2, 1},
                        {1, 1}
                },
                {
                        {2, 0},
                        {2, 1},
                        {3, 1},
                        {3, 2},
                }
        };
    }

    private int[][][] createTetrominoO() {
        return new int[][][]{
                {
                        {2, 1},
                        {3, 1},
                        {3, 0},
                        {2, 0},

                },
                {
                        {3, 1},
                        {3, 0},
                        {2, 0},
                        {2, 1}

                },
                {
                        {3, 0},
                        {2, 0},
                        {2, 1},
                        {3, 1},
                },
                {
                        {2, 0},
                        {2, 1},
                        {3, 1},
                        {3, 0}
                }
        };
    }
}
