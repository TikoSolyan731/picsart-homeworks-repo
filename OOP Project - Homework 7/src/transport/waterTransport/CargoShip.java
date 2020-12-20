package transport.waterTransport;

import reception.CargoReception;
import reception.waterReception.CargoDock;
import transport.CargoTransport;

public class CargoShip extends Ship implements CargoTransport {
    private double cargoWeight = 0;
    private double maxCargoWeight = 3500;

    public CargoShip(CargoDock currentPos, String name) {
        super(currentPos, name);
    }

    public CargoShip(CargoDock currentPos, String name, double cargoWeight, double maxCargoWeight) {
        super(currentPos, name);
        this.cargoWeight = cargoWeight;
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public void transport(double weight, CargoReception from, CargoReception to) {
        if (from instanceof CargoDock && to instanceof CargoDock) {
            CargoDock tempFrom = (CargoDock) from;
            CargoDock tempTo = (CargoDock) to;

            if (weight > tempFrom.getCurrentCargoWeight() || weight + getCargoWeight() > getMaxCargoWeight()
                    || !getCurrentPos().equals(from)) {
                System.out.println("Cannot transport");
                return;
            }

            System.out.println("Transporting " + weight + " cargo from " + tempFrom.getPlacement() + " to " + tempTo.getPlacement());
            tempFrom.sendCargo(weight, this, to);
            this.moveTo(tempTo);
            tempTo.acceptCargo(weight, this, from);
        } else
            System.out.println("Transportation allowed only from Cargo Dock to other Cargo Dock.");
    }


    @Override
    public void takeFromCurrentPos(double weight) {
        if (getCurrentPos() instanceof CargoDock) {
            CargoDock curPos = (CargoDock) getCurrentPos();

            if (weight > curPos.getCurrentCargoWeight() || weight + getCargoWeight() > getMaxCargoWeight()) {
                System.out.println("Cannot take cargo.");
                return;
            }

            System.out.println("Taking " + weight + " cargo from current dock.");
            curPos.setCurrentCargoWeight(curPos.getCurrentCargoWeight() - weight);
            setCargoWeight(getCargoWeight() + weight);
        } else
            System.out.println("Currently not in a Cargo Dock.");
    }

    @Override
    public double emptyToCurrentPos() {
        if (getCurrentPos() instanceof CargoDock) {
            CargoDock curPos = (CargoDock) getCurrentPos();

            if (getCargoWeight() <= 0) {
                System.out.println("Already Empty.");
                return 0;
            }

            double temp = getCargoWeight();
            System.out.println("Emptying the " + getCargoWeight() + " weight to the current dock.");
            curPos.setCurrentCargoWeight(curPos.getCurrentCargoWeight() + getCargoWeight());
            setCargoWeight(0);

            return temp;
        } else {
            System.out.println("Currently not in a Cargo Dock.");
            return 0;
        }
    }

    @Override
    public double empty() {
        System.out.println("\nEmptying the ship " + getName() + ".");
        double temp = getCargoWeight();
        setCargoWeight(0);
        return temp;
    }

    @Override
    public void upgrade() {
        System.out.println("Upgrading the ship " + getName() + ".");
        setMaxCargoWeight(getMaxCargoWeight() + 2000);
        setMaxSpeed(getMaxSpeed() + 5);
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }
}
