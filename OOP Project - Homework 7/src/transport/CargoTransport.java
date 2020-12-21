package transport;

import reception.CargoReception;

public interface CargoTransport extends Transport {
    double dump();
    double emptyToCurrentPos();
    boolean takeFromCurrentPos(double weight);
    boolean transportTo(double weight, CargoReception to);
}
