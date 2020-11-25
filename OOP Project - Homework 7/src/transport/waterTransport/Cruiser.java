package transport.waterTransport;

import reception.TouristReception;
import reception.waterReception.CargoDock;
import reception.waterReception.Dock;
import reception.waterReception.TouristDock;
import transport.TouristTransport;

public class Cruiser extends Ship implements TouristTransport {
    private int passengerCount = 0;
    private int maxPassengerCount = 500;
    private double ticketCost = 5500;

    public Cruiser(CargoDock currentPos, String name) {
        super(currentPos, name);
    }

    public Cruiser(CargoDock currentPos, String name, int maxPassengerCount, double ticketCost) {
        super(currentPos, name);
        this.maxPassengerCount = maxPassengerCount;
        this.ticketCost = ticketCost;
    }

    @Override
    public void transport(int peopleCount, TouristReception from, TouristReception to) {
        if (from instanceof TouristDock && to instanceof TouristDock) {
            TouristDock tempFrom = (TouristDock) from;
            TouristDock tempTo = (TouristDock) to;

            if (peopleCount + getPassengerCount() > getMaxPassengerCount()
                    || !getCurrentPos().equals(from)) {
                System.out.println("Cannot transport");
                return;
            }

            System.out.println("Transporting " + peopleCount + " tourists from " + tempFrom.getName() + " to " + tempTo.getName());
            tempFrom.sendPeople(peopleCount, this, to);
            this.move(tempFrom, tempTo);
            tempTo.acceptPeople(peopleCount, this, from);
        } else
            System.out.println("Transportation allowed only from Cargo Dock to other Cargo Dock.");
    }

    @Override
    public void move(Dock from, Dock to) {
        if (getCurrentPos() == from) {
            System.out.println("Moving The Cruiser " + getName() + " From " + from.getName() + " to " + to.getName());
            getCurrentPos().undockShip(this);
            setCurrentPos(to);
            getCurrentPos().dockShip(this);
        } else
            System.out.println("You cannot move the ship from a place other than the current position.");
    }

    @Override
    public void upgrade() {
        System.out.println("Upgrading the ship " + getName() + ".");
        setMaxPassengerCount(getMaxPassengerCount() + 100);
        setTicketCost(getTicketCost() + 599.9);
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
