package transport.landTransport;

import transport.Transport;

public class Train extends Transport {
    private double routeLength;
    private boolean isElectric;
    private int passengerSeats;

    public Train() {
        super();
        routeLength = 500;
        isElectric = true;
        passengerSeats = 200;
    }

    public Train(double routeLength, boolean isElectric, int passengerSeats) {
        super();
        this.routeLength = routeLength;
        this.isElectric = isElectric;
        this.passengerSeats = passengerSeats;
    }

    public Train(double speed, double routeLength, boolean isElectric, int passengerSeats) {
        super(speed);
        this.routeLength = routeLength;
        this.isElectric = isElectric;
        this.passengerSeats = passengerSeats;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nRoute Length: " + getRouteLength() + "\tIs Electric: " + isElectric() +
                "\tPassenger Seats: " + getPassengerSeats();
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }
}
