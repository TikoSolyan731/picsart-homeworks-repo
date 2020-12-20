package menu;

import reception.Map;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import services.waterReceptionServices.CargoDockService;
import services.waterReceptionServices.DockService;
import services.waterReceptionServices.TouristDockService;
import services.waterTransportServices.CargoShipService;
import services.waterTransportServices.CruiserService;
import services.waterTransportServices.ShipService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<CargoShip> cargoShips = new ArrayList<>();
        ArrayList<Cruiser> cruisers = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Choose a Category:\n1.Transport\n2.Reception\n3.Quit");
            switch (scanner.nextInt()) {
                case 1:
                    boolean isMenuActive = true;

                    while (isMenuActive) {
                        System.out.println("Choose Transport:\n1.Cargo Ship\n2.Cruiser\n3.Go Back");
                        int item = scanner.nextInt();

                        switch (item) {
                            case 1:
                                boolean goBack = false;
                                boolean beforeMethods = true;

                                if (cargoShips.isEmpty()) {
                                    while (beforeMethods) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                CargoShip cs = CargoShipService.createCargoShip();
                                                cargoShips.add(cs);

                                                beforeMethods = false;
                                                break;
                                            case 2:
                                                System.out.println("Going Back.");
                                                beforeMethods = false;
                                                goBack = true;
                                                break;
                                            default:
                                                beforeMethods = true;
                                                break;
                                        }
                                    }
                                }
                                boolean isMethodsMenuActive = true;

                                while (isMethodsMenuActive && !goBack) {
                                    System.out.println("Choose a function:\n1.printCargoShip\n2.printNameBiggestMaxCargo\n" +
                                            "3.printLowestCargo\n4.writeToFile\n5.createCargoShip\n6.Go Back");
                                    int funcNum = scanner.nextInt();

                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            ShipService.printShips(cargoShips);

                                            int objNum = scanner.nextInt();

                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            CargoShipService.printCargoShip(cargoShips.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            ShipService.printShips(cargoShips);

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cargoShips.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            CargoShip[] cargoShips1 = new CargoShip[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                cargoShips1[i] = cargoShips.get(Integer.parseInt(numsStr[i]) - 1);

                                            CargoShipService.printNameBiggestMaxCargo(cargoShips1);
                                            break;
                                        case 3:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            ShipService.printShips(cargoShips);

                                            scanner.nextLine();
                                            line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cargoShips.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            numsStr = line.split(" ");

                                            cargoShips1 = new CargoShip[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                cargoShips1[i] = cargoShips.get(Integer.parseInt(numsStr[i]) - 1);

                                            CargoShipService.printLowestCargo(cargoShips1);
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();

                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CargoShipService.writeToFile(cargoShips.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            CargoShip cargoShip = CargoShipService.createCargoShip();

                                            cargoShips.add(cargoShip);
                                            break;
                                        case 6:
                                            System.out.println("Going Back.");
                                            isMethodsMenuActive = false;
                                            break;
                                    }
                                }

                                break;
                            case 2:
                                goBack = false;
                                beforeMethods = true;


                                if (cruisers.isEmpty()) {
                                    while (beforeMethods) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                Cruiser cruiser = CruiserService.createCruiser();
                                                cruisers.add(cruiser);

                                                beforeMethods = false;
                                                break;
                                            case 2:
                                                System.out.println("Going Back.");
                                                beforeMethods = false;
                                                goBack = true;
                                                break;
                                            default:
                                                beforeMethods = true;
                                                break;
                                        }
                                    }
                                }

                                isMethodsMenuActive = true;

                                while (isMethodsMenuActive && !goBack) {
                                    System.out.println("Choose a function:\n1.printCruiser\n2.printTicketCostAscending\n" +
                                            "3.printLeastPassengerCount\n4.writeToFile\n5.createCruiser\n6.Go Back");
                                    int funcNum = scanner.nextInt();

                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            ShipService.printShips(cruisers);

                                            int objNum = scanner.nextInt();

                                            if (objNum == cruisers.size() + 1)
                                                break;

                                            CruiserService.printCruiser(cruisers.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            ShipService.printShips(cruisers);

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cruisers.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            Cruiser[] cruisers1 = new Cruiser[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                cruisers1[i] = cruisers.get(Integer.parseInt(numsStr[i]) - 1);

                                            CruiserService.printTicketCostAscending(cruisers1);
                                            break;
                                        case 3:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            ShipService.printShips(cruisers);

                                            scanner.nextLine();
                                            line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cruisers.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            numsStr = line.split(" ");

                                            cruisers1 = new Cruiser[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                cruisers1[i] = cruisers.get(Integer.parseInt(numsStr[i]) - 1);

                                            CruiserService.printLeastPassengerCount(cruisers1);
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            ShipService.printShips(cruisers);
                                            objNum = scanner.nextInt();

                                            if (objNum == cruisers.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CruiserService.writeToFile(cruisers.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            Cruiser cruiser = CruiserService.createCruiser();

                                            cruisers.add(cruiser);
                                            break;
                                        case 6:
                                            System.out.println("Going Back.");
                                            isMethodsMenuActive = false;
                                            break;
                                    }
                                }

                                break;
                            case 3:
                                System.out.println("Going Back.");
                                isMenuActive = false;
                                break;
                            default:
                                System.out.println("Wrong Command.");
                                break;
                        }
                    }

                    break;
                case 2:
                    isMenuActive = true;

                    while (isMenuActive) {
                        System.out.println("Choose Reception:\n1.Cargo Dock\n2.Tourist Dock\n3.Go Back");
                        int item = scanner.nextInt();

                        switch (item) {
                            case 1:
                                boolean isMethodsMenuActive = true;

                                while (isMethodsMenuActive) {
                                    System.out.println("Choose a function:\n1.printCargoDock\n2.printBiggestCargoWeight\n" +
                                            "3.printDockedCargoShipBiggestMaxWeight\n4.writeToFile\n5.Go Back");
                                    int funcNum = scanner.nextInt();

                                    ArrayList<Map> cargoDocks;
                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            cargoDocks = DockService.printCargoDocks();

                                            int objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            CargoDockService.printCargoDock((CargoDock) cargoDocks.get(objNum - 1).getReceptions().get(0));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this: 1 2 3 ...");
                                            cargoDocks = DockService.printCargoDocks();

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cargoDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            ArrayList<CargoDock> cargoDocks1 = new ArrayList<>();
                                            for (int i = 0; i < numsStr.length; i++)
                                                cargoDocks1.add((CargoDock) cargoDocks.get(Integer.parseInt(numsStr[i]) - 1).getReceptions().get(0));

                                            CargoDockService.printBiggestCargoWeight(cargoDocks1);
                                            break;
                                        case 3:
                                            System.out.println("Choose an object.");
                                            cargoDocks = DockService.printCargoDocks();

                                            objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            CargoDockService.printDockedCargoShipBiggestMaxWeight((CargoDock) cargoDocks.get(objNum - 1).getReceptions().get(0));
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            cargoDocks = DockService.printCargoDocks();
                                            objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CargoDockService.writeToFile((CargoDock) cargoDocks.get(objNum - 1).getReceptions().get(0), path);
                                            break;
                                        case 5:
                                            System.out.println("Going Back.");
                                            isMethodsMenuActive = false;
                                            break;
                                    }
                                }
                                break;
                            case 2:
                                isMethodsMenuActive = true;

                                while (isMethodsMenuActive) {
                                    System.out.println("Choose a function:\n1.printTouristDock\n2.printNameLeastPeopleCount\n" +
                                            "3.printByMaxPeopleCountAscending\n4.writeToFile\n5.Go Back");
                                    int funcNum = scanner.nextInt();

                                    ArrayList<Map> touristDocks;
                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            touristDocks = DockService.printTouristDocks();

                                            int objNum = scanner.nextInt();

                                            if (objNum == touristDocks.size() + 1)
                                                break;

                                            TouristDockService.printTouristDock((TouristDock) touristDocks.get(objNum - 1).getReceptions().get(1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            touristDocks = DockService.printTouristDocks();

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == touristDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            TouristDock[] touristDocks1 = new TouristDock[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                touristDocks1[i] = (TouristDock) touristDocks.get(Integer.parseInt(numsStr[i]) - 1).getReceptions().get(1);

                                            TouristDockService.printNameLeastPeopleCount(touristDocks1);
                                            break;
                                        case 3:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            touristDocks = DockService.printTouristDocks();

                                            scanner.nextLine();
                                            line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == touristDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            numsStr = line.split(" ");

                                            touristDocks1 = new TouristDock[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                touristDocks1[i] = (TouristDock) touristDocks.get(Integer.parseInt(numsStr[i]) - 1).getReceptions().get(1);

                                            TouristDockService.printByMaxPeopleCountAscending(touristDocks1);
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            touristDocks = DockService.printTouristDocks();
                                            objNum = scanner.nextInt();

                                            if (objNum == touristDocks.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            TouristDockService.writeToFile((TouristDock) touristDocks.get(objNum - 1).getReceptions().get(1), path);
                                            break;
                                        case 5:
                                            System.out.println("Going Back.");
                                            isMethodsMenuActive = false;
                                            break;
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Going Back.");
                                isMenuActive = false;
                                break;
                            default:
                                System.out.println("Wrong command.");
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Quiting.");
                    isRunning = false;
                    break;
            }
        }
    }
}
