package transport.waterTransport;

import reception.waterReception.Dock;
import transport.TransportInt;

public class Ship implements WaterTransport, TransportInt {
    private Dock currentPos;
    private String name = "";
    private double maxSpeed = 55;
    private double cargoWeight = 0;
    private double maxCargoWeight = 3500;
    private int crewMembersCount = 25;
    private String captain = "";

    public Ship(Dock currentPos, String name) {
        this.currentPos = currentPos;
        this.currentPos.dockShip(this);
        this.name = name;
    }

    public Ship(Dock currentPos, String name, double maxSpeed, double cargoWeight, double maxCargoWeight, int crewMembersCount, String captain) {
        this.currentPos = currentPos;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxCargoWeight = maxCargoWeight;
        if (cargoWeight <= this.maxCargoWeight)
            this.cargoWeight = cargoWeight;
        else
            this.cargoWeight = 0;

        this.crewMembersCount = crewMembersCount;
        this.captain = captain;
    }

    @Override
    public void move(Dock from, Dock to) {
        if (currentPos == from) {
            System.out.println("Moving The Ship From " + from.getName() + " to " + to.getName());
            currentPos.undockShip(this);
            currentPos = to;
            currentPos.dockShip(this);
        } else
            System.out.println("You cannot move the ship from a place other than the current position.");
    }

    @Override
    public void transport(double weight, Dock from, Dock to) {
        if (weight > from.getCurrentCargoWeight() || weight + getCargoWeight() > maxCargoWeight) {
            System.out.println("Cannot transport");
            return;
        }
        System.out.println("Transporting " + weight + " cargo from " + from.getName() + " to " + to.getName());
        from.sendCargo(weight, this, to);
        this.move(from, to);
        currentPos.acceptCargo(weight, this, from);
    }

    @Override
    public void takeFromDock(double weight) {
        if (weight > currentPos.getCurrentCargoWeight() || weight + getCargoWeight() > maxCargoWeight) {
            System.out.println("Cannot take cargo.");
            return;
        }

        System.out.println("Taking " + weight + " cargo from current dock.");
        currentPos.setCurrentCargoWeight(currentPos.getCurrentCargoWeight() - weight);
        cargoWeight += weight;
    }

    @Override
    public void emptyToDock() {
        if (cargoWeight <= 0) {
            System.out.println("Already Empty.");
            return;
        }

        System.out.println("Emptying the " + cargoWeight + " weight to the current dock.");
        currentPos.setCurrentCargoWeight(currentPos.getCurrentCargoWeight() + cargoWeight);
        cargoWeight = 0;
    }

    @Override
    public double empty() {
        System.out.println("Emptying the ship " + name + ".");
        double temp = cargoWeight;
        cargoWeight = 0;
        return temp;
    }

    @Override
    public void upgrade() {
        System.out.println("Upgrading the ship " + name + ".");
        maxCargoWeight += 2000;
        maxSpeed += 5;
    }

    public Dock getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Dock currentPos) {
        this.currentPos = currentPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        if (cargoWeight <= this.maxCargoWeight)
            this.cargoWeight = cargoWeight;
        else
            System.out.println("Exceeding the max cargo weight.");
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public int getCrewMembersCount() {
        return crewMembersCount;
    }

    public void setCrewMembersCount(int crewMembersCount) {
        this.crewMembersCount = crewMembersCount;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
