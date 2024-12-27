package org.example;

public class RunnableImp implements Runnable {

    @Override
    public void run() {
        for (char i = 'a'; i <= 'z'; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
