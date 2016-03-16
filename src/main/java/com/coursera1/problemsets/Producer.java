package com.coursera1.problemsets;

import java.util.Random;

/**
 * Created by mamahendru on 3/2/16.
 */
public class Producer implements Runnable {
    Drop drop = null;
    String[] messages = {"First Message","Second Message","Third Message","Fourth Message","Fifth Message","Last Message"};
    public Producer(Drop drop) {
        this.drop = drop;
    }
    public void run() {
        Random random = new Random();
        for(int i = 0; i < messages.length; i++){
            drop.write(messages[i]);
            System.out.println("write: " + messages[i]);
            try{
                Thread.sleep(random.nextInt(5000));
            }
            catch(InterruptedException ie){

            }
        }
    }
}
