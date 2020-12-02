package menu;

import reception.waterReception.CargoDock;
import reception.waterReception.Dock;
import reception.waterReception.TouristDock;
import services.waterReceptionServices.CargoDockService;
import services.waterReceptionServices.TouristDockService;
import services.waterTransportServices.CargoShipService;
import services.waterTransportServices.CruiserService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;
import transport.waterTransport.Ship;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<CargoShip> cargoShips = new ArrayList<>();
        ArrayList<CargoDock> cargoDocks = new ArrayList<>();
        ArrayList<Cruiser> cruisers = new ArrayList<>();
        ArrayList<TouristDock> touristDocks = new ArrayList<>();

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

                                while (beforeMethods) {
                                    if (cargoShips.isEmpty()) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                CargoShip cs = CargoShipService.createCargoShip(true, null);
                                                cargoShips.add(cs);
                                                cargoDocks.add((CargoDock) cs.getCurrentPos());

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
                                            printAvailableShips(cargoShips);

                                            int objNum = scanner.nextInt();

                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            CargoShipService.printCargoShip(cargoShips.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            printAvailableShips(cargoShips);

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
                                            printAvailableShips(cargoShips);

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
                                            printAvailableShips(cargoShips);
                                            objNum = scanner.nextInt();

                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CargoShipService.writeToFile(cargoShips.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            CargoShip cargoShip;

                                            if (!cargoDocks.isEmpty()) {
                                                System.out.println("You already have docks created. Do you wish to utilize them or create a new one?");
                                                printAvailableDocks(cargoDocks);
                                                System.out.println(cargoDocks.size() + 2 + ".Create New Dock");

                                                int n = scanner.nextInt();
                                                if (n == cargoDocks.size() + 2) {
                                                    cargoShip = CargoShipService.createCargoShip(true, null);
                                                    cargoDocks.add((CargoDock) cargoShip.getCurrentPos());
                                                } else
                                                    cargoShip = CargoShipService.createCargoShip(false, cargoDocks.get(n - 1));
                                            } else {
                                                cargoShip = CargoShipService.createCargoShip(true, null);
                                                cargoDocks.add((CargoDock) cargoShip.getCurrentPos());
                                            }
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

                                while (beforeMethods) {
                                    if (cruisers.isEmpty()) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                Cruiser cruiser = CruiserService.createCruiser(true, null);
                                                cruisers.add(cruiser);
                                                touristDocks.add((TouristDock) cruiser.getCurrentPos());

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
                                            printAvailableShips(cruisers);

                                            int objNum = scanner.nextInt();

                                            if (objNum == cruisers.size() + 1)
                                                break;

                                            CruiserService.printCruiser(cruisers.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            printAvailableShips(cruisers);

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
                                            printAvailableShips(cruisers);

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
                                            printAvailableShips(cruisers);
                                            objNum = scanner.nextInt();

                                            if (objNum == cruisers.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CruiserService.writeToFile(cruisers.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            Cruiser cruiser;

                                            if (!cruisers.isEmpty()) {
                                                System.out.println("You already have docks created. Do you wish to utilize them or create a new one?");
                                                printAvailableDocks(touristDocks);
                                                System.out.println(touristDocks.size() + 2 + ".Create New Dock");

                                                int n = scanner.nextInt();
                                                if (n == touristDocks.size() + 2) {
                                                    cruiser = CruiserService.createCruiser(true, null);
                                                    touristDocks.add((TouristDock) cruiser.getCurrentPos());
                                                } else
                                                    cruiser = CruiserService.createCruiser(false, touristDocks.get(n - 1));
                                            } else {
                                                cruiser = CruiserService.createCruiser(true, null);
                                                touristDocks.add((TouristDock) cruiser.getCurrentPos());
                                            }
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
                                boolean goBack = false;
                                boolean beforeMethods = true;

                                while (beforeMethods) {
                                    if (cargoDocks.isEmpty()) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                CargoDock cargoDock = CargoDockService.createCargoDock();
                                                cargoDocks.add(cargoDock);

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
                                    System.out.println("Choose a function:\n1.printCargoDock\n2.printBiggestCargoWeight\n" +
                                            "3.printDockedCargoShipBiggestMaxWeight\n4.writeToFile\n5.createCargoDock\n6.Go Back");
                                    int funcNum = scanner.nextInt();

                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            printAvailableDocks(cargoDocks);

                                            int objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            CargoDockService.printCargoDock(cargoDocks.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            printAvailableDocks(cargoDocks);

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == cargoDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            CargoDock[] cargoDocks1 = new CargoDock[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                cargoDocks1[i] = cargoDocks.get(Integer.parseInt(numsStr[i]) - 1);

                                            CargoDockService.printBiggestCargoWeight(cargoDocks1);
                                            break;
                                        case 3:
                                            System.out.println("Choose an object.");
                                            printAvailableDocks(cargoDocks);

                                            objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            CargoDockService.printDockedCargoShipBiggestMaxWeight(cargoDocks.get(objNum - 1));
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            printAvailableDocks(cargoDocks);
                                            objNum = scanner.nextInt();

                                            if (objNum == cargoDocks.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            CargoDockService.writeToFile(cargoDocks.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            CargoDock cargoDock = CargoDockService.createCargoDock();

                                            cargoDocks.add(cargoDock);
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

                                while (beforeMethods) {
                                    if (touristDocks.isEmpty()) {
                                        System.out.println("You have no objects created. Do you want to create an object of this type?\n" +
                                                "1.Yes\n2.Go Back");

                                        switch (scanner.nextInt()) {
                                            case 1:
                                                TouristDock touristDock = TouristDockService.createTouristDock();
                                                touristDocks.add(touristDock);

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
                                    System.out.println("Choose a function:\n1.printTouristDock\n2.printNameLeastPeopleCount\n" +
                                            "3.printByMaxPeopleCountAscending\n4.writeToFile\n5.createTouristDock\n6.Go Back");
                                    int funcNum = scanner.nextInt();

                                    switch (funcNum) {
                                        case 1:
                                            System.out.println("Choose an object.");
                                            printAvailableDocks(touristDocks);

                                            int objNum = scanner.nextInt();

                                            if (objNum == touristDocks.size() + 1)
                                                break;

                                            TouristDockService.printTouristDock(touristDocks.get(objNum - 1));
                                            break;
                                        case 2:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            printAvailableDocks(touristDocks);

                                            scanner.nextLine();
                                            String line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == touristDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            String[] numsStr = line.split(" ");

                                            TouristDock[] touristDocks1 = new TouristDock[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                touristDocks1[i] = touristDocks.get(Integer.parseInt(numsStr[i]) - 1);

                                            TouristDockService.printNameLeastPeopleCount(touristDocks1);
                                            break;
                                        case 3:
                                            System.out.println("Choose objects for the array like this (1 2 3 ...)");
                                            printAvailableDocks(touristDocks);

                                            scanner.nextLine();
                                            line = scanner.nextLine();
                                            if (line.length() == 1 && Integer.parseInt(line) == touristDocks.size() + 1) {
                                                System.out.println("Going Back.");
                                                break;
                                            }
                                            numsStr = line.split(" ");

                                            touristDocks1 = new TouristDock[numsStr.length];
                                            for (int i = 0; i < numsStr.length; i++)
                                                touristDocks1[i] = touristDocks.get(Integer.parseInt(numsStr[i]) - 1);

                                            TouristDockService.printByMaxPeopleCountAscending(touristDocks1);
                                            break;
                                        case 4:
                                            System.out.println("Choose an object.");
                                            printAvailableDocks(touristDocks);
                                            objNum = scanner.nextInt();

                                            if (objNum == touristDocks.size() + 1)
                                                break;

                                            System.out.println("Write the path to the file.");
                                            String path = scanner.next();

                                            TouristDockService.writeToFile(touristDocks.get(objNum - 1), path);
                                            break;
                                        case 5:
                                            TouristDock touristDock = TouristDockService.createTouristDock();

                                            touristDocks.add(touristDock);
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

    private static void printAvailableShips(ArrayList<? extends Ship> list) {
        var iter = list.iterator();
        int i = 1;

        while (iter.hasNext()) {
            System.out.println(i + "." + iter.next().getName());
            i++;
        }
        System.out.println(i + ".Go Back");
    }

    private static void printAvailableDocks(ArrayList<? extends Dock> list) {
        var iter = list.iterator();
        int i = 1;

        while (iter.hasNext()) {
            System.out.println(i + "." + iter.next().getName());
            i++;
        }
        System.out.println(i + ".Go Back");
    }
}
