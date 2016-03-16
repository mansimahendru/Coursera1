package com.coursera1.problemsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamahendru on 3/3/16.
 */
public class DFSStack {
    public static void main(String[] args) {

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        a.addNeighbors(b);
        a.addNeighbors(c);

        b.addNeighbors(d);
        b.addNeighbors(e);
        b.addNeighbors(a);

        c.addNeighbors(e);
        c.addNeighbors(a);

        d.addNeighbors(f);
        d.addNeighbors(b);

        e.addNeighbors(f);
        e.addNeighbors(b);
        e.addNeighbors(c);

        f.addNeighbors(d);
        f.addNeighbors(e);

        bfs(a);

    }

    private static void bfs(Node node) {
        Queue<Node> queue = new Queue<Node>();
        node.setExplored(true);
        queue.push(node);
        while (!queue.empty()){
            Node n = queue.pop();
            for(Node nn : n.getNeighbors()){
                if(!nn.explored){
                    nn.setExplored(true);
                    queue.push(nn);
                    break;
                }
            }
        }

    }
    private static class Node {
        String name;
        List<Node> neighbors = new ArrayList<Node>();
        boolean explored = false;

        public Node(String name) {
            this.name = name;
            this.neighbors = neighbors;
        }

        public boolean isExplored() {
            return explored;
        }

        public List<Node> getNeighbors() {
            return this.neighbors;
        }

        public void addNeighbors(Node n) {
            neighbors.add(n);
        }

        public void setExplored(boolean b){
            System.out.println("Explored: " + this.name);
            this.explored = b;
        }

    }
    private static class Queue<T>{
        List<T> queue = new ArrayList<T>();

        public T pop() {
            T obj = queue.get(0);
            queue.remove(0);
            return obj;
        }

        public void push(T obj){
            queue.add(0, obj);
        }

        public boolean empty() {
            if(queue.size() > 0){
                return false;
            }
            return true;
        }

    }
}
