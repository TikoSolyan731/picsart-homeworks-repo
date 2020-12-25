package transport.waterTransport;

import reception.AbstractReception;
import reception.City;
import reception.Map;
import reception.Reception;
import reception.waterReception.Dock;
import transport.Transport;

import java.util.Calendar;
import java.util.Date;

public abstract class Ship implements Transport {
    private Dock currentPos;
    private String name;
    private String captain;
    private double maxSpeed = 55;

    public Ship(Dock currentPos, String name) {
        this.currentPos = currentPos;
        this.currentPos.dockShip(this);
        this.name = name;
    }

    @Override
    public void moveTo(AbstractReception to) {
        System.out.println("Moving The Ship " + getName() + " From " + this.currentPos.getPlacement() + " to " + to.getPlacement());
        this.currentPos.undockShip(this);
        this.currentPos = (Dock) to;
        this.currentPos.dockShip(this);
    }

    @Override
    public void moveTo(AbstractReception to, Calendar time) {
        System.out.println("Moving The Ship " + getName() + " From " + this.currentPos.getPlacement() + " to " + to.getPlacement());
        City from = currentPos.getPlacement();
        this.currentPos.undockShip(this);
        this.currentPos = (Dock) to;
        if (this.currentPos.dockShip(this)) {
            Map map = Map.getInstance();
            Date date = time.getTime();
            Date newDate = new Date((long) (date.getTime() + (map.getEdge(from, to.getPlacement()) / getMaxSpeed() * 3600000)));
            time.setTime(newDate);
        }
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
}
