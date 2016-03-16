package com.coursera1.problemsets;

/**
 * Created by mamahendru on 2/26/16.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {2,4,7,1,9,5};
        array = sort(array);
        print(array);
    }

    private static int[] sort(int[] array) {
        if(array.length <= 1)
            return array;
        for(int i = 1; i < array.length; i++){
            int j = i;
            for(int k = i - 1; k >= 0; k--){
                if(array[j] < array[k]) {
                    swap(array, j, k);
                    j--;
                }
            }
        }
        return array;
    }

    private static void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }

    private static void print(int[] array) {
        System.out.print("[");
        for(int i = 0; i< array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
