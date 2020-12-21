package utils;

import reception.AbstractReception;
import reception.City;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class Receptions {
    public static final EnumMap<City, ArrayList<AbstractReception>> receptions = new EnumMap<>(City.class);

    static {
        receptions.put(City.ROTTERDAM, new ArrayList<>(Arrays.asList(new CargoDock(City.ROTTERDAM, 10, 2000), null)));
        receptions.put(City.SYDNEY, new ArrayList<>(Arrays.asList(new CargoDock(City.SYDNEY, 8, 4000), new TouristDock(City.SYDNEY, 15, 500, 4000))));
        receptions.put(City.SAN_FRANCISCO, new ArrayList<>(Arrays.asList(new CargoDock(City.SAN_FRANCISCO, 9, 1000), null)));
        receptions.put(City.STOCKHOLM, new ArrayList<>(Arrays.asList(new CargoDock(City.STOCKHOLM, 7, 500), null)));
        receptions.put(City.VANCOUVER, new ArrayList<>(Arrays.asList(null, new TouristDock(City.VANCOUVER, 15, 1000, 5000))));
        receptions.put(City.LISBON, new ArrayList<>(Arrays.asList(new CargoDock(City.LISBON, 5, 400), new TouristDock(City.LISBON, 12, 1500, 3900))));
        receptions.put(City.RIO_DE_JANEIRO, new ArrayList<>(Arrays.asList(new CargoDock(City.RIO_DE_JANEIRO, 8, 1500), new TouristDock(City.RIO_DE_JANEIRO, 17, 2000, 2500))));
        receptions.put(City.SHANGHAI, new ArrayList<>(Arrays.asList(new CargoDock(City.SHANGHAI, 20, 8000), new TouristDock(City.SHANGHAI, 14, 6000, 9000))));
        receptions.put(City.SINGAPORE, new ArrayList<>(Arrays.asList(new CargoDock(City.SINGAPORE, 25, 10000), new TouristDock(City.SINGAPORE, 15, 2500, 6800))));
        receptions.put(City.HONG_KONG, new ArrayList<>(Arrays.asList(new CargoDock(City.HONG_KONG, 22, 4000), new TouristDock(City.HONG_KONG, 10, 1200, 5000))));
        receptions.put(City.HAMBURG, new ArrayList<>(Arrays.asList(new CargoDock(City.HAMBURG, 4, 1500), null)));
    }
}
