package com.kepler.thread;

public class Demo_WaitThreadA extends Thread {
    private Object lock;

    public Demo_WaitThreadA(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "   end");
        }
    }
}