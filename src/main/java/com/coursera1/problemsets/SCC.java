package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by mamahendru on 3/4/16.
 */
public class SCC {
    private static Stack<Node> stack = new Stack<Node>();

    public static void main(String[] args) {
        List<Node> list = null;
        try {
            list = getNodeList();
        }
        catch(Exception ex){

        }

        for(Node node : list) {
            if(!node.isExplored())
                dfs(node);
        }

        Map<String, Node> map = null;

        try {
            map = getReverseNodeMap();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        List<Node> scc = new ArrayList<Node>();
        List<Integer> count = new ArrayList<Integer>();

        for(int ii = stack.size() - 1; ii >= 0; ii--) {
            Node node = map.get(stack.get(ii).name);
            if(!node.isExplored()) {
                dfsforreverse(node, scc);
            }
            if(scc.size() > 1){
                count.add(scc.size());
            }
            scc.clear();
        }
    }

    private static List<Node> getNodeList() throws Exception{
        Map<String, Node> nodeMap = new HashMap<String, Node>();
        List<Node> list = new ArrayList<Node>();
        BufferedReader in = new BufferedReader(new FileReader("scc.txt"));
        String str;
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            String name1 = result[0].trim();
            String name2 = result[1].trim();
            Node n1 = nodeMap.get(name1);
            if(n1 == null){
                n1 = new Node(name1);
                nodeMap.put(name1, n1);
            }
            Node n2 = nodeMap.get(name2);
            if(n2 == null){
                n2 = new Node(name2);
                nodeMap.put(name2, n2);
            }
            n1.addNeighbors(n2);
        }
        in.close();
        for(String name : nodeMap.keySet()){
            Node n = nodeMap.get(name);
            list.add(n);
        }
        return list;
    }

    private static Map<String, Node> getReverseNodeMap() throws Exception{
        Map<String, Node> nodeMap = new HashMap<String, Node>();
        List<Node> list = new ArrayList<Node>();
        BufferedReader in = new BufferedReader(new FileReader("scc.txt"));
        String str;
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            String name1 = result[1].trim();
            String name2 = result[0].trim();
            Node n1 = nodeMap.get(name1);
            if(n1 == null){
                n1 = new Node(name1);
                nodeMap.put(name1, n1);
            }
            Node n2 = nodeMap.get(name2);
            if(n2 == null){
                n2 = new Node(name2);
                nodeMap.put(name2, n2);
            }
            n1.addNeighbors(n2);
        }
        in.close();

        return nodeMap;
    }

    public static void dfsforreverse(Node node, List<Node> scc) {
        if(node.isExplored())
            return;
        node.setExplored(true);
        for(Node n : node.getNeighbors()){
            if(!n.isExplored())
                dfsforreverse(n, scc);
        }
        scc.add(node);
    }

    public static void dfs(Node node) {
        node.setExplored(true);

        for (Node n : node.getNeighbors()) {
            if (n.isExplored())
                continue;
            dfs(n);
        }


        stack.push(node);

    }

    public static Map<String, Node> reverse(List<Node> list) {
        List<Node> reverseList = new ArrayList<Node>();
        Map<String, Node> map = new HashMap<String, Node>();
        for(Node n: list){
            Node nn = n.clone();
            reverseList.add(nn);
            map.put(nn.name, nn);
        }
        System.out.println("1");
        System.out.println(reverseList.size());
        for(Node node:reverseList){
            for(Node n : list){
                if(n.isNeighbor(node))
                    node.addNeighbors(map.get(n.name));
            }
        }
        System.out.println("2");
        return map;
    }

    private static class Node {
        String name;
        List<Node> neighbors = new ArrayList<Node>();
        boolean explored = false;

        public Node(String name) {
            this.name = name;
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

        public void setExplored(boolean b) {
            this.explored = b;
        }

        public Node clone() {
            Node n = new Node(new String(this.name));
            return n;
        }

        public boolean equals(Node n) {
            if(this.name.equals(n.name))
                return true;
            return false;
        }

        public boolean isNeighbor(Node n) {
            for(Node node:this.neighbors){
                if(node.equals(n))
                    return true;
            }
            return false;
        }

        public void print() {
            System.out.print(this.name + " - ");
            for(Node n : this.neighbors){
                System.out.print(" " + n.name);
            }
            System.out.println();
        }

    }
}
