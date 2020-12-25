package transport;

import reception.AbstractReception;

import java.util.Calendar;

public interface Transport {
    void upgrade();
    void moveTo(AbstractReception to);
    void moveTo(AbstractReception to, Calendar time);
}
