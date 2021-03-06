package menu;

import auth.DB;
import reception.City;
import reception.MapSystem;
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
import transport.waterTransport.Ship;
import utils.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
    private static final DB db;
    private static final MapSystem map;
    private static final Calendar time;
    private static final SimpleDateFormat sdf;

    private static final List<CargoShip> cargoShips;
    private static final List<Cruiser> cruisers;

    static {
        db = DB.getInstance();
        map = MapSystem.getInstance();
        cargoShips = new ArrayList<>();
        cruisers = new ArrayList<>();
        time = Calendar.getInstance();
        sdf = new SimpleDateFormat("MMM dd yyyy");
    }

    public static void start() throws IOException {
        while (true) {
            if (!authorization())
                break;

            Scanner scanner = new Scanner(System.in);

            boolean isRunning = true;

            while (isRunning) {
                System.out.println("Choose a Category:\n1.Transport\n2.Reception\n3.Quit");
                switch (scanner.nextInt()) {
                    case 1:
                        boolean isMenuActive = true;

                        while (isMenuActive) {
                            System.out.println("Choose Transport:\n1.Cargo Ship\n2.Cruiser\n3.Read Ships From File\n4.Go Back");
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
                                                "8.Take Cargo Onto The Ship From Its Dock\n9.Empty A Ship's Cargo Into Its Dock\n10.Dump A Ship's Cargo Into The Ocean\n-------- UPGRADES --------\n11.Upgrade A Ship\n12.Go Back");
                                        int funcNum = scanner.nextInt();

                                        switch (funcNum) {
                                            case 1:
                                                Ship chooseShip = chooseShip(cargoShips, "Choose an object.");
                                                if (chooseShip == null)
                                                    break;

                                                CargoShipService.printCargoShip((CargoShip) chooseShip);
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

                                                List<CargoShip> cargoShips1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    cargoShips1.add(cargoShips.get(Integer.parseInt(s) - 1));

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

                                                cargoShips1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    cargoShips1.add(cargoShips.get(Integer.parseInt(s) - 1));

                                                CargoShipService.printLowestCargo(cargoShips1);
                                                break;
                                            case 4:
                                                chooseShip = chooseShip(cargoShips, "Choose A Ship To Write");
                                                if (chooseShip == null)
                                                    break;

                                                System.out.println("Write the path to the file. If It Doesn't Exist, We Will Create It For You");
                                                String path = scanner.next();

                                                CargoShipService.writeToFile((CargoShip) chooseShip, path);
                                                break;
                                            case 5:
                                                CargoShip cargoShip = CargoShipService.createCargoShip();

                                                cargoShips.add(cargoShip);
                                                break;
                                            case 6:
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                System.out.println();
                                                CargoShip toMove = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Move:");
                                                if (toMove == null)
                                                    break;

                                                System.out.println("Choose The City You Want To Move The Ship To:");
                                                List<City> excludedCities = new ArrayList<>();
                                                excludedCities.add(toMove.getCurrentPos().getPlacement());
                                                excludedCities.addAll(map.getDisconnectedCities(toMove.getCurrentPos().getPlacement()));
                                                List<City> presentCities = City.printPlaces(toMove.getCurrentPos().getPlacement(), excludedCities);
                                                int placeNum = scanner.nextInt();
                                                if (placeNum == presentCities.size() + 1)
                                                    break;

                                                City city = presentCities.get(placeNum - 1);
                                                System.out.println("Choose The Dock Of The City (You Can Choose Any Type Of Dock):");
                                                int j = City.printDocksOfPlace(city);
                                                int dockNum = scanner.nextInt();
                                                if (dockNum == j)
                                                    break;

                                                if (city.getReceptions().get(dockNum - 1) != null)
                                                    toMove.moveTo(city.getReceptions().get(dockNum - 1), time);
                                                else
                                                    toMove.moveTo(city.getReceptions().get(dockNum), time);
                                                System.out.println("The Ship " + toMove.getName() + " Has Moved To The City Of " + city + ".");
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                break;
                                            case 7:
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                System.out.println();
                                                CargoShip toTransport = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Transport Cargo With:");

                                                if (toTransport == null)
                                                    break;

                                                System.out.println("Choose The City You Want To Move The Ship To:");
                                                ArrayList<City> cargoDocksPlaces = DockService.printCargoDocks(toTransport.getCurrentPos().getPlacement());
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
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                break;
                                            case 8:
                                                CargoShip ship = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Load With Cargo:");
                                                if (ship == null)
                                                    break;

                                                maxWeight = Math.min((ship.getMaxCargoWeight() - ship.getCargoWeight()), ((CargoDock) ship.getCurrentPos()).getCurrentCargoWeight());

                                                System.out.println("Enter The Amount Of Cargo To Take (0 < Weight <= " + maxWeight + ")");
                                                weight = scanner.nextDouble();

                                                if (ship.takeFromCurrentPos(weight))
                                                    System.out.println("The Ship " + ship.getName() + " Has Taken " + weight + " Cargo Onto Its Board.");
                                                break;
                                            case 9:
                                                ship = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Empty:");
                                                if (ship == null)
                                                    break;

                                                double temp = ship.emptyToCurrentPos();
                                                if (temp > 0)
                                                    System.out.println("The Ship " + ship.getName() + " Has Emptied " + temp + " Cargo Into Its Dock.");
                                                break;
                                            case 10:
                                                ship = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Empty:");
                                                if (ship == null)
                                                    break;

                                                temp = ship.dump();
                                                if (temp > 0)
                                                    System.out.println("The Ship " + ship.getName() + " Has Dumped " + temp + " Cargo Into The Ocean.");
                                                break;
                                            case 11:
                                                ship = (CargoShip) chooseShip(cargoShips, "Choose The Ship You Want To Upgrade:");
                                                if (ship == null)
                                                    break;

                                                ship.upgrade();
                                                System.out.println("The Ship " + ship.getName() + " Has Been Upgraded:");
                                                CargoShipService.printCargoShip(ship);
                                                break;
                                            case 12:
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
                                                "-------- CONTROLS --------\n6.Move A Ship To A New City\n7.Transport People To A City\n-------- CONTROLS --------\n8.Upgrade A Ship\n9.Go Back");
                                        int funcNum = scanner.nextInt();

                                        switch (funcNum) {
                                            case 1:
                                                Ship ship = chooseShip(cruisers, "Choose An Object");
                                                if (ship == null)
                                                    break;

                                                CruiserService.printCruiser((Cruiser) ship);
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

                                                List<Cruiser> cruisers1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    cruisers1.add(cruisers.get(Integer.parseInt(s) - 1));

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

                                                cruisers1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    cruisers1.add(cruisers.get(Integer.parseInt(s) - 1));

                                                CruiserService.printLeastPassengerCount(cruisers1);
                                                break;
                                            case 4:
                                                ship = chooseShip(cruisers, "Choose an object");
                                                if (ship == null)
                                                    break;

                                                System.out.println("Write the path to the file.");
                                                String path = scanner.next();

                                                CruiserService.writeToFile((Cruiser) ship, path);
                                                break;
                                            case 5:
                                                Cruiser cruiser = CruiserService.createCruiser();

                                                cruisers.add(cruiser);
                                                break;
                                            case 6:
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                System.out.println();
                                                Cruiser toMove = (Cruiser) chooseShip(cruisers, "Choose The Ship You Want To Move:");
                                                if (toMove == null)
                                                    break;

                                                System.out.println("Choose The City You Want To Move The Ship To:");
                                                List<City> excludedCities = new ArrayList<>();
                                                excludedCities.add(toMove.getCurrentPos().getPlacement());
                                                excludedCities.addAll(map.getDisconnectedCities(toMove.getCurrentPos().getPlacement()));

                                                ArrayList<City> presentCities = City.printPlaces(toMove.getCurrentPos().getPlacement(), excludedCities);
                                                int placeNum = scanner.nextInt();
                                                if (placeNum == presentCities.size() + 1)
                                                    break;

                                                City city = presentCities.get(placeNum - 1);
                                                System.out.println("Choose The Dock Of The City (You Can Choose Any Type Of Dock):");
                                                int j = City.printDocksOfPlace(city);
                                                int dockNum = scanner.nextInt();
                                                if (dockNum == j)
                                                    break;

                                                if (city.getReceptions().get(dockNum - 1) != null)
                                                    toMove.moveTo(city.getReceptions().get(dockNum - 1));
                                                else
                                                    toMove.moveTo(city.getReceptions().get(dockNum));
                                                System.out.println("The Ship " + toMove.getName() + " Has Moved To The City Of " + city + ".");
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                break;
                                            case 7:
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                System.out.println();
                                                Cruiser toTransport = (Cruiser) chooseShip(cruisers, "Choose The Ship You Want To Transport People With:");
                                                if (toTransport == null)
                                                    break;

                                                System.out.println("Choose The City You Want To Move The Ship To:");
                                                ArrayList<City> touristDocks = DockService.printTouristDocks(toTransport.getCurrentPos().getPlacement());
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
                                                System.out.println("Current Time: " + sdf.format(time.getTime()));
                                                break;
                                            case 8:
                                                cruiser = (Cruiser) chooseShip(cruisers, "Choose The Ship You Want To Upgrade:");
                                                if (cruiser == null)
                                                    break;

                                                cruiser.upgrade();
                                                System.out.println("The Ship " + cruiser.getName() + " Has Been Upgraded:");
                                                CruiserService.printCruiser(cruiser);
                                                break;
                                            case 9:
                                                System.out.println("Going Back.");
                                                isMethodsMenuActive = false;
                                                break;
                                        }
                                    }

                                    break;
                                case 3:
                                    System.out.println("To Fill The Ships, First Create A File With These Configurations: (Write each ship on 1 line)");
                                    System.out.println("-------------------- Make Sure To Write Those Cities Which Have The According Docks --------------------");
                                    System.out.println("CargoShip:City(Str)|Name(Str)|Captain(Str)|MaxSpeed(double)|CargoWeight(double)|MaxCargoWeight(double)");
                                    System.out.print("Cities With Cargo Docks Are - ");
                                    for (City city : City.values()) {
                                        if (city.getReceptions().get(0) != null)
                                            System.out.print(city + ", ");
                                    }
                                    System.out.println();
                                    System.out.println("Cruiser:City(Str)|Name(Str)|Captain(Str)|MaxSpeed(double)|PassengerCount(int)|MaxPassengerCount(int)|TicketCost(double)");
                                    System.out.print("Cities With Tourist Docks Are - ");
                                    for (City city : City.values()) {
                                        if (city.getReceptions().get(1) != null)
                                            System.out.print(city + ", ");
                                    }
                                    System.out.println();
                                    scanner.nextLine();
                                    System.out.print("Enter The Path To The File - ");
                                    String path = scanner.nextLine();
                                    List<String> data = Utils.readLines(path);
                                    fillShips(data);
                                    break;
                                case 4:
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
                                        System.out.println("Choose a function:\n1.Print A Cargo Dock\n2.Print Cargo Dock With The Most Cargo Weight\n" +
                                                "3.Print The Docked Cargo Ship With The Biggest Max Weight\n4.Write A Cargo Dock To File\n5.Go Back");
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

                                                List<CargoDock> cargoDocks1 = new ArrayList<>();
                                                for (String s : numsStr)
                                                    cargoDocks1.add((CargoDock) cargoDocks.get(Integer.parseInt(s) - 1).getReceptions().get(0));

                                                CargoDockService.printBiggestCargoWeight(cargoDocks1);
                                                break;
                                            case 3:
                                                System.out.println("Choose an object.");
                                                List<City> cities = DockService.printCargoDocks();

                                                objNum = scanner.nextInt();

                                                if (objNum == cities.size() + 1)
                                                    break;

                                                CargoDockService.printDockedCargoShipBiggestMaxWeight((CargoDock) cities.get(objNum - 1).getReceptions().get(0));
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
                                        System.out.println("Choose a function:\n1.Print A Tourist Dock\n2.Print The Name Of The Tourist Dock With The Least Number Of People\n" +
                                                "3.Print Tourist Docks in Ascending Order By The Number Of Max People\n4.Write A Tourist Dock To File\n5.Go Back");
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

                                                List<TouristDock> touristDocks1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    touristDocks1.add((TouristDock) touristDocks.get(Integer.parseInt(s) - 1).getReceptions().get(1));

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

                                                touristDocks1 = new ArrayList<>(numsStr.length);
                                                for (String s : numsStr)
                                                    touristDocks1.add((TouristDock) touristDocks.get(Integer.parseInt(s) - 1).getReceptions().get(1));

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

    private static boolean authorization() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean hasLogOn = false;

        while (!hasLogOn) {
            System.out.println("Login Or Register\n1.Login\t2.Register\t3.Exit");

            int n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter these fields");
                    System.out.print("Username - ");

                    sc.nextLine();

                    String loginUsername = sc.next();
                    System.out.print("Password - ");
                    String loginPassword = sc.next();

                    if (db.login(loginUsername, loginPassword)) {
                        System.out.println("Login Successful!");
                        hasLogOn = true;
                    } else
                        System.out.println("The User Does Not Exist.");

                    break;
                case 2:
                    System.out.println("Enter these fields");
                    System.out.print("Full Name (Name Surname) - ");

                    sc.nextLine();
                    String fullName = sc.nextLine();
                    while (!db.validateName(fullName)) {
                        System.out.println("You Entered Full Name Incorrectly!");
                        System.out.print("Full Name (Name Surname) - ");
                        fullName = sc.nextLine();
                    }
                    System.out.print("Username (More Than 10 Characters) - ");

                    String username = sc.next();
                    while (!db.validateUsername(username)) {
                        System.out.print("Username (More Than 10 Characters) - ");
                        username = sc.next();
                    }
                    System.out.print("Email - ");

                    String email = sc.next();
                    while (!db.validateEmail(email)) {
                        System.out.println("Your Input Is Not An Email!");
                        System.out.print("Email - ");
                        email = sc.next();
                    }
                    System.out.print("Password (More Than 8 Characters, 2 Uppercase, 3 Numbers) - ");

                    String password = sc.next();
                    while (!db.validatePassword(password)) {
                        System.out.println("You Entered Password Incorrectly!");
                        System.out.print("Password (More Than 8 Characters, 2 Uppercase, 3 Numbers) - ");
                        password = sc.next();
                    }

                    if (!db.register(fullName, username, email, password))
                        System.out.println("Could Not Register");
                    else
                        System.out.println("Registered Successfully!");
                    break;
                case 3:
                    System.out.println("Exiting");
                    return false;
            }
        }
        return true;
    }

    private static Ship chooseShip(List<? extends Ship> ships, String text) {
        System.out.println(text);
        ShipService.printShips(ships);
        int objNum = new Scanner(System.in).nextInt();
        if (objNum == ships.size() + 1)
            return null;

        return ships.get(objNum - 1);
    }

    private static void fillShips(List<String> data) {
        for (String line : data) {
            String[] split = line.split(":");
            String type = split[0].toLowerCase();
            String[] info = split[1].split("\\|");
            Ship ship;
            switch (type) {
                case "cargoship":
                    ship = new CargoShip((CargoDock) City.valueOf(info[0].toUpperCase()).getReceptions().get(0), info[1],
                            info[2], Double.parseDouble(info[3]), Double.parseDouble(info[4]), Double.parseDouble(info[5]));
                    cargoShips.add((CargoShip) ship);
                    break;
                case "cruiser":
                    ship = new Cruiser((TouristDock) City.valueOf(info[0].toUpperCase()).getReceptions().get(1), info[1],
                            info[2], Double.parseDouble(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]),
                            Double.parseDouble(info[6]));
                    cruisers.add((Cruiser) ship);
                    break;
            }
        }
    }
}
