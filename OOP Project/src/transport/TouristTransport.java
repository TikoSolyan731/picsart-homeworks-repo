package transport;

import reception.TouristReception;

public interface TouristTransport extends Transport {
    boolean transport(int peopleCount, TouristReception to);
}
