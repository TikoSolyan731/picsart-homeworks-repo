package services.waterTransportServices;

import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import services.waterReceptionServices.CargoDockService;
import services.waterReceptionServices.TouristDockService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Formatter;
import java.util.Scanner;

public class CargoShipService {
    private static final String FILE_FORMAT = "%s,%s,%s,%d,%.1f,%.1f,%.1f\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printCargoShip(CargoShip ship) {
        System.out.printf("Name: %s, Current Pos: %s, Max Speed: %.1f,\n" +
                        "Cargo: %.1f, Max Cargo: %.1f\n", ship.getName(), ship.getCurrentPos().getName(),
                ship.getMaxSpeed(), ship.getCargoWeight(), ship.getMaxCargoWeight());
    }

    public static CargoShip createCargoShip(boolean createNewDock, CargoDock cargoDock) {
        Scanner sc = new Scanner(System.in);

        CargoDock cd;

        if (createNewDock) {
            System.out.println("Create a cargo dock first.");
            cd = CargoDockService.createCargoDock();
        } else
            cd = cargoDock;

        System.out.print("Ship Name: ");
        String name = sc.nextLine();
        CargoShip cs = new CargoShip(cd, name);

        System.out.print("Captain: ");
        cs.setCaptain(sc.nextLine());
        System.out.print("Max Speed: ");
        cs.setMaxSpeed(sc.nextDouble());
        System.out.print("Crew Member Count: ");
        cs.setCrewMembersCount(sc.nextInt());
        System.out.print("Cargo Weight: ");
        cs.setCargoWeight(sc.nextDouble());
        System.out.print("Max Cargo Weight: ");
        cs.setMaxCargoWeight(sc.nextDouble());
        System.out.println("Done!");
        return cs;
    }

    public static void printNameBiggestMaxCargo(CargoShip[] ships) {
        CargoShip max = ships[0];

        for (CargoShip sh : ships) {
            if (sh.getMaxCargoWeight() >= max.getMaxCargoWeight())
                max = sh;
        }

        System.out.println(max.getName());
    }

    public static void printLowestCargo(CargoShip[] ships) {
        CargoShip min = ships[0];

        for (CargoShip sh : ships) {
            if (sh.getCargoWeight() <= min.getCargoWeight())
                min = sh;
        }

        printCargoShip(min);
    }

    public static void writeToFile(CargoShip ship, String path) {
        String info = FORMATTER.format(FILE_FORMAT, ship.getName(), ship.getCurrentPos().getName(),
                ship.getCaptain(), ship.getCrewMembersCount(), ship.getMaxSpeed(), ship.getCargoWeight(), ship.getMaxCargoWeight()).toString();

        try {
            System.out.println("Writing " + ship.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
