package transport.waterTransport;

public class CruiseShip extends Ship {
    private int countryCount;
    private String[] visitingPlaces;
    private double ticketCost;

    public CruiseShip() {
        super();
    }

    public CruiseShip(int countryCount, String[] visitingPlaces, double ticketCost) {
        this.countryCount = countryCount;
        this.visitingPlaces = visitingPlaces;
        this.ticketCost = ticketCost;
    }

    public CruiseShip(int crew, String engine, int countryCount, String[] visitingPlaces, double ticketCost) {
        super(crew, engine);
        this.countryCount = countryCount;
        this.visitingPlaces = visitingPlaces;
        this.ticketCost = ticketCost;
    }

    @Override
    public String toString() {
        String str =  getClass().getName() + "\nSpeed: " + getSpeed() + "\tWeight: " + getWeight() + "\tVolume: "
                + getVolume() + "\nCrew Number: " + getCrew() + "\tEngine: " + getEngine() +
                "\nCountry Count: " + getCountryCount() + "\tVisiting: ";
        for (int i = 0; i < visitingPlaces.length; i++) {
            if (i == visitingPlaces.length - 1)
                str += visitingPlaces[i];
            else
                str += visitingPlaces[i] + ", ";
        }
        str += "\nTicket Cost: " + getTicketCost();
        return str;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(int countryCount) {
        this.countryCount = countryCount;
    }

    public String[] getVisitingPlaces() {
        return visitingPlaces;
    }

    public void setVisitingPlaces(String[] visitingPlaces) {
        this.visitingPlaces = visitingPlaces;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }
}
