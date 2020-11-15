package transport.waterTransport;

import transport.Transport;

public class Ship extends Transport {
    private int crew;
    private String engine = "Unknown";

    public Ship() {
        super();
        crew = 20;
    }

    public Ship(int crew, String engine) {
        super();
        this.crew = crew;
        this.engine = engine;
    }

    public Ship(double speed, double weight, double volume, int crew, String engine) {
        super(speed, weight, volume);
        this.crew = crew;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nCrew Number: " + getCrew() + "\tEngine: " + getEngine();
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
