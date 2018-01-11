package aiModule;

import aiModule.mocks.Controller;

import java.util.concurrent.TimeUnit;

public class InsertTest {

    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
            for (int i = 0; i < 10; i++) {
                controller.nextTetromino();
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
