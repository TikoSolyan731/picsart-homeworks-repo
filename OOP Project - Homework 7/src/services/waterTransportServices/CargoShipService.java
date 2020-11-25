package services.waterTransportServices;

import transport.waterTransport.CargoShip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Formatter;

public class CargoShipService {
    public static String path = "cargo.txt";
    private static final String FILE_FORMAT = "%s,%s,%s,%d,%.1f,%.1f,%.1f\n";

    public static void printCargoShip(CargoShip ship) {
        System.out.printf("Name: %s, Current Pos: %s, Max Speed: %.1f\n" +
                        "Cargo: %.1f, Max Cargo: %.1f\n", ship.getName(), ship.getCurrentPos().getName(),
                ship.getMaxSpeed(), ship.getCargoWeight(), ship.getMaxCargoWeight());
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

    public static void writeToFile(CargoShip ship) {
        Formatter f = new Formatter();
        String info = f.format(FILE_FORMAT, ship.getName(), ship.getCurrentPos().getName(),
                ship.getCaptain(), ship.getCrewMembersCount(), ship.getMaxSpeed(), ship.getCargoWeight(), ship.getMaxCargoWeight()).toString();

        try {
            System.out.println("Writing " + ship.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
