package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

/**
 * Created by mamahendru on 3/23/16.
 */
public class PrimMST {
    private static String absPath;
    private static Map<Node, Integer> heap = new HashMap<Node, Integer>();
    private static Map<Node, Node> visited = new HashMap<Node, Node>();
    private static List<Node> nodes = new ArrayList<Node>();

    public static void main(String[] args) {
        absPath = new File("").getAbsolutePath();
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");

        n1.addNode(n2, 1);
        n1.addNode(n3, 2);

        n2.addNode(n1, 1);
        n2.addNode(n4, 3);

        n3.addNode(n1, 2);
        n3.addNode(n4, 5);

        n4.addNode(n2, 3);
        n4.addNode(n3, 5);

        //nodes.add(n1);
        //nodes.add(n2);
        //nodes.add(n3);
        //nodes.add(n4);

        try {
            getNodeList();
        }
        catch(Exception ex){
            ex.printStackTrace();

        }

        computeMST(n1);

        int sum = 0;

        for(Node key : visited.keySet()){
            Node value = visited.get(key);
            if(value != null) {
                System.out.println(key.label + " " + value.label);
                System.out.println(key.adjoiningNodes.get(value));
                sum = sum + key.adjoiningNodes.get(value);
                key.print();
            }
        }

        System.out.println(sum);

    }

    private static void getNodeList() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(absPath + "/src/main/resources/edges.txt"));
        String str;
        Map<String, Node> map = new HashMap<String, Node>();
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            Node n1 = map.get(result[0]);
            Node n2 = map.get(result[1]);
            int weight = Integer.parseInt(result[2]);
            if(n1 == null){
                n1 = new Node(result[0]);
                map.put(result[0], n1);
                nodes.add(n1);
            }
            if(n2 == null){
                n2 = new Node(result[1]);
                map.put(result[1], n2);
                nodes.add(n2);
            }
            n1.addNode(n2, weight);
            n2.addNode(n1, weight);
        }
        in.close();
    }

    private static void computeMST(Node n1) {
        Node current = null;
        for(Node n: nodes){
            heap.put(n, Integer.MAX_VALUE);
        }
        heap.put(n1, Integer.MIN_VALUE);
        while(heap.size() > 0){
            current = getSmallestNode();
            for(Node n : current.adjoiningNodes.keySet()){
                if(heap.get(n) != null) {
                    if (current.adjoiningNodes.get(n) <= heap.get(n)) {
                        heap.put(n, current.adjoiningNodes.get(n));
                        visited.put(n, current);
                    }
                }
            }
            heap.remove(current);
        }

    }

    private static Node getSmallestNode() {
        int min = Integer.MAX_VALUE;
        Node smallest = null;
        for(Node n : heap.keySet()) {
            if(heap.get(n) <= min){
                min = heap.get(n);
                smallest = n;
            }
        }
        return smallest;
    }

    private static class Node {
        Map<Node, Integer> adjoiningNodes = new HashMap<Node, Integer>();
        String label;

        public Node(String label){
            this.label = label;
        }

        public void addNode(Node n, Integer wt) {
            adjoiningNodes.put(n, wt);
        }

        public void print(){
            System.out.println("{");
            System.out.println(label);
            for(Node n : adjoiningNodes.keySet()){
                System.out.print(" " + n.label + " " + adjoiningNodes.get(n));
            }
            System.out.println();
            System.out.println("}");
        }
    }
}
