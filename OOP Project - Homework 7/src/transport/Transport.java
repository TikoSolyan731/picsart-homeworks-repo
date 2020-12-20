package transport;

import reception.Reception;

public interface Transport {
    void upgrade();
    void moveTo(Reception to);
}
