package com.coursera1.problemsets;

public  class RandomizeSelection {

	public static void main(String[] args) {
		int[] array = {13,18,12,17,14,16,11,15};

		int val = select(8, array, 0, array.length);

		System.out.println(val);
	}

	public static int select(int index, int[] array, int start, int end) {

		if(array.length <= 1)
			return array[0];

		int pivot = array[start];
		int i = start + 1;
		for(int j = start + 1; j < end; j++){
			if(array[j] < pivot){
				swap(array, i, j);
				i++;
			}			
		}
		swap(array, start, i - 1);
		
		if(i == index)
			return array[i - 1];

		if(index < i){
			return select(index, array, start, i - 1);
		}
		else {
			return select(index, array, i , end);
		}
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static void print(int[] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.print("]");
		System.out.println();
	}
}