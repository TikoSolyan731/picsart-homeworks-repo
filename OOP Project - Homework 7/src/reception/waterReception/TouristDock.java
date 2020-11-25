package reception.waterReception;

import reception.TouristReception;
import transport.TouristTransport;
import transport.waterTransport.Cruiser;
import transport.waterTransport.Ship;

public class TouristDock extends Dock implements TouristReception {
    private int currentPeopleCount = 0;
    private int maxPeopleCount = 2000;

    public TouristDock(String name, int maxDockedShips) {
        super(name, maxDockedShips);
    }

    public TouristDock(String name, int maxDockedShips, int currentPeopleCount, int maxPeopleCount) {
        super(name, maxDockedShips);
        this.currentPeopleCount = currentPeopleCount;
        this.maxPeopleCount = maxPeopleCount;
    }

    @Override
    public void acceptPeople(int peopleCount, TouristTransport touristTransport, TouristReception from) {
        Cruiser cruiser = (Cruiser) touristTransport;
        TouristDock tempFrom = (TouristDock) from;

        System.out.println("Receiving " + peopleCount + " tourists from " + tempFrom.getName() + " by the cruiser " +
                cruiser.getName() + ".");
        currentPeopleCount += peopleCount;
        cruiser.setPassengerCount(cruiser.getPassengerCount() - peopleCount);
    }

    @Override
    public void sendPeople(int peopleCount, TouristTransport touristTransport, TouristReception to) {
        Cruiser cruiser = (Cruiser) touristTransport;
        TouristDock tempTo = (TouristDock) to;

        if (cruiser.getPassengerCount() + peopleCount > cruiser.getMaxPassengerCount()) {
            System.out.println("Exceeding Max People Count of the Cruiser.");
            return;
        }

        System.out.println("Sending " + peopleCount + " tourists to " + tempTo.getName() + " by the cruiser " +
                cruiser.getName() + ".");
        currentPeopleCount -= peopleCount;
        cruiser.setPassengerCount(cruiser.getPassengerCount() + peopleCount);
    }

    @Override
    public void dockShip(Ship ship) {
        //System.out.println("Docking a ship at Tourist Dock " + getName() + ".");
        for (int i = 0; i < getDockedShips().length; i++) {
            if (getDockedShips()[i] != null)
                continue;

            getDockedShips()[i] = ship;
            setDockedShipsCount(getDockedShipsCount() + 1);
            return;
        }

        System.out.println("The Tourist Dock is full.");
    }

    @Override
    public void undockShip(Ship ship) {
        //System.out.println("Undocking the ship " + ship.getName() + " from Tourist Dock " + getName() + ".");
        for (int i = 0; i < getDockedShips().length; i++) {
            if (getDockedShips()[i] == ship) {
                getDockedShips()[i] = null;
                return;
            }
        }

        System.out.println("There is no cargoShip " + ship.getName() + " at the dock of " + getName());
    }

    @Override
    public void upgrade() {
        super.upgrade();
        System.out.println("Upgraded the Tourist Dock " + getName() + ".");
    }

    public int getCurrentPeopleCount() {
        return currentPeopleCount;
    }

    public void setCurrentPeopleCount(int currentPeopleCount) {
        this.currentPeopleCount = currentPeopleCount;
    }

    public int getMaxPeopleCount() {
        return maxPeopleCount;
    }

    public void setMaxPeopleCount(int maxPeopleCount) {
        this.maxPeopleCount = maxPeopleCount;
    }
}
