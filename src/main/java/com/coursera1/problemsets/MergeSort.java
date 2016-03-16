package com.coursera1.problemsets;

import java.io.*;

public class MergeSort {
	public static void main(String[] args) {
		MergeSort mergesort = new MergeSort();
		int[] array = null;
		try{
			array = getArray("numbers_orig.txt");
		}
		catch(Exception ex){
		}
		long[] count = {0L};
		int[] sortedarray = mergesort.sort(array, count);
		//print(sortedarray);
		System.out.println("count: " + count[0]);
	}
	
	public int[] sort(int[] full, long[] count) {
		if(full.length <= 1)
			return full;
		int rightlength = Math.abs(full.length / 2);
		int leftlength = full.length - rightlength; 
		int[] part1 = new int[leftlength];
		int[] part2 = new int[rightlength];
	
		System.arraycopy(full, 0, part1, 0, leftlength);
		System.arraycopy(full, leftlength, part2, 0, rightlength);

		part1 = sort(part1, count);
		part2 = sort(part2, count);
		return merge(part1, part2, count);
	}

	public int[] merge(int[] left, int[] right, long[] count) {
		int n = left.length + right.length;
		int[]  merged = new int[n];
		int i = 0;
		int j = 0;
		for(int k = 0; k <= n - 1; k++){
			if(i >= left.length && j < right.length){
				merged[k] = right[j];
				j++;
			}
			else if(j >= right.length && i < left.length){
				merged[k] = left[i];
				i++;
			}
			else if(left[i] <= right[j]){
				merged[k] = left[i];
				i++;
			}
			else {
				merged[k] = right[j];
				j++;
				if(i < left.length) {
					count[0] = count[0] + (left.length - i);
					if(count[0] < 0){
					System.out.println(count[0] + " " + left.length + " " + i);
					}
				}
			}		
		}
		return merged;
	}

	public static void print(int[] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.print("]");
		System.out.println();
	}
	
	public static int[] getArray(String name) throws FileNotFoundException, IOException{
		int[] array = new int[100000];
		BufferedReader br = new BufferedReader(new FileReader(name));
		String line;
		int k = 0;
		while((line = br.readLine()) != null) {
			int i = Integer.parseInt(line);
			array[k] = i;
			k++;
		}
		br.close();
		return array;
	}
}