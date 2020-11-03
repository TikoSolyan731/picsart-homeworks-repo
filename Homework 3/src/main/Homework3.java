package main;

import algorithms.Algorithms;
import student.*;

public class Homework3 {
    public static void main(String[] args) {
        int[] numbers = new int[]{5, 1, 4, 2, 8, 4, 6, 2};
        System.out.println("Before Sorting: ");
        for (int x : numbers)
            System.out.print(x + " ");

        System.out.println();

        Algorithms.bubbleSort(numbers);

        System.out.println("After Sorting: ");
        for (int x : numbers)
            System.out.print(x + " ");

        System.out.println("\n");

        Student student1 = new Student();
        student1.setFirstName("Tigran");
        student1.setLastName("Solyan");
        student1.setAge(18);
        student1.setGender('M');
        student1.setCurrentYear(1);
        student1.setUniversity("AUA");
        student1.setDepartment("Computer Science");
        student1.setYearOfAdmission(2020);

        student1.introduce();

        Homework hw1 = new Homework();
        hw1.setKlass("Discrete Math");
        hw1.setExercises(new int[]{1, 2, 3});
        hw1.setContent("Solve the exercises and explain your steps.");
        hw1.setTeacher("Mr. Mikayelyan");
        hw1.setDeadline(5);

        Homework hw2 = new Homework();
        hw2.setKlass("Calculus");
        hw2.setExercises(new int[]{4, 8, 10, 13});
        hw2.setContent("Solve the exercises and prove the theorems again.");
        hw2.setTeacher("Mr. Keryan");
        hw2.setDeadline(4);

        student1.setHws(new Homework[]{hw1, hw2});

        System.out.println();

        for (Homework hw : student1.getHws()) {
            System.out.println(hw.getKlass());
            System.out.println(hw.getContent());

            for (int n : hw.getExercises())
                System.out.print(n + "  ");
            System.out.println("\n");
        }
    }
}
