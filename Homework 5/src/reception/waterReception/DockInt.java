package reception.waterReception;

import transport.waterTransport.Ship;

public interface DockInt {
    double acceptCargo(double weight, Ship ship, Dock from);
    double sendCargo(double weight, Ship ship, Dock to);
}
