package com.coursera1.problemsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamahendru on 3/14/16.
 */
public class Median {
    static Heap minHeap = new Heap();
    static Heap maxHeap = new Heap(false);
    static int size = 0;

    public static void main(String[] args){
        int median = 0;
        long sum = 0;

        /*
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(6);
        minHeap.insert(9);
        minHeap.insert(4);
        minHeap.insert(5);
        minHeap.insert(8);

        maxHeap.insert(3);
        maxHeap.insert(1);
        maxHeap.insert(6);
        maxHeap.insert(9);
        maxHeap.insert(4);
        maxHeap.insert(5);
        maxHeap.insert(8);

        minHeap.print();
        maxHeap.print();*/


        try {
            BufferedReader in = new BufferedReader(new FileReader("Median.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                int number = Integer.parseInt(str.trim());
                median = findMedian(number);
                sum = sum + median;

            }
            in.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println(sum);
        minHeap.print();
        System.out.println(minHeap.getSize());
        maxHeap.print();
        System.out.println(maxHeap.getSize());
        System.out.println(sum%10000);
    }

    private static int findMedian(int input){
        if (size == 0){
            maxHeap.insert(input);
        } else if(size % 2 == 0){
            if (input > minHeap.get()){
                maxHeap.insert(minHeap.extract());
                minHeap.insert(input);
            } else {
                maxHeap.insert(input);
            }

        } else {
            if (input < maxHeap.get()){
                minHeap.insert(maxHeap.extract());
                maxHeap.insert(input);
            } else {
                minHeap.insert(input);
            }
        }
        size++;
        return maxHeap.get();
    }

    private static class Heap {
        boolean min = true;
        List<Integer> list = new ArrayList<Integer>();

        public Heap() {

        }

        public Heap(boolean b){
            this.min = b;
        }

        public int get() {
            if(list.size() <= 0)
                return 0;
            return list.get(0);
        }

        public void insert(int e){
            list.add(e);
            int child = list.size() - 1;
            int parent = (int)((child - 1) / 2);
            if(min)
                bubbleUp(child, parent);
            else
                bubbleDown(child, parent);
        }

        public int extract() {
            int finalNo = list.get(0);
            int no = list.get(list.size() - 1);
            list.set(0, no);
            list.remove(list.size() - 1);
            if(min)
                bubbleDownExtract(0);
            else
                bubbleUpExtract(0);
            return finalNo;
        }

        public void print() {
            System.out.print("[");
            for(int e : list){
                System.out.print(e + " ");
            }
            System.out.print("]");
            System.out.println();
        }

        public void setMin(boolean m){
            this.min = m;
        }

        public int getSize() {
            return this.list.size();
        }

        private void bubbleDownExtract(int parent) {
            if (list.size() <= 0)
                return;

            int child1 = 2*parent + 1;
            int child2 = child1 + 1;

            int childindex = -1;

            if(child1 <= list.size() - 1){
                int child1val = list.get(child1);
                int child2val = 0;
                if(child2 <= list.size() - 1){
                    child2val = list.get(child2);
                }

                if(child1val < child2val || child2val == 0){
                    childindex = child1;
                }
                else if(child2val < child1val){
                    childindex = child2;
                }

            }

            if(childindex != -1 && list.get(childindex) < list.get(parent)){
                swap(childindex, parent);
                bubbleDownExtract(childindex);
            }

            return;
        }

        private void bubbleUpExtract(int parent) {
            if (list.size() <= 0)
                return;

            int child1 = 2*parent + 1;
            int child2 = child1 + 1;

            int childindex = -1;

            if(child1 <= list.size() - 1){
                int child1val = list.get(child1);
                int child2val = 0;
                if(child2 <= list.size() - 1){
                    child2val = list.get(child2);
                }

                if(child1val >= child2val || child2val == 0){
                    childindex = child1;
                }
                else if(child2val > child1val){
                    childindex = child2;
                }

            }

            if(childindex != -1 && list.get(childindex) > list.get(parent)){
                swap(childindex, parent);
                bubbleUpExtract(childindex);
            }

            return;
        }

        private void bubbleDown(int child, int parent) {
            if (list.size() <= 0)
                return;
            if (list.get(child) > list.get(parent)) {
                swap(child, parent);
                child = parent;
                parent = (int) ((child - 1) / 2);
                bubbleDown(child, parent);
            }
            return;
        }


        private void bubbleUp(int child, int parent) {
            if(list.size() <= 0)
                return;
            if(list.get(child) < list.get(parent)){
                swap(child, parent);
                child = parent;
                parent = (int)((child-1)/2);
                bubbleUp(child, parent);
            }
            return;
        }

        private void swap(int i, int j) {
            int e1 = list.get(i);
            int e2 = list.get(j);
            list.set(i, e2);
            list.set(j, e1);
        }
    }
}
