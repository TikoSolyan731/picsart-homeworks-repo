package reception.waterReception;

import reception.ReceptionInt;
import transport.waterTransport.Ship;

public class Dock implements ReceptionInt, DockInt {
    private String name = "";
    private double currentCargoWeight = 0;
    private int maxDockedShips = 4;
    private int dockedShipsCount = 0;
    private Ship[] dockedShips = new Ship[maxDockedShips];

    public Dock(String name, double currentCargoWeight) {
        this.name = name;
        this.currentCargoWeight = currentCargoWeight;
    }

    public Dock(String name, double currentCargoWeight, int maxDockedShips) {
        this.name = name;
        this.currentCargoWeight = currentCargoWeight;
        this.maxDockedShips = maxDockedShips;
        this.dockedShips = new Ship[maxDockedShips];
    }

    public void dockShip(Ship ship) {

        for (int i = 0; i < dockedShips.length; i++) {
            if (dockedShips[i] != null)
                continue;

            dockedShips[i] = ship;
            dockedShipsCount++;
            return;
        }
    }

    public void undockShip(Ship ship) {
        for (int i = 0; i < dockedShips.length; i++) {
            if (dockedShips[i] == ship) {
                dockedShips[i] = null;
                return;
            }
        }

        System.out.println("There is no ship " + ship.getName() + " at the dock of " + getName());
    }

    @Override
    public double acceptCargo(double weight, Ship ship, Dock from) {
        System.out.println("Receiving Cargo of weight " + weight + " from " + from.getName() + " by the ship " +
                ship.getName() + ".");
        currentCargoWeight += weight;
        ship.setCargoWeight(ship.getCargoWeight() - weight);

        return weight;
    }

    @Override
    public double sendCargo(double weight, Ship ship, Dock to) {
        if (ship.getCargoWeight() + weight > ship.getMaxCargoWeight()) {
            System.out.println("Exceeding Max Weight of the Ship.");
            return -1;
        }

        System.out.println("Sending Cargo of weight " + weight + " to " + to.getName() + " by the ship " +
            ship.getName() + ".");
        currentCargoWeight -= weight;
        ship.setCargoWeight(ship.getCargoWeight() + weight);

        return weight;
    }

    @Override
    public void upgrade() {
        int newMax = maxDockedShips + 5;
        Ship[] temp = new Ship[newMax];
        for (int i = 0; i < dockedShips.length; i++) {
            temp[i] = dockedShips[i];
        }

        maxDockedShips = newMax;
        dockedShips = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public void setCurrentCargoWeight(double currentCargoWeight) {
        this.currentCargoWeight = currentCargoWeight;
    }

    public Ship[] getDockedShips() {
        return dockedShips;
    }

    public void setDockedShips(Ship[] dockedShips) {
        this.dockedShips = dockedShips;
    }

    public int getMaxDockedShips() {
        return maxDockedShips;
    }

    public void setMaxDockedShips(int maxDockedShips) {
        this.maxDockedShips = maxDockedShips;
    }

    public int getDockedShipsCount() {
        return dockedShipsCount;
    }
}
