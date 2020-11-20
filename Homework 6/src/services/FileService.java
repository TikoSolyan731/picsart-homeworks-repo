package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService {
    public static String[][] readLines(String path) {
        File f = null;
        Scanner s = null;

        try {
            f = new File(path);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e);
        }

        try {
            int lineCount = 0;
            while (s.hasNextLine()) {
                s.nextLine();
                lineCount++;
            } //Reading the count of lines = count of students.

            String[][] infoArray = new String[lineCount][];
            s = new Scanner(f); //Resetting the scanner to the beginning.

            for (int i = 0; s.hasNextLine(); i++) {
                String l = s.nextLine();
                infoArray[i] = l.split(",");
            }

            return infoArray;
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception: " + e);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e);
        }

        return null;
    }
}
