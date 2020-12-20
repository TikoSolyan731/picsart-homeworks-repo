package reception.waterReception;

import reception.AbstractReception;
import reception.Map;
import reception.Reception;
import transport.waterTransport.Ship;

import java.util.Arrays;

public abstract class Dock extends AbstractReception {
    private int maxDockedShips;
    private int dockedShipsCount = 0;
    private Ship[] dockedShips;

    public Dock(Map placement, int maxDockedShips) {
        super(placement);
        this.maxDockedShips = maxDockedShips;
        dockedShips = new Ship[maxDockedShips];
    }

    @Override
    public void upgrade() {
        int newMax = maxDockedShips + 5;
        Ship[] temp = Arrays.copyOf(dockedShips, newMax);

        maxDockedShips = newMax;
        dockedShips = temp;
    }

    public abstract void dockShip(Ship ship);

    public abstract void undockShip(Ship ship);

    public int getMaxDockedShips() {
        return maxDockedShips;
    }

    public int getDockedShipsCount() {
        return dockedShipsCount;
    }

    protected void setDockedShipsCount(int dockedShipsCount) {
        this.dockedShipsCount = dockedShipsCount;
    }

    public Ship[] getDockedShips() {
        return dockedShips;
    }
}
