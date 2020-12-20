package services.waterTransportServices;

import transport.waterTransport.Ship;

import java.util.ArrayList;

public class ShipService {
    public static void printShips(ArrayList<? extends Ship> list) {
        StringBuilder sb = new StringBuilder();
        var iter = list.iterator();
        int i = 1;

        while (iter.hasNext()) {
            sb.append(i).append(".").append(iter.next().getName()).append('\n');
            i++;
        }
        sb.append(i).append(".Go Back").append('\n');
        System.out.println(sb.toString());
    }
}
