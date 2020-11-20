package services;

import student.Student;

public class StudentService {
    public static Student[] createStudents(String[][] infoArray) {

        Student[] students;
        try {
            students = new Student[infoArray.length];
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception: " + e);
            return null;
        }

        for (int i = 0; i < infoArray.length; i++) {
            String fn = infoArray[i][0];
            String ln = infoArray[i][1];
            int y = Integer.parseInt(infoArray[i][2]);
            char g = infoArray[i][3].charAt(0);
            double m = Double.parseDouble(infoArray[i][4]);

            Student s = new Student(fn, ln, y, g, m);
            students[i] = s;
        }

        return students;
    }

    public static void printStudent(Student s) {
        System.out.printf("%s, %s, %d, %c, %.2f\n", s.getFirstName(), s.getLastName(),
                s.getYear(), s.getGender(), s.getMark());
    }

    // Task 1: Print full names of students.
    public static void printFullNames(Student[] students) {
        for (Student s : students) {
            System.out.printf("%s %s\n", s.getFirstName(), s.getLastName());
        }
    }

    // Task 2: Print all male students.
    public static void printMale(Student[] students) {
        for (Student s : students) {
            if (s.getGender() == 'm' || s.getGender() == 'M')
                printStudent(s);
        }
    }

    // Task 3: Print all female students who has mark greater then 50.4.
    public static void printFemaleMarkGreater50(Student[] students) {
        for (Student s : students) {
            if ((s.getGender() == 'f' || s.getGender() == 'F') && s.getMark() > 50.4)
                printStudent(s);
        }
    }

    // Task 4: Print student information having the minimal mark.
    public static void printMinMark(Student[] students) {
        Student minStudent = students[0];

        for (Student s: students) {
            if (s.getMark() <= minStudent.getMark())
                minStudent = s;
        }

        printStudent(minStudent);
    }

    // Task 5: Print biggest male student information.
    public static void printBiggestStudent(Student[] students) {
        Student biggestStudent = students[0];

        for (Student s : students) {
            if (s.getYear() <= biggestStudent.getYear())
                biggestStudent = s;
        }

        printStudent(biggestStudent);
    }

    // Task 6: Print students sorted by mark.
    public static void printSortedByMark(Student[] students) {
        Student[] copy = new Student[students.length];
        System.arraycopy(students, 0, copy, 0, students.length);

        boolean swapped = true;
        int count = 0;

        while (swapped) {
            swapped = false;

            for (int i = 0; i < copy.length - 1 - count; i++) {
                if (copy[i].getMark() > copy[i + 1].getMark()) {
                    Student temp = copy[i];
                    copy[i] = copy[i + 1];
                    copy[i + 1] = temp;

                    swapped = true;
                }
            }
            count++;
        }

        for (Student s : copy) {
            printStudent(s);
        }
    }

    // Task 7: Print female students sorted by year.
    public static void printFemaleSortedByYear(Student[] students) {
        Student[] copy = new Student[students.length];
        System.arraycopy(students, 0, copy, 0, students.length);

        boolean swapped = true;
        int count = 0;

        while (swapped) {
            swapped = false;

            for (int i = 0; i < copy.length - 1 - count; i++) {
                if (copy[i].getYear() > copy[i + 1].getYear()) {
                    Student temp = copy[i];
                    copy[i] = copy[i + 1];
                    copy[i + 1] = temp;

                    swapped = true;
                }
            }
            count++;
        }

        for (Student s : copy) {
            if (s.getGender() == 'f' || s.getGender() == 'F')
                printStudent(s);
        }
    }
}
