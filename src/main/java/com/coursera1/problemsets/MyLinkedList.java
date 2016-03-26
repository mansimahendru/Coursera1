package com.coursera1.problemsets;

/**
 * Created by mamahendru on 3/21/16.
 */
public class MyLinkedList<E> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(3));
        list.add(new Integer(4));
        list.add(new Integer(5));
        list.add(new Integer(6));
        list.add(new Integer(7));

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));

        System.out.println("---------");


        System.out.println(list.getMiddle());

        System.out.println("--------");

        LinkedList<Integer> reversedList = new LinkedList<Integer>();
        list.reverse(list.head, null);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
    }


    private static class LinkedList<E> {
        Node<E> head;
        Node<E> tail;

        public void add(E data) {
            Node<E> n = new Node<E>(data);
            if (head == null) {
                head = n;
            } else if (tail == null){
                head.next = n;
                tail = n;
            }
            else {
                tail.next = n;
                tail = n;
            }
        }

        public void add(int index, E data) {
            Node<E> n = new Node<E>(data);

        }

        public void delete(int index) {
            if (index > 0) {
                Node<E> prev = getNode(index - 1);
                Node<E> n = getNode(index);
                prev.next = n.next;
            } else {
                head = null;
            }
        }

        public E get(int index) {
            Node<E> n = getNode(index);
            if (n != null)
                return n.data;
            return null;
        }

        public Node<E> getNode(int index) {
            int i = 0;
            Node<E> n = head;
            while (i < index && n.next != null) {
                n = n.next;
                i++;
            }
            if (i < index)
                return null;
            return n;
        }

        public int find(E data, Node<E> n, int i) {
            if(n.data.equals(data))
                return i;
            i++;
            if(n.next != null) {
                return find(data, n.next, i);
            }
            return -1;
        }

        public E getMiddle() {
            Node<E> faster = head;
            Node<E> slower = head;

            while(faster != null && faster.next != null){
                slower = slower.next;
                faster = faster.next.next;
            }

            return slower.data;
        }

        public void reverse(LinkedList<E> newList, Node<E> n) {
            if(n.next != null){
                reverse(newList, n.next);
            }
            newList.add(n.data);
        }

        public void reverse(Node<E> current, Node<E> prev) {
            if(current.next == null){
                head = current;
                current.next = prev;
            } else {
                reverse(current.next, current);
            }
            current.next = prev;
        }



        private class Node<E> {
            E data;
            Node<E> next;

            public Node(E data) {
                this.data = data;
                this.next = null;
            }

            public void setNext(Node<E> node) {
                this.next = node;
            }
        }
    }
}
