package transport.landTransport;

import transport.Transport;

public class Car extends Transport {
    private double mileage;
    private boolean isSport;

    public Car() {
        super();
    }

    public Car(double mileage) {
        super();
        this.mileage = mileage;
    }

    public Car(double speed, double mileage) {
        super(speed);
        this.mileage = mileage;
    }

    public Car(double speed, double weight, double volume, double mileage, boolean isSport) {
        super(speed, weight, volume);
        this.mileage = mileage;
        this.isSport = isSport;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nMileage: " + getMileage() + "\tIs Sport: " + isSport();
    }

    public boolean isSport() {
        return isSport;
    }

    public void setSport(boolean sport) {
        isSport = sport;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
