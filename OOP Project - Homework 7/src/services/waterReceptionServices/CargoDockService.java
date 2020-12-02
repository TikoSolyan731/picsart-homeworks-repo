package services.waterReceptionServices;

import reception.waterReception.CargoDock;
import services.waterTransportServices.CargoShipService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Ship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Formatter;
import java.util.Scanner;

public class CargoDockService {
    private static final String FILE_FORMAT = "%s,%d,%d,%.1f\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printCargoDock(CargoDock dock) {
        System.out.printf("Name: %s, Docked Ships Count: %d, Max Docked Ships: %d, " +
                        "Current Cargo Weight: %.1f\nDocked Ships:\n", dock.getName(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentCargoWeight());
        for (Ship ship : dock.getDockedShips()) {
            if (ship != null)
                System.out.printf("\t--%s\n", ship.getName());
        }
    }

    public static CargoDock createCargoDock() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Dock Name: ");
        String name = sc.nextLine();
        System.out.print("Max Docked Ships: ");
        int count = sc.nextInt();
        CargoDock cd = new CargoDock(name, count);

        System.out.print("Current Cargo Weight: ");
        cd.setCurrentCargoWeight(sc.nextDouble());
        System.out.println("Done!");

        return cd;
    }

    public static void printBiggestCargoWeight(CargoDock[] docks) {
        CargoDock max = docks[0];

        for (CargoDock d : docks) {
            if (d.getCurrentCargoWeight() >= max.getCurrentCargoWeight())
                max = d;
        }

        printCargoDock(max);
    }

    public static void printDockedCargoShipBiggestMaxWeight(CargoDock dock) {
        Ship max = (dock.getDockedShips().length == 0) ? null : dock.getDockedShips()[0];
        CargoShip cargoMax = null;

        if (max == null) {
            System.out.println("No Ships in this dock.");
            return;
        }

        for (Ship ship : dock.getDockedShips())
            if (ship instanceof CargoShip)
                cargoMax = (CargoShip) ship;

        if (cargoMax == null) {
            System.out.println("No Cargo Ships in this dock.");
            return;
        }

        for (Ship ship : dock.getDockedShips()) {
            if (ship instanceof CargoShip)
                if (((CargoShip) ship).getMaxCargoWeight() >= cargoMax.getMaxCargoWeight())
                    cargoMax = (CargoShip) ship;
        }

        CargoShipService.printCargoShip(cargoMax);
    }

    public static void writeToFile(CargoDock dock, String path) {
        String info = FORMATTER.format(FILE_FORMAT, dock.getName(), dock.getDockedShipsCount(),
                dock.getMaxDockedShips(), dock.getCurrentCargoWeight()).toString();

        try {
            System.out.println("Writing " + dock.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
