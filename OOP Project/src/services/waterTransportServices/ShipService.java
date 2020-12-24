package services.waterTransportServices;

import transport.waterTransport.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipService {
    public static void printShips(List<? extends Ship> list) {
        StringBuilder sb = new StringBuilder();
        var iter = list.iterator();
        int i = 1;

        while (iter.hasNext()) {
            Ship next = iter.next();
            sb.append(i).append(".").append(next.getName()).append(" - ").append(next.getCurrentPos().getPlacement())
                    .append(" ").append(next.getCurrentPos()).append('\n');
            i++;
        }
        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
    }
}
