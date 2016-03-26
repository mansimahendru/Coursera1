package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.File;


/**
 * Created by mamahendru on 3/22/16.
 */
public class JobsScheduler {
    private static String absPath;

    public static void main(String[] args) {
        absPath = new File("").getAbsolutePath();
        List<Node> list = null;
        //Node n1 = new Node(1,2);
        //Node n2 = new Node(2,2);
        //Node n3 = new Node(3,5);
        //Node n4 = new Node(5,8);
        //Node n5 = new Node(6,9);
        //list.add(n1);
        //list.add(n2);
        //list.add(n3);
        //list.add(n4);
        //list.add(n5);

        try {
            list = getNodeList();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        list = sort(list);

        Collections.reverse(list);

        for(Node n : list){
            System.out.println(n.weight + " " + n.length + " " + n.ratio);
        }

        long l = sumOfWeightedCompletionTimes(list);

        System.out.println(l);
    }

    private static long sumOfWeightedCompletionTimes(List<Node> data) {
        long completionTime = 0;
        long score = 0;
        for (Node job : data) {
            completionTime = completionTime + job.length;
            score = score + job.weight * completionTime;
        }
        return score;
    }

    private static List<Node> getNodeList() throws Exception{
        List<Node> nodeList = new ArrayList<Node>();
        BufferedReader in = new BufferedReader(new FileReader(absPath + "/src/main/resources/jobs.txt"));
        String str;
        while((str = in.readLine()) != null){
            String[] result = str.split("\\s");
            Node n = new Node(Integer.parseInt(result[0]), Integer.parseInt(result[1]));
            nodeList.add(n);
        }
        in.close();
        return nodeList;
    }

    private static List<Node> sort(List<Node> list) {
        if(list.size() <= 1)
            return list;
        int med = Math.abs(list.size() / 2);
        List<Node> left = list.subList(0, med);
        List<Node> right = list.subList(med, list.size());

        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private static List<Node> merge(List<Node> left, List<Node> right) {
        List<Node> finalList = new ArrayList<Node>();
        int length = left.size() + right.size();
        int i = 0;
        int j = 0;
        for(int k = 0; k < length; k++){
            if(i >= left.size() ){
                finalList.add(right.get(j));
                j++;
            }
            else if(j >= right.size()) {
                finalList.add(left.get(i));
                i++;
            }
            else if(left.get(i).diff < right.get(j).diff){
                finalList.add(left.get(i));
                i++;
            }
            else if(left.get(i).diff == right.get(j).diff){
                if(left.get(i).weight < right.get(j).weight){
                    finalList.add(left.get(i));
                    i++;
                }
                else{
                    finalList.add(right.get(j));
                    j++;
                }
            }
            else{
                finalList.add(right.get(j));
                j++;
            }
        }
        return finalList;
    }

    private static List<Node> mergeRatio(List<Node> left, List<Node> right) {
        List<Node> finalList = new ArrayList<Node>();
        int length = left.size() + right.size();
        int i = 0;
        int j = 0;
        for(int k = 0; k < length; k++){
            if(i >= left.size() ){
                finalList.add(right.get(j));
                j++;
            }
            else if(j >= right.size()) {
                finalList.add(left.get(i));
                i++;
            }
            else if(left.get(i).ratio < right.get(j).ratio){
                finalList.add(left.get(i));
                i++;
            }
            else if(left.get(i).ratio == right.get(j).ratio){
                if(left.get(i).weight >= right.get(j).weight){
                    finalList.add(left.get(i));
                    i++;
                }
                else{
                    finalList.add(right.get(j));
                    j++;
                }
            }
            else{
                finalList.add(right.get(j));
                j++;
            }
        }
        return finalList;
    }

    private static class Node {
        int weight;
        int length;
        double diff;
        double ratio;

        public Node(int weight, int length) {
            this.weight = weight;
            this.length = length;
            diff = this.weight - this.length;
            ratio = new Double(this.weight) / new Double(this.length);
        }
    }
}
