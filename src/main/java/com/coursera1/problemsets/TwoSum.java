package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mamahendru on 3/14/16.
 */
public class TwoSum {
    public static void main(String[] args){
        Map<Long, Long> numbers = null;

        try{
            numbers = getNodeList();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println("before for loop");
        int count = 0;
        for(long t = -10000; t <= 10000; t++){
            for(long x : numbers.keySet()){
                long diff = t - x;
                if(numbers.get(diff) != null && diff != x){
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);

    }

    private static Map<Long, Long> getNodeList() throws Exception{
        Map<Long, Long> nodeList = new HashMap<Long, Long>();
        BufferedReader in = new BufferedReader(new FileReader("algo1-programming_prob-2sum.txt"));
        String str;
        while((str = in.readLine()) != null){
            Long val = Long.parseLong(str);
            nodeList.put(val, val);
        }
        in.close();
        return nodeList;
    }
}
