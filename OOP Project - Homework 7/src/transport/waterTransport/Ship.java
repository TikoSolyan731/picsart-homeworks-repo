package transport.waterTransport;

import reception.waterReception.CargoDock;
import reception.waterReception.Dock;
import transport.Transport;

public abstract class Ship implements Transport {
    private Dock currentPos;
    private String name = "";
    private String captain = "";
    private double maxSpeed = 55;
    private int crewMembersCount = 25;

    public Ship(Dock currentPos, String name) {
        this.currentPos = currentPos;
        this.currentPos.dockShip(this);
        this.name = name;
    }

    public abstract void move(Dock from, Dock to);

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

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCrewMembersCount() {
        return crewMembersCount;
    }

    public void setCrewMembersCount(int crewMembersCount) {
        this.crewMembersCount = crewMembersCount;
    }
}
