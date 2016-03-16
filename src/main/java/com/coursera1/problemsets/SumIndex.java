package com.coursera1.problemsets;

/**
 * Created by mamahendru on 2/24/16.
 */
public class SumIndex {
    public static void main(String[] args) throws Exception{
        int[] array = {2,4,4,5,4,1};

        System.out.print(middleIndex(array));
    }

    public static int middleIndex(int[] array) throws Exception{
        int leftSum = array[0];
        int rightSum = array[array.length - 1];
        int i = 1, j = array.length - 2, k = 0;
        while(k < array.length){
            if(leftSum < rightSum && i < array.length){
                leftSum = leftSum + array[i];
                i++;
            }
            else if(leftSum > rightSum && j < array.length){
                rightSum = rightSum + array[j];
                j--;
            }
            k++;
        }
        if(leftSum == rightSum)
            return i;
        throw new Exception("Wrong Array");
    }
}
