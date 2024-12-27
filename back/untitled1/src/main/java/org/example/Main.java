package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {
      Counter counter = new Counter();
      Runnable a = ()-> {
          for (int i = 0; i < 10; i++) {
              try {
                  counter.increment();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
      };
      Runnable b = ()-> {
          for (int i = 0; i < 10; i++) {
              try {
                  counter.decrement();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
      };

      Thread t1 = new Thread(a);
      Thread t2 = new Thread(b);
      t1.start();
      t2.start();

      t1.join();
      t2.join();
    }

}
