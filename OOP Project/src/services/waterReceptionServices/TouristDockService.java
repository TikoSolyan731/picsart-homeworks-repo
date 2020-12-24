package services.waterReceptionServices;

import reception.waterReception.TouristDock;
import transport.waterTransport.Ship;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class TouristDockService {
    private static final String FILE_FORMAT = "%s,%d,%d,%d,%d\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printTouristDock(TouristDock dock) {
        System.out.printf("Name: %s, Docked Ships Count: %d, Max Docked Ships: %d, " +
                        "Current People Count: %d, Max People Count: %d\nDocked Ships:\n", dock.getPlacement(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentPeopleCount(), dock.getMaxPeopleCount());
        for (Ship ship : dock.getDockedShips()) {
            if (ship != null)
                System.out.printf("\t--%s\n", ship.getName());
        }
    }

    public static void printNameLeastPeopleCount(List<TouristDock> docks) {
        TouristDock min = Collections.min(docks, new Comparator<TouristDock>() {
            @Override
            public int compare(TouristDock o1, TouristDock o2) {
                return o1.getCurrentPeopleCount() - o2.getCurrentPeopleCount();
            }
        });
        System.out.println("Name: " + min.getPlacement());
    }

    public static void printByMaxPeopleCountAscending(List<TouristDock> docks) {
        List<TouristDock> docksCopy = new ArrayList<>(docks.size());
        Collections.copy(docksCopy, docks);
        docksCopy.sort(new Comparator<TouristDock>() {
            @Override
            public int compare(TouristDock o1, TouristDock o2) {
                return o1.getMaxPeopleCount() - o2.getMaxPeopleCount();
            }
        });

        for (TouristDock d : docksCopy) {
            printTouristDock(d);
        }
    }

    public static void writeToFile(TouristDock dock, String path) {
        String info = FORMATTER.format(FILE_FORMAT, dock.getPlacement(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentPeopleCount(), dock.getMaxPeopleCount()).toString();

        File file = new File(String.valueOf(Paths.get(path)));

        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(path));
            } catch (IOException e) {
                System.out.println("Could Not Create A File");
                return;
            }
        }

        try {
            System.out.println("Writing The Tourist Dock At " + dock.getPlacement() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
