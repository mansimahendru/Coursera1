package com.coursera1.problemsets;

import java.util.*;
import java.io.*;

/**
 * Created by mamahendru on 2/29/16.
 */
public class MinCut {
    private static String absPath;
    public static void main(String[] args) throws Exception{
        absPath = new File("").getAbsolutePath();
        List<Node> nodeList = getNodeList();

        int least = 0;

        for(int i = 0; i < 40000; i++) {
            List<Node> cloneList = cloneNodeList(nodeList);
            int mincut = merge(cloneList);
            if(least == 0)
                least = mincut;
            else if(mincut < least)
                least = mincut;
            System.out.println(i + " : " + mincut);
        }

        System.out.println(least);



    }

    private static List<Node> cloneNodeList(List<Node> origList) {
        List<Node> cloneList = new ArrayList<Node>();
        for(Node n: origList){
            cloneList.add(n.clone());
        }
        return cloneList;
    }

    private static List<Node> getNodeList() throws Exception{
        List<Node> nodeList = new ArrayList<Node>();
        BufferedReader in = new BufferedReader(new FileReader(absPath + "/src/main/resources/problem3.txt"));
        String str;
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            Node n = new Node();
            for (int x=0; x<result.length; x++) {
                if(x == 0){
                    n.setVal(Integer.parseInt(result[x]));
                }
                else {
                    n.addAdjacentNodes(Integer.parseInt(result[x]));
                }
            }
            nodeList.add(n);
        }
        in.close();
        return nodeList;
    }

    private static int merge(List<Node> nodeList) {
        int count = nodeList.size();
        while(count > 2) {
            int absorbingIndex = getRandomNumberInRange(0, nodeList.size() - 1);
            Node absorbingNode = nodeList.get(absorbingIndex);
            Node mergedNode = getMergedNode(absorbingNode, nodeList);
            absorbingNode.addAdjacentNodes(mergedNode.getVal());
            for (int i : mergedNode.getAdjacentNodes()) {
                absorbingNode.addAdjacentNodes(i);
            }
            replace(mergedNode, absorbingNode, nodeList);

            removeSelfLoop(absorbingNode);

            removeMergingNode(mergedNode, nodeList);

            count--;
        }

        return nodeList.get(0).getAdjacentNodes().size();
    }

    private static void removeMergingNode (Node n, List<Node> nodeList) {
        Iterator<Node> iterator = nodeList.iterator();
        while(iterator.hasNext()){
            Node temp = iterator.next();
            if(n.getVal() == temp.getVal())
                iterator.remove();
        }
    }

    private static void removeSelfLoop(Node n) {
        n.removeAdjacentNodes(n.getVal());
    }

    private static void replace(Node n, Node absorbingNode, List<Node> nodeList) {
        List<Integer> replaceList = n.getAdjacentNodes();
        for(int i : replaceList){
            Node an = getNode(i, nodeList);
            an.replaceAdjacentNodes(n.getVal(), absorbingNode.getVal());
        }
    }

    private static Node getMergedNode(Node node, List<Node> nodeList) {
        List<Integer> list = node.adjacentNodes;
        int mergedIndex = getRandomNumberInRange(0, list.size() - 1);
        int mergedVal = list.get(mergedIndex);
        return getNode(mergedVal, nodeList);
    }

    private static Node getNode(int val, List<Node> nodeList) {
        for(Node n : nodeList){
            if(n.getVal() == val)
                return n;
        }
        return null;
    }

    private static int getRandomNumberInRange(int start, int end) {
        Random random = new Random();
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)end - (long)start + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + start);

        return randomNumber;
    }

    private static class Node {
        private int val;
        private List<Integer> adjacentNodes = new ArrayList<Integer>();

        public Node() {

        }

        public Node(int val, int[] nodes) {
            this.val = val;
            for(int i = 0; i < nodes.length; i++){
                adjacentNodes.add(nodes[i]);
            }
        }

        public void setVal(int i) {
            this.val = i;
        }

        public int getVal(){
            return this.val;
        }

        public List<Integer> getAdjacentNodes() {
            return this.adjacentNodes;
        }

        public void addAdjacentNodes(int i) {
            this.adjacentNodes.add(i);
        }

        public void replaceAdjacentNodes(int i, int j) {
            for(int k = 0; k < this.adjacentNodes.size(); k++){
                if(this.adjacentNodes.get(k) == i)
                    this.adjacentNodes.set(k, j);
            }
        }

        public void removeAdjacentNodes(int i) {
            Iterator<Integer> iterator = this.adjacentNodes.iterator();
            while(iterator.hasNext()) {
                int k = iterator.next();
                if(k == i)
                    iterator.remove();
            }
        }

        public Node clone() {
            int[] arrayNodes = new int[this.adjacentNodes.size()];
            for(int i = 0; i < this.adjacentNodes.size(); i++){
                arrayNodes[i] = this.adjacentNodes.get(i);
            }
            Node n = new Node(this.val, arrayNodes);

            return n;
        }

        public void print() {
            System.out.print(this.val);
            System.out.print("-");
            for(int i : adjacentNodes){
                System.out.print(i);
                System.out.print("-");
            }
            System.out.println();
        }
    }
}

