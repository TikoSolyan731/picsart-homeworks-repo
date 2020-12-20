package transport.waterTransport;

import reception.TouristReception;
import reception.waterReception.TouristDock;
import transport.TouristTransport;

public class Cruiser extends Ship implements TouristTransport {
    private int passengerCount = 0;
    private int maxPassengerCount = 500;
    private double ticketCost = 5500;

    public Cruiser(TouristDock currentPos, String name) {
        super(currentPos, name);
    }

    public Cruiser(TouristDock currentPos, String name, int maxPassengerCount, double ticketCost) {
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

            System.out.println("Transporting " + peopleCount + " tourists from " + tempFrom.getPlacement() + " to " + tempTo.getPlacement());
            tempFrom.sendPeople(peopleCount, this, to);
            this.moveTo(tempTo);
            tempTo.acceptPeople(peopleCount, this, from);
        } else
            System.out.println("Transportation allowed only from Cargo Dock to other Cargo Dock.");
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
