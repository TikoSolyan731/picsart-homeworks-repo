package main;

import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import services.waterReceptionServices.CargoDockService;
import services.waterReceptionServices.TouristDockService;
import services.waterTransportServices.CargoShipService;
import services.waterTransportServices.CruiserService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;
import transport.waterTransport.Ship;

import java.util.Scanner;

public class Project {
    public static void main(String[] args) {
        // Part 1. The main classes and functionality.
        // In the future implementations for airplanes and airports can be created.

        CargoDock d1 = new CargoDock("Barcelona", 5, 4500);
        CargoDock d2 = new CargoDock("Madrid", 6, 6000);
        TouristDock d3 = new TouristDock("Cuba", 5, 420, 1000);
        TouristDock d4 = new TouristDock("Bali", 6);

        CargoShip sh1 = new CargoShip(d1, "bla bla1");
        CargoShip sh2 = new CargoShip(d2, "bla bla2");
        Cruiser sh3 = new Cruiser(d2, "Santa Maria");
        Cruiser sh4 = new Cruiser(d1, "Santa Luchia");
        Cruiser sh5 = new Cruiser(d2, "Santa Barbara");

        CargoShip[] cargos = {sh1, sh2};
        Cruiser[] cruisers = {sh3, sh4, sh5};

        CargoDock[] cargoDocks = {d1, d2};
        TouristDock[] touristDocks = {d3, d4};

        System.out.println(sh1.getCurrentPos().getName());
        System.out.println(sh2.getCurrentPos().getName());

        sh1.setCargoWeight(400);
        System.out.println(sh1.getCargoWeight());

        System.out.println(sh1.empty());
        System.out.println(sh1.getCargoWeight());

        sh1.setCargoWeight(1000);
        System.out.println(sh1.emptyToCurrentPos());
        System.out.println(d1.getCurrentCargoWeight());

        System.out.println("---------------");

        for (Ship sh : d1.getDockedShips()) {
            if (sh != null)
                System.out.println(sh.getName());
        }

        sh1.move(d1, d2);

        for (Ship sh : d2.getDockedShips()) {
            if (sh != null)
                System.out.println(sh.getName());
        }

        System.out.println();
        System.out.println();
        System.out.println("---------checking transport-----------");

        sh1.setCargoWeight(1000);
        System.out.println(sh1.getCargoWeight());

        sh1.transport(800, d2, d1);
        System.out.println("sh1 cargo weight: " + sh1.getCargoWeight());
        System.out.println("d1 cargo: " + d1.getCurrentCargoWeight());
        System.out.println("d2 cargo: " + d2.getCurrentCargoWeight());

        System.out.println("Dumping the rest to the dock " + sh1.getCurrentPos().getName());
        sh1.emptyToCurrentPos();

        System.out.println("d1 cargo: " + d1.getCurrentCargoWeight());
        System.out.println(sh1.getCargoWeight());

        sh1.takeFromCurrentPos(3500);
        System.out.println(d1.getCurrentCargoWeight());

        sh1.move(d1, d2);
        sh1.emptyToCurrentPos();
        System.out.println(d2.getCurrentCargoWeight());

        System.out.println();
        System.out.println();
        System.out.println("-----------upgrading----------");
        d2.upgrade();
        System.out.println(d2.getMaxDockedShips());
        for (Ship sh : d2.getDockedShips()) {
            if (sh != null)
                System.out.println(sh.getName());
        }

        System.out.println("-----------cruiser-----------");

        sh3.move(d2, d3);
        sh3.transport(100, d3, d4);
        System.out.println(d3.getCurrentPeopleCount());
        System.out.println(d4.getCurrentPeopleCount());

        // Part 2. Services for models.

        CargoShipService.printCargoShip(sh1);
        System.out.println();

        sh1.setMaxCargoWeight(1000);
        sh2.setMaxCargoWeight(2000);
        CargoShipService.printNameBiggestMaxCargo(cargos);

        sh1.setCargoWeight(120);
        sh2.setCargoWeight(220);
        CargoShipService.printLowestCargo(cargos);

        // Part 3. Writing to file.

        String path = "cargo.txt";

        //CargoShipService.writeToFile(sh1, path);
        //CargoShipService.writeToFile(sh2, path);

        // Part 4. Menu Implementation.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a Category:\n1.Transport\n2.Reception\nPress anything else to quit.");

        switch (scanner.nextInt()) {
            case 1:
                boolean isMenuActive = true;

                while (isMenuActive) {
                    System.out.println("Choose Transport:\n1.Cargo Ship\n2.Cruiser\n3.Quit");
                    int item = scanner.nextInt();

                    switch (item) {
                        case 1:
                            boolean isMethodsMenuActive = true;

                            while (isMethodsMenuActive) {
                                System.out.println("Choose a function:\n1.printCargoShip\n2.printNameBiggestMaxCargo\n" +
                                        "3.printLowestCargo\n4.writeToFile\n5.Quit");
                                int funcNum = scanner.nextInt();

                                switch (funcNum) {
                                    case 1:
                                        CargoShipService.printCargoShip(sh1);
                                        break;
                                    case 2:
                                        CargoShipService.printNameBiggestMaxCargo(cargos);
                                        break;
                                    case 3:
                                        CargoShipService.printLowestCargo(cargos);
                                        break;
                                    case 4:
                                        CargoShipService.writeToFile(sh1, path);
                                        break;
                                    case 5:
                                        System.out.println("Quiting.");
                                        isMethodsMenuActive = false;
                                        break;
                                }
                            }
                            break;
                        case 2:
                            isMethodsMenuActive = true;

                            while (isMethodsMenuActive) {
                                System.out.println("Choose a function:\n1.printCruiser\n2.printTicketCostAscending\n" +
                                        "3.printLeastPassengerCount\n4.writeToFile\n5.Quit");
                                int funcNum = scanner.nextInt();

                                switch (funcNum) {
                                    case 1:
                                        CruiserService.printCruiser(sh3);
                                        break;
                                    case 2:
                                        CruiserService.printTicketCostAscending(cruisers);
                                        break;
                                    case 3:
                                        CruiserService.printLeastPassengerCount(cruisers);
                                        break;
                                    case 4:
                                        CruiserService.writeToFile(sh5, path);
                                        break;
                                    case 5:
                                        System.out.println("Quiting.");
                                        isMethodsMenuActive = false;
                                        break;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Quiting.");
                            isMenuActive = false;
                            break;
                        default:
                            System.out.println("Wrong command.");
                            break;
                    }
                }
                break;
            case 2:
                isMenuActive = true;

                while (isMenuActive) {
                    System.out.println("Choose Reception:\n1.Cargo Dock\n2.Tourist Dock\n3.Quit");
                    int item = scanner.nextInt();

                    switch (item) {
                        case 1:
                            boolean isMethodsMenuActive = true;

                            while (isMethodsMenuActive) {
                                System.out.println("Choose a function:\n1.printCargoDock\n2.printBiggestCargoWeight\n" +
                                        "3.printDockedCargoShipBiggestMaxWeight\n4.writeToFile\n5.Quit");
                                int funcNum = scanner.nextInt();

                                switch (funcNum) {
                                    case 1:
                                        CargoDockService.printCargoDock(d1);
                                        break;
                                    case 2:
                                        CargoDockService.printBiggestCargoWeight(cargoDocks);
                                        break;
                                    case 3:
                                        CargoDockService.printDockedCargoShipBiggestMaxWeight(d2);
                                        break;
                                    case 4:
                                        CargoDockService.writeToFile(d1, path);
                                        break;
                                    case 5:
                                        System.out.println("Quiting.");
                                        isMethodsMenuActive = false;
                                        break;
                                }
                            }
                            break;
                        case 2:
                            isMethodsMenuActive = true;

                            while (isMethodsMenuActive) {
                                System.out.println("Choose a function:\n1.printTouristDock\n2.printNameLeastPeopleCount\n" +
                                        "3.printByMaxPeopleCountAscending\n4.writeToFile\n5.Quit");
                                int funcNum = scanner.nextInt();

                                switch (funcNum) {
                                    case 1:
                                        TouristDockService.printTouristDock(d3);
                                        break;
                                    case 2:
                                        TouristDockService.printNameLeastPeopleCount(touristDocks);
                                        break;
                                    case 3:
                                        TouristDockService.printByMaxPeopleCountAscending(touristDocks);
                                        break;
                                    case 4:
                                        TouristDockService.writeToFile(d3, path);
                                        break;
                                    case 5:
                                        System.out.println("Quiting.");
                                        isMethodsMenuActive = false;
                                        break;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Quiting.");
                            isMenuActive = false;
                            break;
                        default:
                            System.out.println("Wrong command.");
                            break;
                    }
                }
                break;
            default:
                System.out.println("Menu Quited.");
        }
    }
}
