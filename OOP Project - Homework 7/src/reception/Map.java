package reception;

import utils.Receptions;

import java.util.ArrayList;

public enum Map {
    ROTTERDAM, SYDNEY, SAN_FRANCISCO, STOCKHOLM, VANCOUVER, LISBON,
    RIO_DE_JANEIRO, SHANGHAI, SINGAPORE, HONG_KONG, HAMBURG;

    static {
        ROTTERDAM.setReceptions(Receptions.receptions.get(Map.ROTTERDAM));
        SYDNEY.setReceptions(Receptions.receptions.get(Map.SYDNEY));
        SAN_FRANCISCO.setReceptions(Receptions.receptions.get(Map.SAN_FRANCISCO));
        STOCKHOLM.setReceptions(Receptions.receptions.get(Map.STOCKHOLM));
        VANCOUVER.setReceptions(Receptions.receptions.get(Map.VANCOUVER));
        LISBON.setReceptions(Receptions.receptions.get(Map.LISBON));
        RIO_DE_JANEIRO.setReceptions(Receptions.receptions.get(Map.RIO_DE_JANEIRO));
        SHANGHAI.setReceptions(Receptions.receptions.get(Map.SHANGHAI));
        SINGAPORE.setReceptions(Receptions.receptions.get(Map.SINGAPORE));
        HONG_KONG.setReceptions(Receptions.receptions.get(Map.HONG_KONG));
        HAMBURG.setReceptions(Receptions.receptions.get(Map.HAMBURG));
    }

    private ArrayList<AbstractReception> receptions;

    public ArrayList<AbstractReception> getReceptions() {
        return receptions;
    }

    public void setReceptions(ArrayList<AbstractReception> receptions) {
        this.receptions = receptions;
    }
}
