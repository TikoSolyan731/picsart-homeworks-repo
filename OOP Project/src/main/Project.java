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
    public static void main(String[] args) throws IOException {
        //          Transport (interface)                                  Reception (interface)
        //              |     |      |                                    /         |            \
        //             |      |      |                                  /     AbstractReception   \
        //            /      |       \                                /           |                \
        //CargoTransport    Ship     TouristTransport        CargoReception    Dock     TouristReception
        //      \          /   \      /                            \           /   \        /
        //        CargoShip     Cruiser                              CargoDock       TouristDock
        //  Architecture

        // Description: This project has 2 main interfaces - Transport and Reception. First acts as a transport which can be ships, airplanes, etc. The Second acts as a
        // reception point for those classes which can be docks, airports, etc. This project has a map system, which is basically a graph of Cities, which is an enum type.
        // The Cities have 1 reception of each created type and are initialized by me. They cannot be modified or created. The only
        // classes that the user can create are the classes implemented by the Transport interface. The user can create, upgrade, move
        // the transports and/or use its according service's methods. The movement is strictly restricted by the map system, by which
        // a transport can move to another city if and only if the current city is connected to that other city. Although, the map is a graph,
        // it is undirected, and a very simple one (No complex path-finding algorithms, like Dijkstra's were used). I have only implemented the water transport and
        // water reception classes, but the rest of the types are easy to write, as the whole architecture is laid out for them.

        // Part 1. The main classes and functionality.

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
