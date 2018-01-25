package controller.mocks;


import controller.MoveController;

public class AIController {

    MoveController moveController;

//    public AIController(MoveController moveController) {
//        this.moveController = moveController;
//    }

    public AIController(MoveController moveController, TetrisBoard board, double difficultLevel) {
        this.moveController = moveController;
    }

//    public AIController(double difficultLevel, AIBoardMock mainAIBoardMock) {
//        this.difficultLevel = difficultLevel;
//        this.mainAIBoardMock = mainAIBoardMock;
//    }

}


