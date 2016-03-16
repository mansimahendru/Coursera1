package com.coursera1.problemsets;

/**
 * Created by mamahendru on 3/2/16.
 */
public class DeadLock {
    static class Friend {
        private String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.println("bow: " + bower.getName());
            bower.bowBack(this);
            try {
                Thread.sleep(10000);
            }
            catch(InterruptedException ie){

            }
        }

        public synchronized void bowBack(Friend bower) {
            System.out.println("bowBack: " + bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend john = new Friend("John");
        final Friend mary = new Friend("Mary");

        new Thread(new Runnable() {
            @Override
            public void run() {
                john.bow(mary);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mary.bow(john);
            }
        }).start();

    }
}
