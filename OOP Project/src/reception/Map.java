package reception;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int[][] adjacencyMatrix = new int[City.values().length][City.values().length];
    private static Map instance;

    {
        setEdges(City.ROTTERDAM, List.of(City.SAN_FRANCISCO, City.STOCKHOLM, City.LISBON,
                City.SHANGHAI, City.HAMBURG), List.of(20000, 1000, 1500, 25000, 2000));
        setEdges(City.SYDNEY, List.of(City.SAN_FRANCISCO, City.VANCOUVER, City.RIO_DE_JANEIRO,
                City.SHANGHAI, City.SINGAPORE, City.HONG_KONG), List.of(10000, 12000, 18000, 8000, 7000, 8000));
        setEdges(City.SAN_FRANCISCO, List.of(City.VANCOUVER, City.RIO_DE_JANEIRO, City.SHANGHAI,
                City.SINGAPORE, City.HONG_KONG), List.of(2000, 4000, 9000, 10000, 9000));
        setEdges(City.STOCKHOLM, List.of(City.LISBON, City.HAMBURG), List.of(2000, 1000));
        setEdges(City.VANCOUVER, List.of(City.RIO_DE_JANEIRO, City.SINGAPORE), List.of(6000, 15000));
        setEdges(City.LISBON, List.of(City.RIO_DE_JANEIRO, City.HAMBURG), List.of(14000, 4000));
        setEdges(City.SHANGHAI, List.of(City.SINGAPORE, City.HONG_KONG), List.of(500, 1000));
        setEdges(City.SINGAPORE, List.of(City.HONG_KONG), List.of(1200));
    }

    public static Map getInstance() {
        if (instance == null)
            instance = new Map();
        return instance;
    }

    private Map() { }

    public boolean containsEdge(City v1, City v2) {
        return getEdge(v1, v2) != 0;
    }

    public int getEdge(City v1, City v2) {
        return adjacencyMatrix[v1.ordinal()][v2.ordinal()];
    }

    private void setEdge(City v1, City v2, int dis) {
        adjacencyMatrix[v1.ordinal()][v2.ordinal()] = dis;
        adjacencyMatrix[v2.ordinal()][v1.ordinal()] = dis;
    }

    private void setEdges(City v1, List<City> endVertices, List<Integer> distances) {
        for (int i = 0; i < endVertices.size(); i++) {
            setEdge(v1, endVertices.get(i), distances.get(i));
        }
    }

    public List<City> getAdjacentCities(City v1) {
        List<City> adjacent = new ArrayList<>();
        for (City v2 : City.values()) {
            if (containsEdge(v1, v2))
                adjacent.add(v2);
        }
        return adjacent;
    }

    public List<City> getDisconnectedCities(City v1) {
        List<City> adjacent = new ArrayList<>();
        for (City v2 : City.values()) {
            if (!containsEdge(v1, v2))
                adjacent.add(v2);
        }
        return adjacent;
    }
}
