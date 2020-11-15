package transport.landTransport;

public class Metro extends Train {
    private boolean hasWifi;
    private int passingStations;
    private String startingStation;

    public Metro() {
        super();
        hasWifi = true;
        startingStation = "Barekamutyun";
    }

    public Metro(boolean hasWifi, int passingStations, String startingStation) {
        this.hasWifi = hasWifi;
        this.passingStations = passingStations;
        this.startingStation = startingStation;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nRoute Length: " + getRouteLength() + "\tIs Electric: " + isElectric() +
                "\tPassenger Seats: " + getPassengerSeats() + "\nHas Wifi: " + isHasWifi() + "\tPassing Stations: " +
                getPassingStations() + "\tStarting Station: " + getStartingStation();
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public int getPassingStations() {
        return passingStations;
    }

    public void setPassingStations(int passingStations) {
        this.passingStations = passingStations;
    }

    public String getStartingStation() {
        return startingStation;
    }

    public void setStartingStation(String startingStation) {
        this.startingStation = startingStation;
    }
}
