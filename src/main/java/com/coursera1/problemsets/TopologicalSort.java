package com.coursera1.problemsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamahendru on 3/4/16.
 */
public class TopologicalSort {
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

        c.addNeighbors(e);

        d.addNeighbors(f);

        e.addNeighbors(f);

        int[] position = {6};

        dfs(a, position);

    }

    private static void dfs(Node n, int[] position) {
        if(n.isExplored()) {
            return;
        }
        for(Node nn : n.getNeighbors()){
            dfs(nn, position);
        }
        n.setExplored(true);
        n.setPosition(position[0]);
        position[0]--;

    }

    private static class Node {
        String name;
        int position = 0;
        List<Node> neighbors = new ArrayList<Node>();
        boolean explored = false;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean isExplored() {
            return explored;
        }

        public List<Node> getNeighbors() {
            return this.neighbors;
        }

        public void addNeighbors(Node n) {
            this.neighbors.add(n);
        }

        public boolean hasNeighbors() {
            return this.neighbors.size() > 0;
        }

        public void setExplored(boolean b){
            System.out.println("Explored: " + this.name);
            this.explored = b;
        }

        public void setPosition(int i) {
            this.position = i;
            System.out.println("Position: " + this.position);
        }

    }
}
