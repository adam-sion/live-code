package org.example;

public class Counter {

    private int count = 0;

    public int getCount() {
        return count;
    }

    public synchronized  void increment() throws InterruptedException {
        Thread.sleep(1000);
        count++;
        System.out.println(count);
    }

    public synchronized void decrement() throws InterruptedException {
        Thread.sleep(1000);
        count--;
        System.out.println(count);
    }

}
