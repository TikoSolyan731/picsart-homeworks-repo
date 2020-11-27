package services.waterReceptionServices;

import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import transport.waterTransport.Ship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Formatter;

public class TouristDockService {
    private static final String FILE_FORMAT = "%s,%d,%d,%d,%d";
    private static final Formatter FORMATTER = new Formatter();

    public static void printTouristDock(TouristDock dock) {
        System.out.printf("Name: %s, Docked Ships Count: %d, Max Docked Ships: %d, " +
                        "Current People Count: %d, Max People Count: %d\nDocked Ships:\n", dock.getName(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentPeopleCount(), dock.getMaxPeopleCount());
        for (Ship ship : dock.getDockedShips()) {
            if (ship != null)
                System.out.printf("\t--%s\n", ship.getName());
        }
    }

    public static void printNameLeastPeopleCount(TouristDock[] docks) {
        TouristDock min = docks[0];

        for (TouristDock d : docks) {
            if (d.getCurrentPeopleCount() <= min.getCurrentPeopleCount())
                min = d;
        }

        System.out.println("Name: " + min.getName());
    }

    public static void printByMaxPeopleCountAscending(TouristDock[] docks) {
        TouristDock[] tempDocks = Arrays.copyOf(docks, docks.length);
        boolean isSorted = false;
        int count = 0;

        while (!isSorted) {
            isSorted = true;

            for (int i = 0; i < tempDocks.length - 1 - count; i++) {
                if (tempDocks[i].getMaxPeopleCount() >= tempDocks[i + 1].getMaxPeopleCount()) {
                    var temp = tempDocks[i];
                    tempDocks[i] = tempDocks[i + 1];
                    tempDocks[i + 1] = temp;

                    isSorted = false;
                }
            }
            count++;
        }

        for (TouristDock d : tempDocks) {
            printTouristDock(d);
        }
    }

    public static void writeToFile(TouristDock dock, String path) {
        String info = FORMATTER.format(FILE_FORMAT, dock.getName(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentPeopleCount(), dock.getMaxPeopleCount()).toString();

        try {
            System.out.println("Writing " + dock.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
