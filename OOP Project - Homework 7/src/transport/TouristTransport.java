package transport;

import reception.TouristReception;

public interface TouristTransport extends Transport {
    void transport(int peopleCount, TouristReception from, TouristReception to);
}
