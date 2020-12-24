package services.waterTransportServices;

import reception.City;
import reception.waterReception.TouristDock;
import transport.waterTransport.Cruiser;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class CruiserService {
    private static final String FILE_FORMAT = "%s,%s,%s,%d,%.1f,%d,%d,%.1f\n";
    private static final Formatter FORMATTER = new Formatter();

    public static void printCruiser(Cruiser ship) {
        System.out.printf("Name: %s, Current Pos: %s, Max Speed: %.1f,\n" +
                        "Passenger Count: %d, Max Passenger Count: %d, Ticket Cost: %.1f\n", ship.getName(), ship.getCurrentPos().getPlacement(),
                ship.getMaxSpeed(), ship.getPassengerCount(), ship.getMaxPassengerCount(), ship.getTicketCost());
    }

    public static Cruiser createCruiser() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Choose the place to create the ship at: ");
        City[] places = City.values();
        int i = 1;
        for (City place : places) {
            sb.append(i++).append(".").append(place).append(" - has ").append(place.getReceptions()).append('\n');
        }
        int p;
        while (true) {
            System.out.println(sb.toString());
            p = sc.nextInt();
            if (!Utils.checkInput(p, places.length)) {
                System.out.println("Enter valid number.");
                continue;
            }
            if (!(places[p - 1].getReceptions().get(1) instanceof TouristDock)) {
                System.out.println("There are no Tourist Docks at " + places[p - 1]);
                System.out.println("Choose another place: ");
            } else
                break;
        }
        TouristDock td = (TouristDock) places[p - 1].getReceptions().get(1);

        sc.nextLine();
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

    public static void printTicketCostAscending(List<Cruiser> ships) {
        List<Cruiser> shipsCopy = new ArrayList<>(ships.size());
        Collections.copy(shipsCopy, ships);
        shipsCopy.sort(new Comparator<Cruiser>() {
            @Override
            public int compare(Cruiser o1, Cruiser o2) {
                return (int) (o1.getTicketCost() - o2.getTicketCost());
            }
        });

        for (Cruiser c : shipsCopy) {
            printCruiser(c);
        }
    }

    public static void printLeastPassengerCount(List<Cruiser> ships) {
        Cruiser min = Collections.min(ships, new Comparator<Cruiser>() {
            @Override
            public int compare(Cruiser o1, Cruiser o2) {
                return o1.getPassengerCount() - o2.getPassengerCount();
            }
        });

        printCruiser(min);
    }

    public static void writeToFile(Cruiser ship, String path) {
        String info = FORMATTER.format(FILE_FORMAT, ship.getName(), ship.getCurrentPos().getPlacement(),
                ship.getCaptain(), ship.getCrewMembersCount(), ship.getMaxSpeed(),
                ship.getPassengerCount(), ship.getMaxPassengerCount(),
                ship.getTicketCost()).toString();

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
            System.out.println("Writing " + ship.getName() + " to the file.");
            Files.write(Paths.get(path), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
