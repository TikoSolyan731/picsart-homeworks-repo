package services.waterReceptionServices;

import reception.waterReception.CargoDock;
import services.waterTransportServices.CargoShipService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Ship;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class CargoDockService {
    private static final String FILE_FORMAT = "%s,%d,%d,%.1f\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printCargoDock(CargoDock dock) {
        System.out.printf("Name: %s, Docked Ships Count: %d, Max Docked Ships: %d, " +
                        "Current Cargo Weight: %.1f\nDocked Ships:\n", dock.getPlacement(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentCargoWeight());
        for (Ship ship : dock.getDockedShips()) {
            if (ship != null)
                System.out.printf("\t--%s\n", ship.getName());
        }
    }

    public static void printBiggestCargoWeight(List<CargoDock> docks) {
        CargoDock max = Collections.max(docks, new Comparator<CargoDock>() {
            @Override
            public int compare(CargoDock o1, CargoDock o2) {
                return (int) (o1.getCurrentCargoWeight() - o2.getCurrentCargoWeight());
            }
        });

        printCargoDock(max);
    }

    public static void printDockedCargoShipBiggestMaxWeight(CargoDock dock) {
        List<Ship> dockedShips = new ArrayList<>(Arrays.asList(dock.getDockedShips()));
        List<CargoShip> dockedCargoShips = new ArrayList<>();

        int count = 0;
        for (Ship ship : dockedShips) {
            if (ship == null)
                count++;
            else if (ship instanceof CargoShip)
                dockedCargoShips.add((CargoShip) ship);
        }

        if (count == dockedShips.size()) {
            System.out.println("No Ships in this dock.");
            return;
        }

        CargoShip max = Collections.max(dockedCargoShips, new Comparator<CargoShip>() {
            @Override
            public int compare(CargoShip o1, CargoShip o2) {
                return (int) (o1.getMaxCargoWeight() - o2.getMaxCargoWeight());
            }
        });

        CargoShipService.printCargoShip(max);
    }

    public static void writeToFile(CargoDock dock, String path) {
        String info = FORMATTER.format(FILE_FORMAT, dock.getPlacement(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentCargoWeight()).toString();

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
            System.out.println("Writing The Cargo Dock At " + dock.getPlacement() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
