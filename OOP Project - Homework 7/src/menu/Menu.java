package menu;

import reception.City;
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
                                    System.out.println("Choose a function\n-------- SERVICES --------\n1.Print A Cargo Ship\n2.Print The Name Of The Cargo Ship With The Biggest Current Cargo Weight\n" +
                                            "3.Print The Ship With The Least Cargo Weight\n4.Write The Ship To A File\n5.Create A New Cargo Ship\n-------- CONTROLS --------\n6.Move A Ship To A New City\n7.Transport Cargo To A City\n" +
                                            "8.Take Cargo Onto The Ship From Its Dock\n9.Empty A Ship's Cargo Into Its Dock\n10.Dump A Ship's Cargo Into The Ocean\n11.Go Back");
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
                                            System.out.println("Choose The Ship You Want To Move:");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            CargoShip toMove = cargoShips.get(objNum - 1);

                                            System.out.println("Choose The City You Want To Move The Ship To:");
                                            ArrayList<City> presentCities = City.printPlaces(toMove.getCurrentPos().getPlacement());
                                            int placeNum = scanner.nextInt();
                                            if (placeNum == presentCities.size() + 1)
                                                break;

                                            City city = presentCities.get(placeNum - 1);
                                            System.out.println("Choose The Dock Of The City (You Can Choose Any Type Of Dock):");
                                            int j = City.printDocksOfPlace(city);
                                            int dockNum = scanner.nextInt();
                                            if (dockNum == j)
                                                break;

                                            toMove.moveTo(city.getReceptions().get(dockNum - 1));
                                            System.out.println("The Ship " + toMove.getName() + " Has Moved To The City Of " + city + ".");
                                            break;
                                        case 7:
                                            System.out.println("Choose The Ship You Want To Transport Cargo With:");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            CargoShip toTransport = cargoShips.get(objNum - 1);

                                            System.out.println("Choose The City You Want To Move The Ship To:");
                                            ArrayList<City> cargoDocksPlaces = DockService.printCargoDocks();
                                            placeNum = scanner.nextInt();
                                            if (placeNum == cargoDocksPlaces.size() + 1)
                                                break;
                                            city = cargoDocksPlaces.get(placeNum - 1);
                                            CargoDock dock = (CargoDock) city.getReceptions().get(0);

                                            double maxWeight = Math.min((toTransport.getMaxCargoWeight() - toTransport.getCargoWeight()), ((CargoDock) toTransport.getCurrentPos()).getCurrentCargoWeight());
                                            System.out.println("Enter The Amount Of Cargo To Transport (0 < Weight <= " + maxWeight + ")");
                                            double weight = scanner.nextDouble();

                                            if (toTransport.transportTo(weight, dock))
                                                System.out.println("The Ship " + toTransport.getName() + " Has Transported " + weight + " Cargo To The City Of " + city + ".");
                                            break;
                                        case 8:
                                            System.out.println("Choose The Ship You Want To Load With Cargo:");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            CargoShip ship = cargoShips.get(objNum - 1);

                                            maxWeight = Math.min((ship.getMaxCargoWeight() - ship.getCargoWeight()), ((CargoDock) ship.getCurrentPos()).getCurrentCargoWeight());

                                            System.out.println("Enter The Amount Of Cargo To Take (0 < Weight <= " + maxWeight + ")");
                                            weight = scanner.nextDouble();

                                            if (ship.takeFromCurrentPos(weight))
                                                System.out.println("The Ship " + ship.getName() + " Has Taken " + weight + " Cargo Onto Its Board.");
                                            break;
                                        case 9:
                                            System.out.println("Choose The Ship You Want To Empty:");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            ship = cargoShips.get(objNum - 1);
                                            double temp = ship.emptyToCurrentPos();
                                            if (temp > 0)
                                                System.out.println("The Ship " + ship.getName() + " Has Emptied " + temp + " Cargo Into Its Dock.");
                                            break;
                                        case 10:
                                            System.out.println("Choose The Ship You Want To Empty:");
                                            ShipService.printShips(cargoShips);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            ship = cargoShips.get(objNum - 1);

                                            temp = ship.dump();
                                            if (temp > 0)
                                                System.out.println("The Ship " + ship.getName() + " Has Dumped " + temp + " Cargo Into The Ocean.");
                                            break;
                                        case 11:
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
                                    System.out.println("Choose a function\n-------- SERVICES --------\n1.Print A Cruiser\n2.Print The Ships In Ascending Order By Ticket Cost\n" +
                                            "3.Print The Ship With The Least Number Of Passengers\n4.Write The Ship To A File\n5.Create A New Cruiser\n" +
                                            "-------- CONTROLS --------\n6.Move A Ship To A New City\n7.Transport People To A City\n.Go Back");
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
                                            System.out.println("Choose The Ship You Want To Move:");
                                            ShipService.printShips(cruisers);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            Cruiser toMove = cruisers.get(objNum - 1);

                                            System.out.println("Choose The City You Want To Move The Ship To:");
                                            ArrayList<City> presentCities = City.printPlaces(toMove.getCurrentPos().getPlacement());
                                            int placeNum = scanner.nextInt();
                                            if (placeNum == presentCities.size() + 1)
                                                break;

                                            City city = presentCities.get(placeNum - 1);
                                            System.out.println("Choose The Dock Of The City (You Can Choose Any Type Of Dock):");
                                            int j = City.printDocksOfPlace(city);
                                            int dockNum = scanner.nextInt();
                                            if (dockNum == j)
                                                break;

                                            toMove.moveTo(city.getReceptions().get(dockNum - 1));
                                            System.out.println("The Ship " + toMove.getName() + " Has Moved To The City Of " + city + ".");
                                            break;
                                        case 7:
                                            System.out.println("Choose The Ship You Want To Transport People With:");
                                            ShipService.printShips(cruisers);
                                            objNum = scanner.nextInt();
                                            if (objNum == cargoShips.size() + 1)
                                                break;

                                            Cruiser toTransport = cruisers.get(objNum - 1);

                                            System.out.println("Choose The City You Want To Move The Ship To:");
                                            ArrayList<City> touristDocks = DockService.printTouristDocks();
                                            placeNum = scanner.nextInt();
                                            if (placeNum == touristDocks.size() + 1)
                                                break;
                                            city = touristDocks.get(placeNum - 1);
                                            TouristDock dock = (TouristDock) city.getReceptions().get(1);

                                            int maxPeople = Math.min((toTransport.getMaxPassengerCount() - toTransport.getPassengerCount()), ((TouristDock) toTransport.getCurrentPos()).getCurrentPeopleCount());
                                            System.out.println("Enter The Number Of People To Transport (0 < People <= " + maxPeople + ")");
                                            int people = scanner.nextInt();

                                            if (toTransport.transport(people, dock))
                                                System.out.println("The Ship " + toTransport.getName() + " Has Transported " + people + " People To The City Of " + city + ".");
                                            break;
                                        case 8:
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

                                    ArrayList<City> cargoDocks;
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

                                    ArrayList<City> touristDocks;
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
