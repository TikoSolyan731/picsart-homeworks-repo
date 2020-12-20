package utils;

import reception.AbstractReception;
import reception.Map;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class Receptions {
    public static final EnumMap<Map, ArrayList<AbstractReception>> receptions = new EnumMap<>(Map.class);

    static {
        receptions.put(Map.ROTTERDAM, new ArrayList<>(Arrays.asList(new CargoDock(Map.ROTTERDAM, 10, 2000), null)));
        receptions.put(Map.SYDNEY, new ArrayList<>(Arrays.asList(new CargoDock(Map.SYDNEY, 8, 4000), new TouristDock(Map.SYDNEY, 15, 500, 4000))));
        receptions.put(Map.SAN_FRANCISCO, new ArrayList<>(Arrays.asList(new CargoDock(Map.SAN_FRANCISCO, 9, 1000), null)));
        receptions.put(Map.STOCKHOLM, new ArrayList<>(Arrays.asList(new CargoDock(Map.STOCKHOLM, 7, 500), null)));
        receptions.put(Map.VANCOUVER, new ArrayList<>(Arrays.asList(null, new TouristDock(Map.VANCOUVER, 15, 1000, 5000))));
        receptions.put(Map.LISBON, new ArrayList<>(Arrays.asList(new CargoDock(Map.LISBON, 5, 400), new TouristDock(Map.LISBON, 12, 1500, 3900))));
        receptions.put(Map.RIO_DE_JANEIRO, new ArrayList<>(Arrays.asList(new CargoDock(Map.RIO_DE_JANEIRO, 8, 1500), new TouristDock(Map.RIO_DE_JANEIRO, 17, 2000, 2500))));
        receptions.put(Map.SHANGHAI, new ArrayList<>(Arrays.asList(new CargoDock(Map.SHANGHAI, 20, 8000), new TouristDock(Map.SHANGHAI, 14, 6000, 9000))));
        receptions.put(Map.SINGAPORE, new ArrayList<>(Arrays.asList(new CargoDock(Map.SINGAPORE, 25, 10000), new TouristDock(Map.SINGAPORE, 15, 2500, 6800))));
        receptions.put(Map.HONG_KONG, new ArrayList<>(Arrays.asList(new CargoDock(Map.HONG_KONG, 22, 4000), new TouristDock(Map.HONG_KONG, 10, 1200, 5000))));
        receptions.put(Map.HAMBURG, new ArrayList<>(Arrays.asList(new CargoDock(Map.HAMBURG, 4, 1500), null)));
    }
}
