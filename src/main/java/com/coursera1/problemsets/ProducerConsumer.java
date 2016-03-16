package com.coursera1.problemsets;

/**
 * Created by mamahendru on 3/2/16.
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Drop drop = new Drop();
        Producer producer = new Producer(drop);
        Consumer consumer = new Consumer(drop);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
