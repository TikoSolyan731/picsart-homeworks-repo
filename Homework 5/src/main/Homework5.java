package main;

import reception.waterReception.Dock;
import transport.waterTransport.Ship;

public class Homework5 {
    public static void main(String[] args) {
        Dock d1 = new Dock("Barcelona", 4500);
        Dock d2 = new Dock("Madrid", 6000);
        Ship sh1 = new Ship(d1, "bla bla1");

        System.out.println(sh1.getCurrentPos().getName());

        sh1.setCargoWeight(400);
        System.out.println(sh1.empty());
        System.out.println(sh1.getCargoWeight());
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
        System.out.println();
        System.out.println("---------checking transport-----------");
        sh1.setCargoWeight(1000);

        System.out.println(sh1.getCargoWeight());
        sh1.transport(800, d2, d1);
        System.out.println("sh1 cargo weight: " + sh1.getCargoWeight());
        System.out.println("d1 cargo: " + d1.getCurrentCargoWeight());
        System.out.println("d2 cargo: " + d2.getCurrentCargoWeight());

        System.out.println("Dumping the rest to the dock " + sh1.getCurrentPos().getName());
        sh1.emptyToDock();
        System.out.println("d1 cargo: " + d1.getCurrentCargoWeight());
        System.out.println(sh1.getCargoWeight());
        sh1.takeFromDock(3500);
        System.out.println(d1.getCurrentCargoWeight());
        sh1.move(d1, d2);
        sh1.emptyToDock();
        System.out.println(d2.getCurrentCargoWeight());

        System.out.println("-----------upgrading----------");
        d2.upgrade();
        System.out.println(d2.getMaxDockedShips());
        for (Ship sh : d2.getDockedShips()) {
            if (sh != null)
                System.out.println(sh.getName());
        }
    }
}
