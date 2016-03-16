package com.coursera1.problemsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamahendru on 3/3/16.
 */
public class DFS {

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

        dfs(a);

    }

    private static void dfs(Node n) {
        if(n.isExplored())
            return;
        n.setExplored(true);
        for(Node nn : n.getNeighbors()){
            if(nn.hasNeighbors()) {
                dfs(nn);
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

        public boolean hasNeighbors() {
            return this.neighbors.size() > 0;
        }

        public void setExplored(boolean b){
            System.out.println("Explored: " + this.name);
            this.explored = b;
        }

    }
}
