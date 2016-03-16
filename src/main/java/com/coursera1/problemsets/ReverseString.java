package com.coursera1.problemsets;

/**
 * Created by mamahendru on 2/25/16.
 */
public class ReverseString {
    public static void main(String[] args) {
        String str = "trial";
        System.out.println(str);
        char[] reversed = new char[str.length()];
        reverse(str.toCharArray(), reversed, 0, str.length());
        print(reversed);

    }

    private static void reverse(char[] original, char[] reversed, int index, int reverseindex) {
        if(index < original.length) {
            index++;
            reverseindex--;
            reverse(original, reversed, index, reverseindex);
        }
        reversed[reverseindex] = original[index - 1];
        return;
    }

    private static void print(char[] array) {
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + "");
        System.out.println();
    }
}
