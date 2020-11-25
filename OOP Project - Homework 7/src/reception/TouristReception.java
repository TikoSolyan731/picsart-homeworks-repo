package reception;

import transport.TouristTransport;

public interface TouristReception extends Reception {
    void acceptPeople(int peopleCount, TouristTransport touristTransport, TouristReception from);
    void sendPeople(int peopleCount, TouristTransport touristTransport, TouristReception to);
}
