package transport;

public class Transport {
    private double speed;
    private double weight;
    private double volume;

    public Transport() { }

    public Transport(double speed) {
        this.speed = speed;
    }

    public Transport(double speed, double weight, double volume) {
        this.speed = speed;
        this.weight = weight;
        this.volume = volume;
    }

    public String toString() {
        return getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
