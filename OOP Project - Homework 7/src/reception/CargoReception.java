package reception;

import transport.CargoTransport;

public interface CargoReception extends Reception {
    void acceptCargo(double weight, CargoTransport cargoTransport, CargoReception from);
    void sendCargo(double weight, CargoTransport cargoTransport, CargoReception to);
}
