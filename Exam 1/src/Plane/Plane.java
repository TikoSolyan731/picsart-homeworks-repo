package Plane;

public class Plane {
    private String model;
    private String country;
    private int year;
    private int hours;
    private boolean isMilitary;
    private String engineType;
    private int weight;
    private int wingspan;
    private int topSpeed;
    private int seats;
    private double cost;

    public Plane() {
        model = "Something";
        country = "Germany";
        year = 2012;
        hours = 0;
        isMilitary = false;
        engineType = "Some Engine";
        weight = 20000;
        wingspan = 30;
        topSpeed = 200;
        seats = 10;
        cost = 100000.0;
    }

    public Plane(String model, String country, int year, int hours, boolean isMilitary, String engineType,
                 int weight, int wingspan, int topSpeed, int seats, double cost) {
        setModel(model);
        setCountry(country);
        setYear(year);
        setHours(hours);
        setMilitary(isMilitary);
        setEngineType(engineType);
        setWeight(weight);
        setWingspan(wingspan);
        setTopSpeed(topSpeed);
        setSeats(seats);
        setCost(cost);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.isEmpty())
            this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (!country.isEmpty())
            this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 1903 && year <= 2020)
            this.year = year;
        else
            System.out.println("Enter legal year");
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours >= 0 && hours <= 10000)
            this.hours = hours;
        else
            System.out.println("Enter legal hours");
    }

    public boolean isMilitary() {
        return isMilitary;
    }

    public void setMilitary(boolean military) {
        isMilitary = military;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        if (!engineType.isEmpty())
            this.engineType = engineType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight >= 1000 && weight <= 160000)
            this.weight = weight;
        else
            System.out.println("Enter legal weight");
    }

    public int getWingspan() {
        return wingspan;
    }

    public void setWingspan(int wingspan) {
        if (wingspan >= 10 && wingspan <= 45)
            this.wingspan = wingspan;
        else
            System.out.println("Enter legal wingspan");
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (topSpeed >= 0)
            this.topSpeed = topSpeed;
        else
            System.out.println("Enter positive topSpeed");
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (seats >= 0)
            this.seats = seats;
        else
            System.out.println("Enter positive seats number");
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost >= 0.0)
            this.cost = cost;
        else
            System.out.println("Enter positive cost");
    }
}
