package services.waterReceptionServices;

import reception.AbstractReception;
import reception.City;
import reception.Map;
import reception.waterReception.CargoDock;
import reception.waterReception.TouristDock;

import java.util.ArrayList;
import java.util.List;

public class DockService {
    public static ArrayList<City> printCargoDocks() {
        StringBuilder sb = new StringBuilder();
        ArrayList<City> placesWithCargoDocks = new ArrayList<>();
        int i = 1;

        City[] places = City.values();
        for (City place : places) {
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

    public static ArrayList<City> printCargoDocks(City from) {
        StringBuilder sb = new StringBuilder();
        ArrayList<City> placesWithCargoDocks = new ArrayList<>();
        int i = 1;

        Map map = Map.getInstance();
        List<City> places = new ArrayList<>(map.getAdjacentCities(from));
        for (City place : places) {
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

    public static ArrayList<City> printTouristDocks() {
        StringBuilder sb = new StringBuilder();
        ArrayList<City> placesWithTouristDocks = new ArrayList<>();
        int i = 1;

        City[] places = City.values();
        for (City place : places) {
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

    public static ArrayList<City> printTouristDocks(City from) {
        StringBuilder sb = new StringBuilder();
        ArrayList<City> placesWithTouristDocks = new ArrayList<>();
        int i = 1;

        Map map = Map.getInstance();
        List<City> places = new ArrayList<>(map.getAdjacentCities(from));
        for (City place : places) {
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
