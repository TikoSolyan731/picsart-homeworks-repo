package transport;

import reception.CargoReception;

public interface CargoTransport extends Transport {
    double empty();
    double emptyToCurrentPos();
    void takeFromCurrentPos(double weight);
    void transport(double weight, CargoReception from, CargoReception to);
}
