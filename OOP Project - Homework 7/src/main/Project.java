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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project {
    static CargoDock d1 = new CargoDock("Barcelona", 5, 4500);
    static CargoDock d2 = new CargoDock("Madrid", 6, 6000);
    static TouristDock d3 = new TouristDock("Cuba", 5, 420, 1000);
    static TouristDock d4 = new TouristDock("Bali", 6);

    static CargoShip sh1 = new CargoShip(d1, "bla bla1");
    static CargoShip sh2 = new CargoShip(d2, "bla bla2");
    static Cruiser sh3 = new Cruiser(d2, "Santa Maria");
    static Cruiser sh4 = new Cruiser(d1, "Santa Luchia");
    static Cruiser sh5 = new Cruiser(d2, "Santa Barbara");

    static CargoShip[] cargos = {sh1, sh2};
    static Cruiser[] cruisers = {sh3, sh4, sh5};

    static CargoDock[] cargoDocks = {d1, d2};
    static TouristDock[] touristDocks = {d3, d4};

    static final String PATH = "cargo.txt";

    public static void main(String[] args) {
        //          Transport (interface)                             Reception (interface)
        //               /   |     \                                      /      |      \
        //CargoTransport    Ship     TouristTransport        CargoReception    Dock     TouristReception
        //      \          /   \      /                            \           /   \        /
        //        CargoShip     Cruiser                              CargoDock       TouristDock
        //  Architecture
        // I have used lists and Field,Method,Class classes for a dynamic menu.
        // It gets generated from the static fields above.

        // Part 1. The main classes and functionality.

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

//        CargoShipService.writeToFile(sh1, PATH);
//        CargoShipService.writeToFile(sh2, PATH);

        // Part 4. Menu Implementation.
        try {
            menu();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static void menu() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Declare the objects you want to work with and the file path as static fields. Press 1 to continue, 2 to quit.");

        Field[] fields = Project.class.getDeclaredFields();
        List<Field> staticFields = new ArrayList<>();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                staticFields.add(field);
            }
        }

        List<Field> cargoShips = getFieldsWithType("CargoShip", staticFields);
        List<Field> cruisers = getFieldsWithType("Cruiser", staticFields);
        List<Field> cargoDocks = getFieldsWithType("CargoDock", staticFields);
        List<Field> touristDocks = getFieldsWithType("TouristDock", staticFields);

        List<Field> cargoShipsArray = getFieldsWithType("CargoShip[]", staticFields);
        List<Field> cruisersArray = getFieldsWithType("Cruiser[]", staticFields);
        List<Field> cargoDocksArray = getFieldsWithType("CargoDock[]", staticFields);
        List<Field> touristDocksArray = getFieldsWithType("TouristDock[]", staticFields);

        switch (scanner.nextInt()) {
            case 1:
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
                                        boolean isMethodsMenuActive = true;

                                        while (isMethodsMenuActive) {
                                            System.out.println("Choose a function:\n1.printCargoShip\n2.printNameBiggestMaxCargo\n" +
                                                    "3.printLowestCargo\n4.writeToFile\n5.Go Back");
                                            int funcNum = scanner.nextInt();

                                            boolean isObjectMenuActive = true;
                                            switch (funcNum) {
                                                case 1:
                                                    selectAndDealWithObject(cargoShips, CargoShipService.class, "printCargoShip",
                                                            CargoShip.class, isObjectMenuActive);
                                                    break;
                                                case 2:
                                                    selectAndDealWithObject(cargoShipsArray, CargoShipService.class, "printNameBiggestMaxCargo",
                                                            CargoShip[].class, isObjectMenuActive);
                                                    break;
                                                case 3:
                                                    selectAndDealWithObject(cargoShipsArray, CargoShipService.class, "printLowestCargo",
                                                            CargoShip[].class, isObjectMenuActive);
                                                    break;
                                                case 4:
                                                    selectAndDealWithObject(cargoShips, CargoShipService.class, "writeToFile",
                                                            CargoShip.class, isObjectMenuActive);
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
                                            System.out.println("Choose a function:\n1.printCruiser\n2.printTicketCostAscending\n" +
                                                    "3.printLeastPassengerCount\n4.writeToFile\n5.Go Back");
                                            int funcNum = scanner.nextInt();

                                            boolean isObjectMenuActive = true;
                                            switch (funcNum) {
                                                case 1:
                                                    selectAndDealWithObject(cruisers, CruiserService.class, "printCruiser",
                                                            Cruiser.class, isObjectMenuActive);
                                                    break;
                                                case 2:
                                                    selectAndDealWithObject(cruisersArray, CruiserService.class, "printTicketCostAscending",
                                                            Cruiser[].class, isObjectMenuActive);
                                                    break;
                                                case 3:
                                                    selectAndDealWithObject(cruisersArray, CruiserService.class, "printLeastPassengerCount",
                                                            Cruiser[].class, isObjectMenuActive);
                                                    break;
                                                case 4:
                                                    selectAndDealWithObject(cruisers, CruiserService.class, "writeToFile",
                                                            Cruiser.class, isObjectMenuActive);
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


                                            boolean isObjectMenuActive = true;
                                            switch (funcNum) {
                                                case 1:
                                                    selectAndDealWithObject(cargoDocks, CargoDockService.class, "printCargoDock",
                                                            CargoDock.class, isObjectMenuActive);
                                                    break;
                                                case 2:
                                                    selectAndDealWithObject(cargoDocksArray, CargoDockService.class, "printBiggestCargoWeight",
                                                            CargoDock[].class, isObjectMenuActive);
                                                    break;
                                                case 3:
                                                    selectAndDealWithObject(cargoDocks, CargoDockService.class, "printDockedCargoShipBiggestMaxWeight",
                                                            CargoDock.class, isObjectMenuActive);
                                                    break;
                                                case 4:
                                                    selectAndDealWithObject(cargoDocks, CargoDockService.class, "writeToFile",
                                                            CargoDock.class, isObjectMenuActive);
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

                                            boolean isObjectMenuActive = true;
                                            switch (funcNum) {
                                                case 1:
                                                    selectAndDealWithObject(touristDocks, TouristDockService.class, "printTouristDock",
                                                            TouristDock.class, isObjectMenuActive);
                                                    break;
                                                case 2:
                                                    selectAndDealWithObject(touristDocksArray, TouristDockService.class, "printNameLeastPeopleCount",
                                                            TouristDock[].class, isObjectMenuActive);
                                                    break;
                                                case 3:
                                                    selectAndDealWithObject(touristDocksArray, TouristDockService.class, "printByMaxPeopleCountAscending",
                                                            TouristDock[].class, isObjectMenuActive);
                                                    break;
                                                case 4:
                                                    selectAndDealWithObject(touristDocks, TouristDockService.class, "writeToFile",
                                                            TouristDock.class, isObjectMenuActive);
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
                break;
            case 2:
                System.out.println("Quiting.");
                break;
        }
    }

    static List<Field> getFieldsWithType(String type, List<Field> fields) {
        List<Field> desiredFields = new ArrayList<>();

        for (Field f : fields) {
            if (f.getType().getTypeName().endsWith(type)) {
                desiredFields.add(f);
            }
        }
        return desiredFields;
    }

    static void printAvailableObjects(List<Field> objects) {
        System.out.println("Choose an object:");
        int i = 1;
        for (Field f : objects) {
            System.out.println(i + "." + f.getName());
            i++;
        }
        System.out.println("Press " + (objects.size() + 1) + " to go back.");
    }

    static void selectAndDealWithObject(List<Field> fields, Class<?> service, String function, Class<?> cast, boolean bool) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        while (bool) {
            printAvailableObjects(fields);
            int objNum = new Scanner(System.in).nextInt();

            if (objNum > 0 && objNum <= fields.size()) {
                if (function.equals("writeToFile"))
                    service.getDeclaredMethod(function, cast, String.class).invoke(null, fields.get(objNum - 1).get(null), PATH);
                else
                    service.getDeclaredMethod(function, cast).invoke(null, fields.get(objNum - 1).get(null));
            }
            else if (objNum == fields.size() + 1) {
                System.out.println("Going Back.");
                bool = false;
            }
            else
                System.out.println("No Object");
        }
    }
}
