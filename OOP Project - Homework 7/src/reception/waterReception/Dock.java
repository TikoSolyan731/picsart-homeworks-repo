package reception.waterReception;

import reception.Reception;
import transport.waterTransport.Ship;

import java.util.Arrays;

public abstract class Dock implements Reception {
    private String name = "";
    private int maxDockedShips = 4;
    private int dockedShipsCount = 0;
    private Ship[] dockedShips = new Ship[maxDockedShips];

    public Dock(String name, int maxDockedShips) {
        this.name = name;
        this.maxDockedShips = maxDockedShips;
    }

    @Override
    public void upgrade() {
        int newMax = getMaxDockedShips() + 5;
        Ship[] temp = Arrays.copyOf(getDockedShips(), newMax);

        setMaxDockedShips(newMax);
        setDockedShips(temp);
    }

    public abstract void dockShip(Ship ship);

    public abstract void undockShip(Ship ship);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxDockedShips() {
        return maxDockedShips;
    }

    public void setMaxDockedShips(int maxDockedShips) {
        this.maxDockedShips = maxDockedShips;
    }

    public int getDockedShipsCount() {
        return dockedShipsCount;
    }

    public void setDockedShipsCount(int dockedShipsCount) {
        this.dockedShipsCount = dockedShipsCount;
    }

    public Ship[] getDockedShips() {
        return dockedShips;
    }

    public void setDockedShips(Ship[] dockedShips) {
        this.dockedShips = dockedShips;
    }
}
