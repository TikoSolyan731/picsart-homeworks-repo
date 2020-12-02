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
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class CruiserService {
    private static final String FILE_FORMAT = "%s,%s,%s,%d,%.1f,%d,%d,%.1f\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printCruiser(Cruiser ship) {
        System.out.printf("Name: %s, Current Pos: %s, Max Speed: %.1f,\n" +
                        "Passenger Count: %d, Max Passenger Count: %d, Ticket Cost: %.1f\n", ship.getName(), ship.getCurrentPos().getName(),
                ship.getMaxSpeed(), ship.getPassengerCount(), ship.getMaxPassengerCount(), ship.getTicketCost());
    }

    public static Cruiser createCruiser(boolean createNewDock, TouristDock touristDock) {
        Scanner sc = new Scanner(System.in);

        TouristDock td;

        if (createNewDock) {
            System.out.println("Create a tourist dock first.");
            td = TouristDockService.createTouristDock();
        } else
            td = touristDock;

        System.out.print("Ship Name: ");
        String name = sc.nextLine();

        Cruiser cs = new Cruiser(td, name);
        System.out.print("Captain: ");
        cs.setCaptain(sc.nextLine());
        System.out.print("Max Speed: ");
        cs.setMaxSpeed(sc.nextDouble());
        System.out.print("Crew Member Count: ");
        cs.setCrewMembersCount(sc.nextInt());
        System.out.print("Passenger Count: ");
        cs.setPassengerCount(sc.nextInt());
        System.out.print("Max Passenger Count: ");
        cs.setMaxPassengerCount(sc.nextInt());
        System.out.print("Ticket Cost: ");
        cs.setTicketCost(sc.nextDouble());
        System.out.println("Done!");
        return cs;
    }

    public static void printTicketCostAscending(Cruiser[] ships) {
        Cruiser[] temp = Arrays.copyOf(ships, ships.length);

        boolean isSorted = false;
        int count = 0;

        while (!isSorted) {
            isSorted = true;

            for (int i = 0; i < temp.length - 1 - count; i++) {
                if (temp[i].getTicketCost() >= temp[i + 1].getTicketCost()) {
                    Cruiser c = temp[i];
                    temp[i] = temp[i + 1];
                    temp[i + 1] = c;

                    isSorted = false;
                }
            }
            count++;
        }

        for (Cruiser c : temp) {
            printCruiser(c);
        }
    }

    public static void printLeastPassengerCount(Cruiser[] ships) {
        Cruiser min = ships[0];

        for (Cruiser c : ships) {
            if (c.getPassengerCount() <= min.getPassengerCount())
                min = c;
        }

        printCruiser(min);
    }

    public static void writeToFile(Cruiser ship, String path) {
        String info = FORMATTER.format(FILE_FORMAT, ship.getName(), ship.getCurrentPos().getName(),
                ship.getCaptain(), ship.getCrewMembersCount(), ship.getMaxSpeed(),
                ship.getPassengerCount(), ship.getMaxPassengerCount(),
                ship.getTicketCost()).toString();

        try {
            System.out.println("Writing " + ship.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
