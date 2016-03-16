package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by mamahendru on 3/7/16.
 */
public class Dijkstra {
    static Node source = null;
    static Node current = null;
    static int currentdist = 0;
    static Map<Node, Node> path = new HashMap<Node, Node>();
    static Map<String, Integer> visited = new HashMap<String, Integer>();
    static Map<String, Integer> unvisited = new HashMap<String, Integer>();
    static Map<String, Node> list = new HashMap<String, Node>();

    public static void main(String[] args) {


        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        a.addNeighbor(e, 2);
        a.addNeighbor(b, 5);
        a.addNeighbor(d, 9);

        b.addNeighbor(c, 2);
        b.addNeighbor(a, 5);

        c.addNeighbor(b, 2);
        c.addNeighbor(d, 3);

        d.addNeighbor(c, 3);
        d.addNeighbor(a, 9);
        d.addNeighbor(f, 2);

        e.addNeighbor(a, 2);
        e.addNeighbor(f, 3);

        f.addNeighbor(d, 2);
        f.addNeighbor(e, 3);

        //list.put(a.getLabel(), a);
        //list.put(b.getLabel(), b);
        //list.put(c.getLabel(), c);
        //list.put(d.getLabel(), d);
        //list.put(e.getLabel(), e);
        //list.put(f.getLabel(), f);

        try {
            getNodeList();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        source = list.get("1");
        current = source;
        currentdist = 0;
        for(String label : list.keySet()){
            Node n = list.get(label);
            if(n.getLabel().equalsIgnoreCase(source.getLabel())){
                unvisited.put(n.getLabel(), 0);
            }
            else {
                unvisited.put(n.getLabel(), Integer.MAX_VALUE);
            }
        }

        while(unvisited.size() > 0){
            current = getSmallestNode();
            currentdist = unvisited.get(current.getLabel());
            if(!current.isExplored()) {
                for (Node n : current.getNeighbors().keySet()) {
                    if(!n.isExplored()) {
                        int olddist = unvisited.get(n.getLabel());
                        int newdist = current.getNeighbors().get(n) + currentdist;
                        if (newdist < olddist) {
                            unvisited.put(n.getLabel(), newdist);
                        }
                    }
                }
            }
            current.setExplored(true);
            unvisited.remove(current.getLabel());
            visited.put(current.getLabel(), currentdist);
        }

        for(String label : visited.keySet()){
            System.out.println(label + " : " + visited.get(label));
        }

        System.out.println("7" + " : " + visited.get("7"));
        System.out.println("37" + " : " + visited.get("37"));
        System.out.println("59" + " : " + visited.get("59"));
        System.out.println("82" + " : " + visited.get("82"));
        System.out.println("99" + " : " + visited.get("99"));
        System.out.println("115" + " : " + visited.get("115"));
        System.out.println("133" + " : " + visited.get("133"));
        System.out.println("165" + " : " + visited.get("165"));
        System.out.println("188" + " : " + visited.get("188"));
        System.out.println("197" + " : " + visited.get("197"));

    }

    private static void getNodeList() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("dijkstraData.txt"));
        String str;
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            Node n = list.get(result[0]);
            if(n == null){
                n = new Node(result[0]);
                list.put(n.getLabel(), n);
            }
            for(int i = 1; i < result.length; i++){
                String[] neighborslist = result[i].split(",");
                Node ng = list.get(neighborslist[0]);
                if(ng == null){
                    ng = new Node(neighborslist[0]);
                    list.put(ng.getLabel(), ng);
                }
                n.addNeighbor(ng, Integer.parseInt(neighborslist[1]));
            }
        }
        in.close();
    }

    private static Node getSmallestNode() {
        int min = Integer.MAX_VALUE;
        Node smallest = null;
        for(String label : unvisited.keySet()) {
            if(unvisited.get(label) <= min){
                min = unvisited.get(label);
                smallest = list.get(label);
            }
        }
        return smallest;
    }

    private static void printArray(String[] array) {
        System.out.print("{");
        for(String str : array){
            System.out.print(str + " ");
        }
        System.out.print("}");
        System.out.println();
    }

    private static class Node {
        String label;
        Map<Node, Integer> neighbors = new HashMap<Node, Integer>();
        boolean explored = false;

        public Node(String label) {
            this.label = label;
        }

        public void addNeighbor(Node n, int distance) {
            neighbors.put(n, distance);
        }

        public Map<Node, Integer> getNeighbors() {
            return this.neighbors;
        }

        public String getLabel() {
            return this.label;
        }

        public void setExplored(boolean b) {
            this.explored = b;
        }

        public boolean isExplored() {
            return this.explored;
        }

        public void print() {
            System.out.print(label);
            System.out.print(" - ");
            for(Node n : neighbors.keySet()){
                System.out.print(n.getLabel() + " : " + neighbors.get(n));
            }
            System.out.println();
        }
    }
}
