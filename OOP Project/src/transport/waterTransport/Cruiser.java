package transport.waterTransport;

import reception.TouristReception;
import reception.waterReception.Dock;
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

    public Cruiser(Dock currentPos, String name, String captain, double maxSpeed, int passengerCount, int maxPassengerCount, double ticketCost) {
        super(currentPos, name, captain, maxSpeed);
        this.passengerCount = passengerCount;
        this.maxPassengerCount = maxPassengerCount;
        this.ticketCost = ticketCost;
    }

    @Override
    public boolean transport(int peopleCount, TouristReception to) {
        if (getCurrentPos() instanceof TouristDock && to instanceof TouristDock) {
            TouristDock tempFrom = (TouristDock) getCurrentPos();
            TouristDock tempTo = (TouristDock) to;

            if (peopleCount > tempFrom.getCurrentPeopleCount()) {
                System.out.println("Cannot Transport - The Inputted Number Of People Is Over The Current Number At The Dock.");
                return false;
            }
            if (peopleCount + getPassengerCount() > getMaxPassengerCount()) {
                System.out.println("Cannot Transport - The Number Of People Is Over The Max Capacity Of The Ship.");
                return false;
            }

            System.out.println("Transporting " + peopleCount + " tourists from " + tempFrom.getPlacement() + " to " + tempTo.getPlacement());
            tempFrom.sendPeople(peopleCount, this, to);
            this.moveTo(tempTo);
            tempTo.acceptPeople(peopleCount, this, tempFrom);

            return true;
        }

        System.out.println("Transportation allowed only from Tourist Dock to other Tourist Dock.");
        return false;
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
