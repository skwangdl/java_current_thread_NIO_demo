package com.kepler.thread;

public class Demo_PauseThread extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            i ++;
        }
    }
}
