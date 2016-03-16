package com.coursera1.problemsets;

/**
 * Created by mamahendru on 3/2/16.
 */
public class Drop {
    private String message;

    public synchronized String read() {
        while(this.message == null){
            try {
                wait();
            }
            catch(InterruptedException ie){

            }
        }
        String str = this.message;
        this.message = null;
        notifyAll();
        return str;
    }

    public synchronized void write(String message) {
        while(this.message != null){
            try{
                wait();
            }
            catch(InterruptedException ie){

            }
        }
        this.message = message;
        notifyAll();
    }
}
