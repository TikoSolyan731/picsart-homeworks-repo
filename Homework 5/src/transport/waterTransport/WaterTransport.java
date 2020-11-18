package transport.waterTransport;

import reception.waterReception.Dock;

public interface WaterTransport {
    void move(Dock from, Dock to);
    void transport(double weight, Dock from, Dock to);
    void takeFromDock(double weight);
    void emptyToDock();
}
