package com.coursera1.problemsets;

/**
 * Created by mamahendru on 3/22/16.
 */
public class Queue {

    public static void main(String[] args) {

        InnerQueue<String> queue = new InnerQueue<String>();

        queue.push("first");
        queue.push("second");
        queue.push("third");
        queue.push("fourth");
        queue.push("fifth");
        queue.push("sixth");

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

    }

    private static class InnerQueue<E>{
        Node<E> head;
        Node<E> tail;

        public void push(E data){
            Node<E> n = new Node<E>(data);
            if(head == null){
                head = n;
                head.next = null;
            }
            else if(tail == null){
                tail = n;
                head.next = tail;
            }
            else {
                tail.next = n;
                tail = n;
            }
        }

        public E pop() {
            Node<E> n = head;
            head = head.next;
            return n.data;
        }

        public E peek() {
            return head.data;
        }
    }

    private static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data){
            this.data = data;
        }
    }
}
