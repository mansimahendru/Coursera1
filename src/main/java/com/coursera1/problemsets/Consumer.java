package com.coursera1.problemsets;

import java.util.Random;

/**
 * Created by mamahendru on 3/2/16.
 */
public class Consumer implements Runnable {
    private Drop drop;
    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for(String message = drop.read(); !message.equals("Last Message"); message = drop.read()){
            System.out.println("read: " + message);
            try{
                Thread.sleep(random.nextInt(5000));
            }
            catch(InterruptedException ie) {

            }
        }
    }
}
