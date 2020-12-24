package reception;

import reception.waterReception.Dock;
import utils.Receptions;

import java.util.*;

public enum City {
    ROTTERDAM, SYDNEY, SAN_FRANCISCO, STOCKHOLM, VANCOUVER, LISBON,
    RIO_DE_JANEIRO, SHANGHAI, SINGAPORE, HONG_KONG, HAMBURG;

    static {
        ROTTERDAM.setReceptions(Receptions.receptions.get(City.ROTTERDAM));
        SYDNEY.setReceptions(Receptions.receptions.get(City.SYDNEY));
        SAN_FRANCISCO.setReceptions(Receptions.receptions.get(City.SAN_FRANCISCO));
        STOCKHOLM.setReceptions(Receptions.receptions.get(City.STOCKHOLM));
        VANCOUVER.setReceptions(Receptions.receptions.get(City.VANCOUVER));
        LISBON.setReceptions(Receptions.receptions.get(City.LISBON));
        RIO_DE_JANEIRO.setReceptions(Receptions.receptions.get(City.RIO_DE_JANEIRO));
        SHANGHAI.setReceptions(Receptions.receptions.get(City.SHANGHAI));
        SINGAPORE.setReceptions(Receptions.receptions.get(City.SINGAPORE));
        HONG_KONG.setReceptions(Receptions.receptions.get(City.HONG_KONG));
        HAMBURG.setReceptions(Receptions.receptions.get(City.HAMBURG));
    }

    private ArrayList<AbstractReception> receptions;

    public ArrayList<AbstractReception> getReceptions() {
        return receptions;
    }

    public void setReceptions(ArrayList<AbstractReception> receptions) {
        this.receptions = receptions;
    }

    public static ArrayList<City> printPlaces(City... exclusions) {
        StringBuilder sb = new StringBuilder();
        List<City> exclusionsList = new ArrayList<>(Arrays.asList(exclusions));
        Set<City> citiesToReturn = new LinkedHashSet<>(City.values().length - exclusionsList.size());
        int i = 1;

        City[] places = City.values();
        for (City place : places) {
            if (!exclusionsList.contains(place)) {
                sb.append(i++).append(".").append(place).append('\n');
                citiesToReturn.add(place);
            }
        }

        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
        return new ArrayList<>(citiesToReturn);
    }

    public static int printDocksOfPlace(City place) {
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for (AbstractReception reception : place.getReceptions()) {
            if (reception instanceof Dock)
                sb.append(i++).append(".").append(reception).append('\n');
        }

        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
        return i;
    }
}
