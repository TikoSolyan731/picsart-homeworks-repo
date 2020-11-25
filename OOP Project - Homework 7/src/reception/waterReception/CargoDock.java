package reception.waterReception;

import reception.CargoReception;
import reception.Reception;
import transport.CargoTransport;
import transport.waterTransport.CargoShip;
import transport.waterTransport.Ship;

import java.util.Arrays;

public class CargoDock extends Dock implements CargoReception {
    private double currentCargoWeight = 0;

    public CargoDock(String name, int maxDockedShips) {
        super(name, maxDockedShips);
    }

    public CargoDock(String name, int maxDockedShips, double currentCargoWeight) {
        super(name, maxDockedShips);
        this.currentCargoWeight = currentCargoWeight;
    }

    @Override
    public void dockShip(Ship ship) {
        //System.out.println("Docking a ship at Cargo Dock " + getName() + ".");
        for (int i = 0; i < getDockedShips().length; i++) {
            if (getDockedShips()[i] != null)
                continue;

            getDockedShips()[i] = ship;
            setDockedShipsCount(getDockedShipsCount() + 1);
            return;
        }

        System.out.println("The Cargo Dock is full.");
    }

    @Override
    public void undockShip(Ship ship) {
        //System.out.println("Undocking the ship " + ship.getName() + " from Cargo Dock " + getName() + ".");

        for (int i = 0; i < getDockedShips().length; i++) {
            if (getDockedShips()[i] == ship) {
                getDockedShips()[i] = null;
                return;
            }
        }

        System.out.println("There is no cargoShip " + ship.getName() + " at the dock of " + getName());
    }

    @Override
    public void acceptCargo(double weight, CargoTransport cargoTransport, CargoReception from) {
        CargoShip cargoShip = (CargoShip) cargoTransport;
        CargoDock tempFrom = (CargoDock) from;

        System.out.println("Receiving Cargo of weight " + weight + " from " + tempFrom.getName() + " by the cargoShip " +
                cargoShip.getName() + ".");
        currentCargoWeight += weight;
        cargoShip.setCargoWeight(cargoShip.getCargoWeight() - weight);
    }

    @Override
    public void sendCargo(double weight, CargoTransport cargoTransport, CargoReception to) {
        CargoShip cargoShip = (CargoShip) cargoTransport;
        CargoDock tempTo = (CargoDock) to;

        if (cargoShip.getCargoWeight() + weight > cargoShip.getMaxCargoWeight()) {
            System.out.println("Exceeding Max Weight of the CargoShip.");
            return;
        }

        System.out.println("Sending Cargo of weight " + weight + " to " + tempTo.getName() + " by the cargoShip " +
            cargoShip.getName() + ".");
        currentCargoWeight -= weight;
        cargoShip.setCargoWeight(cargoShip.getCargoWeight() + weight);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        System.out.println("Upgraded the Cargo Dock " + getName() + ".");
    }

    public double getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public void setCurrentCargoWeight(double currentCargoWeight) {
        this.currentCargoWeight = currentCargoWeight;
    }
}
