package services.waterReceptionServices;

import reception.AbstractReception;
import reception.Map;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;

import java.util.ArrayList;

public class DockService {
    public static ArrayList<Map> printCargoDocks() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Map> placesWithCargoDocks = new ArrayList<>();
        int i = 1;

        Map[] places = Map.values();
        for (Map place : places) {
            AbstractReception dock = place.getReceptions().get(0);
            if (dock instanceof CargoDock) {
                sb.append(i++).append(".").append(place).append(" - ").append(dock).append('\n');
                placesWithCargoDocks.add(place);
            }
        }

        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
        return placesWithCargoDocks;
    }

    public static ArrayList<Map> printTouristDocks() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Map> placesWithTouristDocks = new ArrayList<>();
        int i = 1;

        Map[] places = Map.values();
        for (Map place : places) {
            AbstractReception dock = place.getReceptions().get(1);
            if (dock instanceof TouristDock) {
                sb.append(i++).append(".").append(place).append(" - ").append(dock).append('\n');
                placesWithTouristDocks.add(place);
            }
        }

        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
        return placesWithTouristDocks;
    }
}
