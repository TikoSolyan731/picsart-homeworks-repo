package transport.waterTransport;

public class CargoShip extends Ship {
    private double cargoCapacity;
    private String from;
    private String to;

    public CargoShip(double cargoCapacity, String from, String to) {
        this.cargoCapacity = cargoCapacity;
        this.from = from;
        this.to = to;
    }

    public CargoShip(int crew, String engine, double cargoCapacity, String from, String to) {
        super(crew, engine);
        this.cargoCapacity = cargoCapacity;
        this.from = from;
        this.to = to;
    }

    public CargoShip(double speed, double weight, double volume, int crew, String engine, double cargoCapacity, String from, String to) {
        super(speed, weight, volume, crew, engine);
        this.cargoCapacity = cargoCapacity;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nCrew Number: " + getCrew() + "\tEngine: " + getEngine() +
                "\nCargo Capacity: " + getCargoCapacity() + "\tTransporting From: " + getFrom() +
                "\tTransporting To: " + getTo();
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
