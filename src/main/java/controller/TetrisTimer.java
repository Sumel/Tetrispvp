package controller;

import java.util.Timer;
import java.util.TimerTask;

public class TetrisTimer {

    int delay;
    int counter = 0;

    TetrisTimer(int delay){
        this.delay = delay;
    }

    void reset(){
        counter = 0;
    }

    void init(){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(counter != 0) {
                    System.out.println("Tick");
                }
                counter++;
            }
        };

        Timer timer = new Timer("MyTimer");

        timer.scheduleAtFixedRate(timerTask, 30, delay);

    }
}
