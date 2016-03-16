package com.coursera1.problemsets;

import java.io.*;

public class QuickSort {
	public static void main(String[] args) {
		//int[] array = {3,1,8,2,5,10,4,7,9,6};
		int[] array = null;
		try{
			array = getArray("numbers2.txt");
		}
		catch(Exception ex){
		}
		int[] count = {0};
		QuickSort qsort = new QuickSort();
		qsort.sort(array,0,array.length,count);
		print(array);
		System.out.println(count[0]);
	}
	
	public void sort(int[] array, int start, int end, int[] count) {
		if(end - start <= 1)
			return;

		swap(array,start,end - 1);
		
		count[0] = count[0] + ((end - start) - 1);	
		int i = start + 1;
		int pivot = array[start];
		for(int j = start + 1; j < end; j++){
			if(array[j] < pivot){
				swap(array, i, j);
				i++;
			}
		}
		swap(array, start, i - 1);
		
		sort(array, start, i - 1,count);
		sort(array, i , end,count);
		
	}

	public static void print(int[] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.print("]");
		System.out.println();
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static int[] getArray(String name) throws FileNotFoundException, IOException{
		int[] array = new int[10000];
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