package main;

import services.FileService;
import services.StudentService;
import student.Student;

import java.util.Scanner;

public class Homework6 {
    public static void main(String[] args) {
        // Part 1. Main Homework.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path you want to read a file from.");
        String path = sc.nextLine();

        Student[] students = StudentService.createStudents(
                FileService.readLines(path));

        for (Student s : students) {
            StudentService.printStudent(s);
        }
        System.out.println();
        StudentService.printFullNames(students);

        System.out.println();
        StudentService.printMale(students);

        System.out.println();
        StudentService.printFemaleMarkGreater50(students);

        System.out.println();
        StudentService.printMinMark(students);

        System.out.println();
        StudentService.printBiggestStudent(students);

        System.out.println();
        StudentService.printSortedByMark(students);

        System.out.println();
        StudentService.printFemaleSortedByYear(students);

        // Part 2. Merge sort implementation.
        System.out.println();
        System.out.println("Merge Sort Implementation.");

        int[] arr = {-5, 8, -10, 7, 8, 3, 10, -78, 2};
        int[] sorted = mergeSort(arr);

        for (int x : sorted) {
            System.out.print(x + " ");
        }
    }

    // Hayk jan Es merge sorty lriv im dzevovem grel (voch mi texic chem nayel), chgitem inchqanova optimal, bayc ashxatuma.

    public static int[] mergeSort(int[] a) {
        int[] first = new int[(int) Math.ceil((double) a.length / 2)];
        int[] second = new int[a.length / 2];

        System.arraycopy(a, 0, first, 0, (int) Math.ceil( (double) a.length / 2));
        System.arraycopy(a, (int) Math.ceil((double) a.length / 2), second, 0, a.length / 2);

        if (a.length > 2) {
            first = mergeSort(first);
            second = mergeSort(second);
        }

        return merge(first, second);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length + arr2.length];

        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j])
                arr[k++] = arr1[i++];
            else
                arr[k++] = arr2[j++];
        }

        while (i < arr1.length) {
            arr[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            arr[k++] = arr2[j++];
        }

        return arr;
    }
}
