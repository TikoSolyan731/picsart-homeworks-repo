package main;

import menu.Menu;
import reception.City;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;
import services.waterTransportServices.CargoShipService;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Cruiser;
import transport.waterTransport.Ship;

import java.io.IOException;

public class Project {
    static final String PATH = "classes.txt";

    public static void main(String[] args) throws IOException {
        //          Transport (interface)                             Reception (interface)
        //               /   |     \                                      /      |      \
        //CargoTransport    Ship     TouristTransport        CargoReception    Dock     TouristReception
        //      \          /   \      /                            \           /   \        /
        //        CargoShip     Cruiser                              CargoDock       TouristDock
        //  Architecture

        // Part 1. The main classes and functionality.

//        CargoDock d1 = new CargoDock(City., 5, 4500);
//        CargoDock d2 = new CargoDock(City., 6, 6000);
//        TouristDock d3 = new TouristDock(City.RIO_DE_JANEIRO, 5, 420, 1000);
//        TouristDock d4 = new TouristDock(City.SINGAPORE, 6);
        TouristDock sydTourist = (TouristDock) City.SYDNEY.getReceptions().get(1);
        CargoDock roterCargo = (CargoDock) City.ROTTERDAM.getReceptions().get(0);


        CargoShip sh1 = new CargoShip(roterCargo, "bla bla1");
        Ship[] dockedShips = roterCargo.getDockedShips();
        CargoShip sh2 = new CargoShip(roterCargo, "bla bla2");
        Cruiser sh3 = new Cruiser(sydTourist, "Santa Maria");
        Cruiser sh4 = new Cruiser(sydTourist, "Santa Luchia");



        // Part 2. Services for models.

        CargoShipService.printCargoShip(sh1);
        System.out.println();

        sh1.setMaxCargoWeight(1000);
        sh2.setMaxCargoWeight(2000);
        //CargoShipService.printNameBiggestMaxCargo(cargos);

        sh1.setCargoWeight(120);
        sh2.setCargoWeight(220);
        //CargoShipService.printLowestCargo(cargos);

        // Part 3. Writing to file.

//        CargoShipService.writeToFile(sh1, PATH);
//        CargoShipService.writeToFile(sh2, PATH);

        // Part 4. Menu Implementation.
        Menu.start();
    }
}
