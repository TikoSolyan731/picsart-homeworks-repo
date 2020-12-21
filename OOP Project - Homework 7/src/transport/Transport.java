package transport;

import reception.AbstractReception;

public interface Transport {
    void upgrade();
    void moveTo(AbstractReception to);
}
