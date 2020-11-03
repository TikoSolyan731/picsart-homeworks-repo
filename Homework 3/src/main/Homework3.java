package main;

import algorithms.Algorithms;

public class Homework3 {
    public static void main(String[] args) {

        int[] numbers = new int[]{5, 1, 4, 2, 8, 4, 6, 2};
        System.out.println("Before Sorting: ");
        for (int x : numbers)
            System.out.print(x + " ");

        System.out.println();
        System.out.println();

        Algorithms.bubbleSort(numbers);
        System.out.println("After Sorting: ");
        for (int x : numbers)
            System.out.print(x + " ");

    }
}
