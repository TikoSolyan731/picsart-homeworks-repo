package main;

import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import services.waterTransportServices.CargoShipService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;
import transport.waterTransport.Ship;

public class Project {
    public static void main(String[] args) {
        // Part 1. The main classes and functionality.
        // In the future I will create implementations for airplanes and airports.

        CargoDock d1 = new CargoDock("Barcelona", 5, 4500);
        CargoDock d2 = new CargoDock("Madrid", 6, 6000);
        TouristDock d3 = new TouristDock("Cuba", 5, 420, 1000);
        TouristDock d4 = new TouristDock("Bali", 6);

        CargoShip sh1 = new CargoShip(d1, "bla bla1");
        CargoShip sh2 = new CargoShip(d2, "bla bla2");
        Cruiser sh3 = new Cruiser(d2, "Santa Maria");

        CargoShip[] cargos = {sh1, sh2};

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

        CargoShipService.writeToFile(sh1);
        CargoShipService.writeToFile(sh2);
    }
}
